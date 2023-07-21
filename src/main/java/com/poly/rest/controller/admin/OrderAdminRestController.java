package com.poly.rest.controller.admin;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.model.Order;
import com.poly.model.Product;
import com.poly.service.OrderService;
import com.poly.service.impl.OrderServiceImpl;



@CrossOrigin("*")
@RestController
public class OrderAdminRestController {
	
	@Autowired
	OrderService orderService = new OrderServiceImpl();
	
	// Admin: hiển thị các hóa đơn theo ngày chỉ định
	@GetMapping("/rest/order-by-day/{day}")
	public ResponseEntity<List<Order>> getQuantitiesByProduct(@PathVariable("day") String day){
		return ResponseEntity.ok(orderService.getOrderByDay(day));
	}
	
	@GetMapping("/rest/order")
	public ResponseEntity<List<Order>> getAll() {
		List<Order> order = orderService.findAll();
		return ResponseEntity.ok(order);
	}

	
}
