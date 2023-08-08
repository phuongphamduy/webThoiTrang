package com.example.service;

import java.util.List;

import com.example.entity.Account;

public interface AccountService {
	List<Account> getUser();
	
	Account findById(String id, String username);

	Account findById(String id);
	
	Account FindByUsernameActivated(String id);
	
}
