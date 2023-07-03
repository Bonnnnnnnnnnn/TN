package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") long id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "user/order/detail";
	}
}
