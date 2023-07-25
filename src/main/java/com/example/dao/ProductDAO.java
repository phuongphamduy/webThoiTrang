package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long> {

}
