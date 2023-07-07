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
    
    @Query(value="SELECT * FROM Products WHERE status = 1", nativeQuery = true)
    List<Product> getByProductNew();
    
//    @Query("SELECT distinct p FROM Products p WHERE p.name LIKE %?1% OR p.id LIKE %?2%")
//    List<Product> searchByProductNameOrId(String productName, String id);
//    
//    @Query(value = "SELECT * FROM Products p INNER JOIN favourites f ON p.id = f.productId WHERE username = ?1 AND is_liked = 1", nativeQuery = true)
//    List<Product> findAllProductCustomerLike(String username);
}
