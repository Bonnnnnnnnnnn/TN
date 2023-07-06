package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.poly.model.Account;
//import com.poly.model.MailInfo;
import com.poly.service.AccountService;
//import com.poly.service.EmailService;


@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	//EmailService emailService;
	
	@GetMapping("/account/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		return "security/login_register.html";
	}
	
    @RequestMapping("/account/register")
    public String registerUser(@ModelAttribute Account account) {
        accountService.create(account);     
        return "security/login_register.html";
    }
    
 //   @GetMapping("/account/forgot-pass")
 //   public String forgotPass() {
 //   	return "web/security/forgot-pass";
 //   }
    }
    
    
   