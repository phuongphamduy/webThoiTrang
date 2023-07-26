package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {

}
