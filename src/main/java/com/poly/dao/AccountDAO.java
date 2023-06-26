package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Account;

public interface AccountDAO extends JpaRepository<Account, String>{
	@Query(value = "select * from Accounts where UserEmail = ?1", nativeQuery = true)
	Account findByEmail(String email);
}
