package com.poly.service;

import com.poly.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
	List<Product> findAll();
	
	Product findById(String id);
	
	List<Product> findByCategoryId(String id);
	
	List<Product> findByProductNew();
	
	Product create(Product product);
	
	Product update(Product product);
	boolean existsById(String id);
	
	public void delete(String id);
	
	// Search product name or product id
	List<Product> searchByProductNameOrId(String productName, String id);
	
	List<Product> findAllProductCustomerLike(String username);
}
