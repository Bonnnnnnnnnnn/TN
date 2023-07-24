package com.poly.rest.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.model.Discount;
import com.poly.service.DiscountService;

@CrossOrigin("*")
@RestController
public class DiscountAdminRestController {
	@Autowired
	private DiscountService discountService;


	@GetMapping("/rest/discount")
	public ResponseEntity<List<Discount>> getAll() {
		List<Discount> discounts = discountService.findAll();
		return ResponseEntity.ok(discounts);
	}

	@GetMapping("/rest/discount/{id}")
	public ResponseEntity<Discount> findOne(@PathVariable("id") Integer id) {
		Discount discount = discountService.findById();
		if (discount != null) {
			return ResponseEntity.ok(discount);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/rest/discount")
	public ResponseEntity<Discount> post(@RequestBody Discount discount) {
		Discount createdProduct = discountService.create(discount);
		return ResponseEntity.ok(createdProduct);
	}
//	@PutMapping("/rest/discount/{id}")
//	public ResponseEntity<Discount> put(@PathVariable("id") Integer id, @RequestBody Discount discount) {
//		Discount existingProduct = discountService.findById(id);
//		if (existingProduct != null) {
//			discount.setProductId(id);
//			discount updatedDiscount = discountService.update(discount);
//			return ResponseEntity.ok(updatedDiscount);
//		}
//		return ResponseEntity.notFound().build();
//	}

	@DeleteMapping("/rest/discount/{id}")
	public ResponseEntity<Void> delete(@PathVariable("discountId") Integer id) {
		Discount existingDiscount = discountService.findById(id);
		if (existingDiscount != null) {
			discountService.delete(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}