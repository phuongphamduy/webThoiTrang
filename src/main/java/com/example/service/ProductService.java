package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.entity.Product;

public interface ProductService {

//	List<Product> getProducts();

	Product findById(Long id);

	Product create(Product product);

	Product update(Product product);

	void delete(Long id);
	
	Page<Product> getProductPage(Pageable pageable);

	List<Product> getProduct();

	

	

}
