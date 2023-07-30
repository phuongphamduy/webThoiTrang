package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	
	@RequestMapping("/cart")
	public String cart(Model model) {
		return "cart/cart";
	}
	
	@RequestMapping("/cart/pay")
	public String payForm() {
		return "cart/payForm";
	}
	
	@RequestMapping("/cart/success")
	public String success() {
		return "cart/paySuccess";
	}
}
