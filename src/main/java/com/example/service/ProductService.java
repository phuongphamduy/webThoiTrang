package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.entity.Product;

public interface ProductService {

	List<Product> getProduct();

	Product findById(Long id);

	Page<Product> getProductPage(Pageable pageable);
	Product create(Product product);
	

	

}
