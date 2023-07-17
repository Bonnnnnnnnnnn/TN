package com.poly.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.TotalProductsUtil;
import com.poly.model.Account;
import com.poly.model.Category;
import com.poly.model.Favourite;
import com.poly.model.Product;
import com.poly.service.CategoryService;
import com.poly.service.FavouriteService;
import com.poly.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	FavouriteService favouriteService;
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, HttpSession session, @PathVariable("id") Integer id) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		
		//Hiển thị số lượng yêu thích
  		int totalProducts = TotalProductsUtil.getTotalProducts();
  		model.addAttribute("totalProducts", totalProducts);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> authentication = (Map<String, Object>) session.getAttribute("authentication");
		if(authentication != null) {
			Account account = (Account) authentication.get("user");
			Favourite favourite = favouriteService.create(account, item);
			model.addAttribute("flagLikedBtn", favourite.getIsLiked());
		}
		return "user/product/detail";
	}
	
	@RequestMapping("/product/list")
	public String list(Model model, HttpServletRequest request, @RequestParam("cid")Optional<String> cid, @RequestParam(defaultValue = "1") int page) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories); 
        
        //Hiển thị số lượng yêu thích
  		int totalProducts = TotalProductsUtil.getTotalProducts();
  		model.addAttribute("totalProducts", totalProducts);
        
		List<Product> list;
		
		list = productService.findByProductNew();
		model.addAttribute("productsNew", list);
		
		if (cid.isPresent()) {
			list= productService.findByCategoryId(cid.get());
		}else {
			list = productService.findAll();
		}
		
		if(list.isEmpty()) {
			int maxPages = 0;
			model.addAttribute("items", list);
			model.addAttribute("currentPage", page);
			model.addAttribute("maxPages", maxPages);
			return "user/product/list";
		}
		
		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i).getProductQuantity() == 0) {
				list.remove(list.get(i));
			}
		}
		
		System.out.println("List: " + list.size());
		/*
		 * 		pageSize   : số lượng sản phẩm trên 1 trang
		 * 		maxPages   : Tổng số trang
		 * 		startIndex : chỉ số bắt đầu của sản phẩm cần hiển thị trên trang hiện tại
		 */
		
		int pageSize = 12; // Kích thước trang
		int maxPages = (int) Math.ceil((double) list.size() / pageSize); 

		int startIndex = (page - 1) * pageSize;

		startIndex = Math.min(startIndex, list.size()); // Đảm bảo không vượt quá số lượng sản phẩm
		int endIndex = Math.min(startIndex + pageSize, list.size()); // Chỉ số kết thúc của sản phẩm cần hiển thị trên trang hiện tại
 
		// Kiểm tra và xử lý lỗi
	    if (startIndex == 0) {
	        startIndex = 0;
	    }

	    if (endIndex > list.size()) {
	        endIndex = list.size();
	    } 
	    
	 // dssp cần hiển thị
 		List<Product> productsOnPage = list.subList(startIndex, endIndex); // Danh sách sản phẩm trên trang hiện tại
 		model.addAttribute("items", productsOnPage);
 		model.addAttribute("currentPage", page);
 		model.addAttribute("maxPages", maxPages);
	 		
		return "user/product/list";
	}

}
