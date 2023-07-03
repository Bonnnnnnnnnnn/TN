package com.poly.repository;

import com.poly.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Các phương thức truy vấn khác (nếu cần)
}

