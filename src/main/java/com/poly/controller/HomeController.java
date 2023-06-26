package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.model.Category;
import com.poly.model.Product;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;

@Controller
public class HomeController{
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = {"/","/home/index"})
	public String home(Model model) {
		List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        
        List<Category> top4Category = categoryService.findByTop4Categoy();
        model.addAttribute("top4Category", top4Category);
        
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        
		return "/user/home/home";
	}
	
//	@RequestMapping(value = {"/admin","/admin/home/index"})
//	public String admin() {
//		return "redirect:/assets/admin/index.html";
//	}
}
