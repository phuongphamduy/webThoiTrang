package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.AccountDAO;
import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	@Autowired
	CategoryService accountService;

	@RequestMapping("/main")
	public String main(Model model, @RequestParam(name = "NProduct", required = false) Optional<Integer> no, @RequestParam(name  = "page", required = false) Optional<Integer> pageN )  {
		Pageable pageable = PageRequest.of(pageN.orElse(0), no.orElse(10));
		Page<Product> page = service.getProductPage(pageable);
		List<Integer> pageList = new ArrayList();
		String href = "";
		if(no.isPresent()) {
			href = href + "&NProduct=" + no.orElse(10);
		}
		model.addAttribute("href", href);
		int numberPage = page.getTotalPages();
		for(int i = 1; i <= numberPage;i++) {
			pageList.add(i);
		}
		model.addAttribute("pageList", pageList);
		model.addAttribute("products", page);
		return "product/main";
	}

	@RequestMapping("/category/{id}")
	public String productOfCategory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("products", accountService.ProductOfCates(id));
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
}
