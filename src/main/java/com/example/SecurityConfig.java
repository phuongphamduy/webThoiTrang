package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers("/main", "/form", "/formSignUp", "/loadForgotPassword",
            		"/loadResetPassword/{username}", "/forgotPassword", "/changePassword").permitAll() // Cho phép tất cả mọi người truy cập vào địa chỉ này
            .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
            .and()
        .formLogin() // Cho phép người dùng xác thực bằng form login
            .defaultSuccessUrl("/main")
            .permitAll() // Tất cả đều được truy cập vào địa chỉ này
            .and()
        .logout() // Cho phép logout
            .permitAll();
	}
	
	
}
