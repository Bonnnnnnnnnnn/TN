package com.poly.dao;

import com.poly.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    List<Product> getByCategoryId(String cid);
    
    @Query(value="SELECT * FROM Products WHERE Status = 1", nativeQuery = true)
    List<Product> getByProductNew();
}
