package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/product")
public class ProductRestController {
	
	@Autowired
	ProductService service;
	
	@GetMapping
	public List<Product> getProduct() {
		return service.getProduct();
	}
}
