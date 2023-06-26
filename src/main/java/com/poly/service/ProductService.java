package com.poly.service;

import com.poly.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
	List<Product> findAll();
	
	Product findById(Integer id);
	
	List<Product> findByCategoryId(Integer cid);
	
	List<Product> findByProductNew();
	
	Product create(Product product);
	
	Product update(Product product);
	
	void delete(Integer id);
}
