package com.example.service;

import java.util.List;

import com.example.entity.Account;

public interface AccountService {
	List<Account> getUser();

	Account findById(String id, String username);
	
	Account FindByUsernameActivated(String id);

	Account update(Account account);

	public List<Account> findAll();
	public Account findById(String username) ;
	public List<Account> getAdministrators() ;
	
}
