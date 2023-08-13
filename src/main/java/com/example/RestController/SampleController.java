package com.example.RestController;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("*")
//@RequestMapping("/")
public class SampleController {
	@GetMapping

	public String welcome() {
		return "welcom to google";
	}
	
	@GetMapping("/user")
	public Principal user(Principal princial) {
		System.out.println("username: "+ princial.getName());
		return princial;
	}
} 
