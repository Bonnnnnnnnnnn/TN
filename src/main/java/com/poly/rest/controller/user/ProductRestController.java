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

	@GetMapping("/rest/product")
	public List<Product> getAll() {
		return productService.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build(); // 404 not found
		}
	}

	@GetMapping("/product-quantity/{id}")
	public ResponseEntity<List<Product>> getQuantitiesByProduct(@PathVariable("id") String id) {
		List<Product> products = productService.findByCategoryId(id);
		if (products != null) {
			return ResponseEntity.ok(products);
		} else {
			return ResponseEntity.notFound().build(); // 404 not found
		}
	}

	@PostMapping("/product")
	public ResponseEntity<Product> post(@RequestBody Product product) {
		if (productService.existsById(product.getId())) {
			return ResponseEntity.badRequest().build(); // 400 bad request
		}
		productService.create(product);
		return ResponseEntity.ok(product);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Product> put(@PathVariable("id") Integer id, @RequestBody Product product) {
		Product existingProduct = productService.findById(id);
		if (existingProduct != null) {
			productService.update(product);
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build(); // 404 not found
		}
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Product existingProduct = productService.findById(id);
		if (existingProduct != null) {
			productService.delete(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build(); // 404 not found
		}
	}
}
