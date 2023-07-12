package com.poly.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.model.Discount;
import com.poly.model.paypal.PayPalResponse;
import com.poly.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	DiscountService discountService;

	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") long id, Model model) {
		var order = orderService.findById(id);
		Discount discount;
		if (order.getDiscountId() != null) {
			discount = discountService.findById(order.getDiscountId());
		} else {
			discount = new Discount();
			discount.setPrice(0.0);
		}

		model.addAttribute("order", order);
		model.addAttribute("discount", discount);
		return "user/order/detail";
	}

	@RequestMapping("/order/checkout/success/{id}")
	public String paymentSuccess(@PathVariable("id") long id, HttpServletRequest request, Model model) {
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
		model.addAttribute("order", out);
		model.addAttribute("discount", discount);
		return "user/order/detail";
	}
}
