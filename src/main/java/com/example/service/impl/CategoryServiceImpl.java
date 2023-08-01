package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CategoryDAO;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO dao;
	
	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Product> ProductOfCates(Integer id) {
		return dao.findById(id).get().getProducts();
	}

	@Override
	public Category create(Category category) {
		return dao.save(category);
	}

	@Override
	public Category update(Category category) {
		return dao.save(category);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
