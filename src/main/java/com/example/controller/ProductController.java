package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/main")
	public String main(Model model, @RequestParam(name = "NProduct", required = false) Optional<Integer> no, @RequestParam(name  = "page", required = false) Optional<Integer> pageN )  {
		Pageable pageable = PageRequest.of(pageN.orElse(0), no.orElse(10));
		Page<Product> page = service.getProductPage(pageable);
		List<Integer> pageList = new ArrayList();
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
	
//	@PreAuthorize("hasRole('ADMIN')") //demo4
	@RequestMapping("/admin/main")
	public String admins(Model model) {
		if (!request.isUserInRole("ADMIN")) {
			return "redirect:/login/access/denied";
		}
		model.addAttribute("message", "Hello Administrator");
		return "admin/index";
	}
	
////	@PreAuthorize("hasAnyRole('ADMIN','USER')") //demo4
//	@RequestMapping("/users")
//	public String users(Model model) {
//		if (!(request.isUserInRole("ADMIN") || request.isUserInRole("USER "))) {
//			return "redirect:/auth/access/denied";
//		}
//		model.addAttribute("message", "Hello Staff");
//		return "home/index";
//	}
//	
////	@PreAuthorize("isAuthenticated()") //demo4
//	@RequestMapping("/authenticated")
//	public String authenticated(Model model) {
//		if (request.getRemoteUser() == null) {
//			return "redirect:/auth/login/form";
//		}
//		model.addAttribute("message", "Hello Authenticated user");
//		return "home/index";
//	}
//	
//	@RequestMapping("/thymeleaf1")
//	public String thymeleaf1(Model model) {
//		model.addAttribute("message", "Thymeleaf - Without Extras");
//		return "home/thymeleaf1";
//	}
//	
//	@RequestMapping("/thymeleaf2")
//	public String thymeleaf2(Model model) {
//		model.addAttribute("message", "Thymeleaf - With Extras");
//		return "home/thymeleaf2";
//	}
}
