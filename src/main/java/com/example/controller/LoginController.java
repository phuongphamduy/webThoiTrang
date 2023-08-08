package com.example.controller;


import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.service.MailService;
import com.example.service.MailerService;


@Controller
@RequestMapping("/login")
public class LoginController {
  
  @Autowired
	AccountDAO dao;

	@Autowired
	MailerService mail;

	
	@GetMapping("/signin/form")
	public String doGetForm() {
		return "login/signin";
	}
	
	@RequestMapping("/signin/success")
	public String success(Model model) {
		model.addAttribute("message1", "Đăng nhập thành công");
		return "forward:/login/signin/form";
	}
	
	@GetMapping("/signin/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "forward:/login/signin/form";
	}
	
	@RequestMapping("/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message1", "Đăng xuất thành công");
		return "forward:/login/signin/form";
	}
	
	@RequestMapping("/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy cập!");
		return "login/signin";
	}

	@RequestMapping("/formSignUp")
	public String signUp(Model model) {
		return "login/signup";

	}
	
	@PostMapping("/formSignUp/create")
	public String save(Model model, @Validated @ModelAttribute("account") Account acc,
			@RequestParam("rePass") String rePass, BindingResult result, Errors errors, HttpSession session) { 
		if(errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng sửa các lỗi sau:");			
		}
		else {
			if (!dao.findById(acc.getUsername()).isEmpty()) {
				model.addAttribute("message", "Vui lòng điền lại chính xác các thông tin sau:");
				model.addAttribute("error_user", "Username đã tồn tại!");	
			} else {
				if (acc.getPassword().equals(rePass) && !result.hasErrors()) {
					if(acc.getPhone().length()>=9 && acc.getPhone().length()<=15 && !result.hasErrors()) {	
					String otp = RandomStringUtils.randomNumeric(6);
					acc.setOtp(otp);
					try {
						mail.send(acc.getEmail(), "Mã xác nhận Otp", "Không gửi mã xác nhận này cho bất kỳ ai: " + otp);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					dao.save(acc);
					session.setAttribute("msg", "Đăng ký thành công");				
					return "forward:/login/confirm";
					} else {
						model.addAttribute("message", "Vui lòng điền lại chính xác các thông tin sau:");
						model.addAttribute("error_phone", "Số điện thoại không hợp lệ!");						
					}
				} else {
					model.addAttribute("message", "Vui lòng điền lại chính xác các thông tin sau:");
					model.addAttribute("error_repass", "Mật khẩu nhập lại không chính xác!");													
				}
			}
		}		
		model.addAttribute("accounts", dao.findAll());
		return "login/signup";
	}
	
	@RequestMapping("/confirm")
	public String xacnhan(@ModelAttribute("account") Account acc, Model model) {
		model.addAttribute("email1", acc.getEmail());
		return "login/xacnhan";
	}
	
	@PostMapping("/xacnhanMail")
	public String confirm(@RequestParam("email") String email, @RequestParam("otp") String otp) {
		Account acc = dao.findByEmail(email);
		if (acc.getOtp().equals(otp)) {
			acc.setOtp(null);
			acc.setActive(true);
			dao.save(acc);
			return "login/success";
		}
		return "login/xacnhan";
	}

//	@PostMapping("/formSignUp")
//	public String doSignUp(@Validated @ModelAttribute("account") Account acc, BindingResult result,
//			@RequestParam("rePass") String rePass, Model model, Errors errors) {
//		Optional<Account> account = dao.findById(acc.getUsername());
//		if (account.isPresent()) {
//			if (account.get().getActivated()) {
//				System.out.println("lỗi1");
//				model.addAttribute("isHave", true);
//				return "login/signup";
//			}
//		} else {
//			model.addAttribute("isHave", false);
//		}
//		if (dao.findByEmail(acc.getEmail()) != null) {
//			System.out.println("lỗi2");
//			model.addAttribute("isEmail", true);
//			return "signUp";
//		} else {
//			model.addAttribute("isEmail", false);
//		}
//
//		if (acc.getPassword().equals(rePass) && !result.hasErrors()) {
//			System.out.println("lỗi");
//			String otp = RandomStringUtils.randomNumeric(6);
//			acc.setOtp(otp);
//			dao.save(acc);
//			try {
//				mail.send(acc.getEmail(), "Mã xác nhận Otp", "Không gửi mã xác nhận này cho bất kỳ ai: " + otp);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//			return "forward:/confirm";
//		} else {
//			model.addAttribute("re", true);
//		}
//
//		return "login/signup";
//
//	}
	

	
	
	

	

}
