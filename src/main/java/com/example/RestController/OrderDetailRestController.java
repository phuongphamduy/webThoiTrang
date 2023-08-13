package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderDetails")
public class OrderDetailRestController {
	@Autowired
	OrderService os;
	
//	@GetMapping("{orderid}")
//	public List<OrderDetail> findAllByOrderId(@PathVariable("orderid") Integer orderid){
//		return os.findByOrderId(orderid);
//	}
	
	@GetMapping()
	public List<OrderDetail> findAllOD(){
		return os.findAllOD();
	}
	
	
}
