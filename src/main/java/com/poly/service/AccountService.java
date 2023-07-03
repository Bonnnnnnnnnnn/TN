package com.poly.service;

import java.util.List;

import com.poly.model.Account;

public interface AccountService {
	Account findById(String username);
	
	Account create(Account account);
	
	List<Account> findAll();

	List<Account> getAdministrators();

	Account resetPassword(String email);
	
}
