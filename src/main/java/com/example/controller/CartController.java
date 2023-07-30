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
	
	@RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") Long id) {
		
		return "cart/cart";
	}
}
