package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.AccountDAO;
import com.example.entity.Account;

@Controller
public class ForgotPasswordController {
	@Autowired
	AccountDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/loadForgotPassword")
	public String loadForgotPassword() {
		return "login/forgot_password";
	}
	
	@GetMapping("/loadResetPassword/{username}")
	public String loadResetPassword(@PathVariable String username, Model model ) {
		model.addAttribute("username", username);
		return "login/change_password";
	}
	
	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String email, @RequestParam String phone, HttpSession session) {
		Account user = dao.findByEmailAndPhone(email, phone);
		
		if(user!=null) {
			return "redirect:/loadResetPassword/" + user.getUsername();
		}else {
			session.setAttribute("msg", "Không tồn tại email và số điện thoại");
			return "login/forgot_password";
		}
		
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam String password, @RequestParam String username, HttpSession session) {
		Account user = dao.findById(username).get();
		String encryptPsw= passwordEncoder.encode(password);
		user.setPassword(encryptPsw);
		Account updateUser = dao.save(user);
		
		if(updateUser!=null) {
			session.setAttribute("msg", "Đổi mật khẩu thành công");			
		}
		return "";
	}
	
	
}
