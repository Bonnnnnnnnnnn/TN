package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.model.Category;
import com.poly.model.Product;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "user/product/detail";
	}
	
	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cid")Optional<Integer> cid) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories); 
        
		List<Product> list;
		
		list = productService.findByProductNew();
		model.addAttribute("productsNew", list);
		
		if (cid.isPresent()) {
			list= productService.findByCategoryId(cid.get());
		}else {
			list = productService.findAll();
		}
		model.addAttribute("items", list);
		return "user/product/list";
	}

}
