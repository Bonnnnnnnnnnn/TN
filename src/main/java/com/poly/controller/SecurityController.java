package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.poly.model.Account;
import com.poly.repository.AccountRepository;
import com.poly.service.AccountService;

@Controller
public class SecurityController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepository; // Ghi chú: AccountRepository là một interface để tương tác với bảng "account" trong cơ sở dữ liệu

	
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "/user/security/login_register";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "/user/security/login_register";
	}

	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "/user/security/login_register";
	}

	@RequestMapping("/security/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		return "/user/security/login_register";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy xuất!");
		return "/user/security/login_register";
	}
	
	@RequestMapping("/security/register/form")
	public String registerForm(Model model) {
		model.addAttribute("regisActive", "right-panel-active");
		return "/user/security/login_register";
	}
	
	@GetMapping("/security/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		return "/user/security/login_register";
	}
	
    @PostMapping("/security/register")
    public String registerUser(@ModelAttribute Account account) {
        accountService.create(account);    
        return "/user/security/login_register";
    }
}