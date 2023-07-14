package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.Category;
import com.poly.service.CategoryService;

@Controller
public class ShoppingCartController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/cart/view")
	public String viewCart(Model model) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
		return "/user/cart/view";
	}
}
