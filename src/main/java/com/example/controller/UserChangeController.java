package com.example.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Account;
import com.example.service.AccountService;

@Controller
public class UserChangeController {
	@Autowired
	AccountService accService;
	@RequestMapping("/user/form")
	public String userForm(Principal principal, Model model) {
		Account account = accService.findById(principal.getName()); 
		model.addAttribute("acc",account);
		return "login/UserChange";
	}
	
	@RequestMapping("/user/change")
	public String userChange(Principal principal, Model model,@RequestParam("fullname") String fullname,@RequestParam("address") String address,@RequestParam("phone") String phone) {
		Account account = accService.findById(principal.getName());
		account.setFullname(fullname);
		account.setAddress(address);
		account.setPhone(phone);
		accService.update(account);
		model.addAttribute("acc",account);
		return "login/UserChange";
	}
	
}
