package com.poly.service;

import com.poly.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
	List<Product> findAll();
	
	Product findById(Integer id);
	
	List<Product> findByCategoryId(String id);
	
	List<Product> findByProductNew();
	
	Product create(Product product);
	
	Product update(Product product);
	boolean existsById(Integer integer);
	
	public void delete(Integer id);
}
