package com.poly.service;

import com.poly.model.Account;

public interface AccountService {
	Account findById(String username);
	
	Account create(Account account);
}
