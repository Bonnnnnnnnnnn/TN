package com.poly.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Order;



public interface OrderDAO extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);
	
	
	// Admin: hiển thị các hóa đơn theo ngày chỉ định
	@Query(value = "select * from Orders where CreateDate = ?1", nativeQuery = true)
	List<Order> getOrderByDay(String day);
	
	//Admin: Hiển thị ds order đợi xác nhận
	@Query(value = "select * from Orders where Status = N'Đợi xác nhận' ", nativeQuery = true)
	List<Order> findByWaitConfirm();
	
	//Admin: Hiển thị ds order đang giao
	@Query(value = "select * from Orders where Status = N'Đang giao' ", nativeQuery = true)
	List<Order> findByWaitingForShipping();
	
	//Admin: Hiển thị ds order đã giao
	@Query(value = "select * from Orders where Status = N'Đã giao' ", nativeQuery = true)
	List<Order> findByDelivered();
	
	//Admin: Hiển thị ds order đã hủy
	@Query(value = "select * from Orders where Status = N'Đã hủy' ", nativeQuery = true)
	List<Order> findByCancelled();
}
