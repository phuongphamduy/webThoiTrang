package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
        .authorizeRequests()
            .antMatchers("/main", "/form", "/formSignUp", "/loadForgotPassword",
            		"/loadResetPassword/{username}", "/forgotPassword", "/changePassword", "/css/**", "/js/**", "/images/**", "/**").permitAll() // Cho phép tất cả mọi người truy cập vào địa chỉ này
            .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
            .and()
        .formLogin() // Cho phép người dùng xác thực bằng form login
            .defaultSuccessUrl("/main")
            .permitAll() // Tất cả đều được truy cập vào địa chỉ này
            .and()
        .logout() // Cho phép logout
            .permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
	
	
}
