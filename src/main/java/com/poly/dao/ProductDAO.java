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
    
    @Query(value="SELECT TOP 10 * FROM Products ORDER BY CreateDate DESC", nativeQuery = true)
    List<Product> getByProductNew();
    
    @Query(value="SELECT * FROM Products WHERE star= 5", nativeQuery = true)
    List<Product> getByProductTop();
    
//    @Query("SELECT distinct p FROM Products p WHERE p.name LIKE %?1% OR p.id LIKE %?2%")
//    List<Product> searchByProductNameOrId(String productName, String id);
//    
    // Hiển thị các sản phẩm user đã thích
    @Query(value = "SELECT * FROM Products p INNER JOIN Favourites f ON p.Id = f.ProductId WHERE Username = ?1 AND IsLiked = 1", nativeQuery = true)
    List<Product> findAllProductCustomerLike(String username);
}
