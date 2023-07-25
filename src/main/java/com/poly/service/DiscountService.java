package com.poly.service;

import com.poly.model.Discount;
import com.poly.model.Product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DiscountService {
	
	List<Discount> findAll();

    Discount findByCode(String code);

    Discount findById(Long id);
    
    Discount create(Discount discount);
	
    Discount update(Discount discount);
	boolean existsById(Integer id);
	
	public void delete(Integer id);
	
}
