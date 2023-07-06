package com.poly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.model.Account;
import com.poly.model.Product;

@Service
public interface AccountService {
	Account findById(String username);
	
	Account create(Account account);
	
	List<Account> findAll();

	List<Account> getAdministrators();

	Account resetPassword(String email);
	
	
}
