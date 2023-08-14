package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.service.CategoryService;
import com.example.service.OrderService;
import com.example.service.ProductService;

@Controller
public class AdminController {
    @Autowired
    CategoryService accountService;
    
    @Autowired
    OrderService service;
    
    @Autowired
    ProductService productService;

    @RequestMapping("/admin")
    public String index(Model model) {

        return "admin/index";
    }

    @RequestMapping("/admin/form/danhmuc")
    public String danhmuc(Model model) {
        return "admin/danhmuc/danhmuc";
    }

    @RequestMapping("/admin/order")
    public String order(Model model) {
        return "admin/order/order";
    }

    @RequestMapping("/admin/order/detail/{id}")
    public String orderDetail(Model model, @PathVariable("id") Integer id) {
    	List<OrderDetail> items = service.findById(id).getOrderDetails();
    	model.addAttribute("list", items);
        return "admin/order/order-detail";
    }

    @RequestMapping("/admin/product/formproduct")
    public String formProduct(Model model) {
        model.addAttribute("products", productService.getProduct());

        return "admin/product/form-product";
    }

    @RequestMapping("/admin/user/listuser")
    public String listUser(Model model) {
        return "admin/user/list-user";
    }
}
