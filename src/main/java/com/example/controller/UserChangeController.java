package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserChangeController {
	@GetMapping("/user/change")
	public String userChange() {
		return "login/UserChange";
	}
}
