package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.model.Order;

public interface OrderService {
	
	Order create(JsonNode orderData);

	Order findById(long id);

	List<Order> findByUsername(String username);

	List<Order> findAll();
}
