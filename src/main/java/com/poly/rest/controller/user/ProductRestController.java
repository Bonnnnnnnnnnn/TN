package com.poly.rest.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.model.Product;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	@GetMapping("")
	public List<Product> getAll() {
		return productService.findAll();
	}
	
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id")String id) {
		return productService.findById(id);
	}
	
//	@PostMapping
//	public Product create(@RequestBody Product product ) {
//		return productService.create(product);
//	}
//	
//	@PutMapping("{id}")
//	public Product update(@PathVariable("id")String id,@RequestBody Product product ) {
//		return productService.update(product); 
//	}
//	
//	@DeleteMapping("{id}")
//	public void delete(@PathVariable("id")String id) {
//		productService.delete(id); 
//	}
}
