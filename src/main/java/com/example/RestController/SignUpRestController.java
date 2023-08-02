package com.example.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.service.AccountService;


@CrossOrigin("*")
@RestController
public class SignUpRestController {
	@Autowired
	AccountDAO dao;
	
	@Autowired
	AccountService accountService;
	
	@PostMapping("/rest/accounts/create")
	public Account create(@RequestBody Account account) {
		return accountService.create(account);
	}
	
//	@PostMapping("/rest/accounts/create")
//	public ResponseEntity<Account> post(@RequestBody Account account){
//		if(dao.existsById(account.getUsername())) {
//			return ResponseEntity.badRequest().build();
//		}
//		dao.save(account);
//		return ResponseEntity.ok(account);
//	}
	
	
	
	
	
}
