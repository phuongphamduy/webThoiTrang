package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDAO;
import com.example.dao.AuthorityDAO;
import com.example.entity.Account;
import com.example.entity.Authority;
import com.example.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO accountDAO;

	public List<Authority> findAll() {
		return dao.findAll();
	}

	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountDAO.getAdministrators();
		return dao.authoritiesOf(accounts);
	}
}
