package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/main")
	public String hello(Model model) {
		model.addAttribute("products", service.getProduct());
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
}
