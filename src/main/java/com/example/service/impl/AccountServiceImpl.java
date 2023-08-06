package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO dao;
	
	@Override
	public List<Account> getUser() {
		return dao.findAll();
	}

	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}

	@Override
	public Account findById(String id, String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
