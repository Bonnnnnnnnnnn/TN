package com.poly.service;

import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.model.Order;
import com.poly.model.Product;

public interface OrderService {

	Order create(JsonNode orderData);
	
	// Khi đặt hàng thành công rồi. Sẽ hiển thị lại hóa đơn cho khách hàng
	Order findById(Long id);
	
	// Cho khách xem lại danh sách đơn hàng của chính khách đặt
	List<Order> findByUsername(String username);
	
	// Admin: hiển thị các hóa đơn theo ngày chỉ định
	List<Order> getOrderByDay(String day);
	
	List<Order> findAll();
	
	List<Order> findByWaitConfirm();

	List<Order> findByWaitingForShipping();
	
	Order update(Order order);
}
