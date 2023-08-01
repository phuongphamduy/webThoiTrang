package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/products")
public class ProductRestController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("{id}")
	public Product getProduct(@PathVariable("id") Long id) {
		return service.findById(id);
	}
}
