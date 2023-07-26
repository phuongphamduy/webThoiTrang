package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

}
