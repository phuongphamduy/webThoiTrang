package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {

}
