package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.service.OrderService;

@Controller
public class CartController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	OrderService service;

	@RequestMapping("/cart")
	public String cart(Model model) {
		return "cart/cart";
	}

	@RequestMapping("/cart/pay")
	public String payForm(Model model) {
		return "cart/payForm";
	}

	@RequestMapping("/cart/success/{id}")
	public String success(@PathVariable("id") Integer id, Model model) {
		Order order = service.findById(id);
		List<OrderDetail> list = order.getOrderDetails();
		double sum = 0;
		for(OrderDetail d : list) {
			sum+=d.getPrice() * d.getQuantity();
		}
		model.addAttribute("sum", sum);
		model.addAttribute("list", list);
		model.addAttribute("order", order);
		return "cart/paySuccess";
	}
	
	@RequestMapping("/checkorder")
	public String index(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", service.findByUsername(username));
		return "product/checkorder";
	}
	
	@RequestMapping("/orderdetail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		Order order = service.findById(id);
		List<OrderDetail> list = order.getOrderDetails();
		double sum = 0;
		for(OrderDetail d : list) {
			sum+=d.getPrice() * d.getQuantity();
		}
		model.addAttribute("sum", sum);
		model.addAttribute("list", list);
		model.addAttribute("order", order);
		return "product/order_detail";
	}
}
