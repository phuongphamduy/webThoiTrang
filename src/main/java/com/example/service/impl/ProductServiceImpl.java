package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<Product> getProductPage(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Product create(Product product) {
		return dao.save(product);
	}

	@Override
	public Product update(Product product) {
		return dao.save(product);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
		;
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}
}
