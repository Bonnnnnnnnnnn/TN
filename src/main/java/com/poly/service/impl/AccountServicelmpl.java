package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;
import com.poly.service.AccountService;

@Service
public class AccountServicelmpl implements AccountService{

	@Autowired 
	AccountDAO dao;
	
	@Override
	public Account findById(String username) {		
		return dao.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}
	
	@Override
	public Account create(Account account) {
		return dao.save(account);
	}
	
	@Override
	public Account resetPassword(String email) {
		Account existAccount = dao.findByEmail(email);
		if(existAccount != null) {
			// random 4 số | 1000 - 9999 | công thức: (Math.random()) * ((max - min) + 1)) + min
			String newPass = String.valueOf((int)(Math.random() * ((9999 - 1000) + 1)) + 1000);
			existAccount.setPassword(newPass); // set password cho user
			return dao.save(existAccount); // gọi xuống dao
		}
		return null;
	}

}
