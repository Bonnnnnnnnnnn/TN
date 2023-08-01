package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Account;

public interface AccountDAO extends JpaRepository<Account, String>{
	// Lấy tất cả account có vai trò DIRE và Staf
	@Query("select distinct ar.account from Authority ar where ar.role.id in ('DIRE', 'STAF')")
	List<Account> getAdministrators();
	
	@Query(value = "select * from Accounts where Email = ?1", nativeQuery = true)
	Account findByEmail(String email);
}
