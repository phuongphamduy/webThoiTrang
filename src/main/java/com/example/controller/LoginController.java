package com.example.controller;



import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.service.MailService;


@Controller
public class LoginController {
	@Autowired
	AccountDAO dao;
	
	@Autowired
	MailService mail;
	
	@RequestMapping("/form")
	public String form(Model model) {
		return "login/login";
	}
	
	@RequestMapping("/formSignUp")
	public String signUp(Model model) {
		return "login/signup";
	}
	
//	@PostMapping("/formSignUp/create")
//	public String SignUp(Model model, @Validated @ModelAttribute("account") Account acc, BindingResult result) {
//				
//			if(!dao.findById(acc.getUsername()).isEmpty()) {
//				model.addAttribute("error", "Username đã tồn tại");	
//			}else {
//				dao.save(acc);
//				model.addAttribute("success", "Đăng ký thành công");
//				System.out.println("thành công");
//			}					
//		
//		model.addAttribute("accounts", dao.findAll());
//		return "login/signup";
//	}
	
	@PostMapping("/formSignUp")
	public String doSignUp(@Valid @ModelAttribute("account") Account acc, BindingResult result,
			@RequestParam("rePass") String rePass, Model model) {
		Optional<Account> account = dao.findById(acc.getUsername());
		if (account.isPresent()) {
			if (account.get().getActivated()) {
				model.addAttribute("isHave", true);
				return "signUp";
			}
		} else {
			model.addAttribute("isHave", false);
		}
		if (dao.findByEmail(acc.getEmail()) != null) {
			model.addAttribute("isEmail", true);
			return "signUp";
		} else {
			model.addAttribute("isEmail", false);
		}

		if (acc.getPassword().equals(rePass) && !result.hasErrors()) {
			String otp = RandomStringUtils.randomNumeric(6);
			acc.setOtp(otp);
			dao.save(acc);
			try {
				mail.send(acc.getEmail(), "Mã xác nhận Otp", "Không gửi mã xác nhận này cho bất kỳ ai: " + otp);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return "forward:/confirm";
		} else {
			model.addAttribute("re", true);
		}

		return "login/signup";

	}
	
	@RequestMapping("/confirm")
	public String xacnhan(@ModelAttribute("account") Account acc, Model model) {
		model.addAttribute("email1", acc.getEmail());
		return "login/xacnhan";
	}

	@PostMapping("/xacnhanMail")
	public String confirm(@RequestParam("otp") String otp, @RequestParam("email") String email) {
		Account acc = dao.findByEmail(email);
		if (acc.getOtp().equals(otp)) {
			acc.setOtp(null);
			acc.setActivated(true);
			dao.save(acc);
			return "redirect:/form/in";
		}
		return "login/xacnhan";
	}
	
	
}	

