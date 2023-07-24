package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.model.Account;
import com.poly.service.AccountService;

@Service
public class AccountServicelmpl implements AccountService{

	@Autowired 
	AccountDAO dao;
	
	  @Autowired
	  PasswordEncoder passwordEncoder; // Tiêm bộ mã hóa mật khẩu vào
	
	
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
    public Account updatePassword(Account account, String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword)); // Hash the new password before saving
        return dao.save(account);
    }
	
	@Override
    public Account resetPassword(String email) {
        Account existAccount = dao.findByEmail(email);
        if (existAccount != null) {
            String newPass = String.valueOf((int) (Math.random() * ((9999 - 1000) + 1)) + 1000);
            String encodedPassword = passwordEncoder.encode(newPass); // Mã hoá mật khẩu mới trước khi lưu
            existAccount.setPassword(encodedPassword);
            return dao.save(existAccount); // Lưu thông tin người dùng với mật khẩu đã mã hoá
        }
        return null;
    }

}
