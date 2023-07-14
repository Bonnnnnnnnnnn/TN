package com.poly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.Category;
import com.poly.service.CategoryService;
import com.poly.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "user/order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") long id, Model model) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        
		model.addAttribute("order", orderService.findById(id));
		return "user/order/detail";
	}
}
