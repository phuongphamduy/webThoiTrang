package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
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

    @RequestMapping("/admin/order/detail")
    public String orderDetail(Model model) {
        return "admin/order/order-detail";
    }

    @RequestMapping("/admin/product/formproduct")
    public String formProduct(Model model) {
        return "admin/product/form-product";
    }

    @RequestMapping("/admin/user/listuser")
    public String listUser(Model model) {
        return "admin/user/list-user";
    }
}
