package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {

}
