package com.poly.rest.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.model.Account;
import com.poly.model.Product;
import com.poly.service.AccountService;

@CrossOrigin("*")
@RestController
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
	@PostMapping("/rest/account")
	public ResponseEntity<Account> post(@RequestBody Account account) {
	    Account createdAccount = accountService.create(account);
	    return ResponseEntity.ok(createdAccount);
	}
	
	@GetMapping("/rest/accounts")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}

}

