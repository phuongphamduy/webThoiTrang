package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	@RequestMapping("/main")
	public String hello() {
		return "product/main";
	}
	
	@RequestMapping("/hello1")
	public String hello1() {
		return "product/product_ofCate";
	}
	
	@RequestMapping("/detail")
	public String detail() {
		return "product/product_detail";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@RequestMapping("/signup")
	public String signup() {
		return "login/signup";
	}
}
