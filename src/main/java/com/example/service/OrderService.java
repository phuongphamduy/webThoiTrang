package com.example.service;

import java.util.List;

import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	List<Order> findAll(); 
	
	Order create(JsonNode orderData);

	Order findById(Integer id);
	
	List<Order> findByUsername(String username);
	
//	List<OrderDetail> findByOrderId(Integer orderid);
	
	List<OrderDetail> findAllOD();

	Order update(Order order);
}
