package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Account;
import com.fasterxml.jackson.databind.JsonNode;


public interface AccountDAO extends JpaRepository<Account, String> {

	Account findByEmail(String email);
	
	Account findByEmailAndPhone (String email, String phone);
	
	Account findByUsernameAndActive(String username, Boolean active);
}
