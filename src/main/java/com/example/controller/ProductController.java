package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Product;
import com.example.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/main")
	public String main(Model model) {
		model.addAttribute("products", service.getProduct());
		return "product/main";
	}
	
	@RequestMapping("/category/{id}")
	public String productOfCategory(@PathVariable("id") Integer id,Model model) {
		return "product/product_ofCate";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		Product p = service.findById(id);
		List<Product> products = p.getCategory().getProducts();
		model.addAttribute("product", p);
		model.addAttribute("products", products);
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
