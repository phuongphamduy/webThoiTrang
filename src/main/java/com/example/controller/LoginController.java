package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
//	@RequestMapping("/form")
//	public String form(Model model) {
//		return "login/login";
//	}
	
//	@RequestMapping("/formSignUp")
//	public String signUp(Model model) {
//		return "login/signup";
//	}
	
	@GetMapping("/signin/form")
	public String doGetForm() {
		return "login/signin";
	}
	
	@RequestMapping("/signin/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "forward:/login/signin/form";
	}
	
	@GetMapping("/signin/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "forward:/login/signin/form";
	}
	
	@RequestMapping("/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "forward:/login/signin/form";
	}
	
	@RequestMapping("/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy cập!");
		return "login/signin";
	}
}
