package com.example.service;

import java.util.List;

import com.example.entity.Category;
import com.example.entity.Product;

public interface CategoryService {

	List<Category> findAll();

	List<Product> ProductOfCates(Integer id);

	Category create(Category category);

	Category update(Category category);

	void delete(Integer id);

}
