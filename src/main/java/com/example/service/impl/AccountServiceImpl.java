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
	
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}
	
	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}
	
	@Override
	public Account findById(String id, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account FindByUsernameActivated(String id) {
		return dao.findByUsernameAndActive(id, true);
	}

	@Override
	public Account update(Account account) {
		return dao.save(account);
	}

	
	
}
