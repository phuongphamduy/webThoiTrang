package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/category")
public class CategoryRestController {
	@Autowired
	CategoryService service;

	@GetMapping
	public List<Product> getCategory() {
		return service.findAll();
	}
}