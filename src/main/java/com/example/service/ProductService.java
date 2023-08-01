package com.example.service;

import java.util.List;

import com.example.entity.Account;
import com.example.entity.Product;

public interface ProductService {

	List<Product> getProduct();

	Product findById(Long id);

	Product create(Product product);
	

	

}
