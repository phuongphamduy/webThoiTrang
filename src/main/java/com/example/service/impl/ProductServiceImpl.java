package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDAO;
import com.example.entity.Product;
import com.example.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO dao;

	@Override
	public List<Product> getProduct() {
		return dao.findAll();
	}

	@Override
	public Product findById(Long id) {
		return dao.findById(id).get();
	}
	
	
}
