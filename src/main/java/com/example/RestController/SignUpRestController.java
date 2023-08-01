package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.entity.Product;
import com.example.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class SignUpRestController {
	@Autowired
	AccountDAO dao;
	
	@Autowired
	ProductService productService;
	
//	@PostMapping
//	public Product create(@RequestBody Product product) {
//		return productService.create(product);
//	}
	
	@PostMapping("/rest/accounts/create")
	public ResponseEntity<Account> post(@RequestBody Account account){
		if(dao.existsById(account.getUsername())) {
			return ResponseEntity.badRequest().build();
		}
		dao.save(account);
		return ResponseEntity.ok(account);
	}
	
	
	
}
