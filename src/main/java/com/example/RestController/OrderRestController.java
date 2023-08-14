package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService service;
	
	@GetMapping()
	public List<Order> findAll(){
		return service.findAll();
	}
	
	@PostMapping()
	public Order create(@RequestBody JsonNode orderData) {
		return service.create(orderData);
	}
	
	@GetMapping("{id}")
	public List<OrderDetail>  od(@PathVariable("id") Integer id) {
		return service.findById(id).getOrderDetails();
	}
}
