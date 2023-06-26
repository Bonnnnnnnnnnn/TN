package com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired 
	AccountDAO dao;
	
	@Override
	public Account findById(String username) {
		// TODO Auto-generated method stub
		return dao.findById(username).get();
	}

	@Override
	public Account create(Account account) {
		// TODO Auto-generated method stub
		return dao.save(account);
	}

}
