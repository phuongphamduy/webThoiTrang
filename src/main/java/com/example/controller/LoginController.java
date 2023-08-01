package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.AccountDAO;
import com.example.entity.Account;


@Controller
public class LoginController {
	@Autowired
	AccountDAO dao;
	
	@RequestMapping("/form")
	public String form(Model model) {
		return "login/login";
	}
	
	@RequestMapping("/formSignUp")
	public String signUp(Model model) {
		return "login/signup";
	}
	
//	@PostMapping("/formSignUp/create")
//	public String SignUp(Model model, @Validated @ModelAttribute("user") Account account, BindingResult result) {
//		
//			if(!dao.findById(account.getUsername()).isEmpty()) {
//				model.addAttribute("error", "Username đã tồn tại");				
//			}else {
//				dao.save(account);
//				model.addAttribute("success", "Đăng ký thành công");
//				System.out.println("thành công");
//			}					
//		
//		model.addAttribute("users", dao.findAll());
//		return "login/signup";
//	}
	
	
}	

