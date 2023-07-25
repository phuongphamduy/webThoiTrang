package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckOrderController {
    @RequestMapping("/check_order")
    public String index(Model model) {

        return "/product/check_order";
    }
}
