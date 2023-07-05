package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.model.Product;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public Product findById(String id) {
	    Optional<Product> optionalProduct = productDAO.findById(id);
	    return optionalProduct.orElse(null);
	}


	@Override
	public List<Product> findByCategoryId(String cid) {
		return productDAO.getByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {
		return productDAO.save(product);
	}

	@Override
	public Product update(Product product) {
		return productDAO.save(product);
	}

	@Override
	public void delete(String id) {
		productDAO.deleteById(id);		
	}
	
	@Override
	public boolean existsById(String id) {
		if(productDAO.existsById(id))
			return true;
		return false;
	}

	@Override
	public List<Product> findByProductNew() {
		return productDAO.getByProductNew();
	}

	@Override
	public List<Product> searchByProductNameOrId(String productName, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAllProductCustomerLike(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
