package com.poly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.TotalProductsUtil;
import com.poly.model.Category;
import com.poly.model.Discount;
import com.poly.service.CategoryService;
import com.poly.service.DiscountService;
import com.poly.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	DiscountService discountService;

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		
		//Hiển thị số lượng yêu thích
  		int totalProducts = TotalProductsUtil.getTotalProducts();
  		model.addAttribute("totalProducts", totalProducts);
  		
  		
		
		return "user/order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") long id, Model model) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        
        //Hiển thị số lượng yêu thích
  		int totalProducts = TotalProductsUtil.getTotalProducts();
  		model.addAttribute("totalProducts", totalProducts);
        
		var order = orderService.findById(id);
		Discount discount;
		if (order.getDiscountId() != null) {
			discount = discountService.findById(order.getDiscountId());
		} else {
			discount = new Discount();
			discount.setPrice(0.0);
		}
		double total = order.getOrderDetails().stream()
                .mapToDouble(detail -> detail.getQuantity() * detail.getPrice())
                .sum();
		System.out.println("total : "+total);
		model.addAttribute("total", total);
		model.addAttribute("order", order);
		model.addAttribute("discount", discount);
		return "user/order/detail";
	}

	@RequestMapping("/order/checkout/success/{id}")
	public String paymentSuccess(@PathVariable("id") long id, HttpServletRequest request, Model model) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        
        //Hiển thị số lượng yêu thích
        int totalProducts = TotalProductsUtil.getTotalProducts();
		model.addAttribute("totalProducts", totalProducts);
        
		var paypalOrderId = request.getParameter("token");
		var out = orderService.findById(id);
		out.setPaypalOrderId(paypalOrderId);
		out.setPaypalOrderStatus("PAID");
		orderService.update(out);
		Discount discount;
		if (out.getDiscountId() != null) {
			discount = discountService.findById(out.getDiscountId());
		} else {
			discount = new Discount();
			discount.setPrice(0.0);
		}
		double total = out.getOrderDetails().stream()
                .mapToDouble(detail -> detail.getQuantity() * detail.getPrice())
                .sum();
		System.out.println("total : "+total);
		model.addAttribute("order", out);
		model.addAttribute("total", total);
		model.addAttribute("discount", discount);
		return "user/order/detail";
	}
	
	
	
	
}
