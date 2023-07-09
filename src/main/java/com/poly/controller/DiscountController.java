package com.poly.controller;

import com.poly.model.Discount;
import com.poly.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Controller
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @RequestMapping("/discount")
    public String getDiscount(@RequestParam("code") String code, Model model) {
        Discount discounts = discountService.findByCode(code);
        double discount = 0.0;
        if (discounts != null) discount = discounts.getPrice();
        model.addAttribute("price", discount);
        return "/user/cart/view";
    }
}
