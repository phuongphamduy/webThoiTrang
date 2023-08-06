package com.example;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.entity.Account;
import com.example.service.AccountService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
//	Cung cấp nguồn dữ liệu
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.findById(username);
				
				// Tạo UserDetails từ Account
				String password = pe.encode(user.getPassword());
				String[] roles = user.getAuthorities().stream()
					.map(er -> er.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username)
						.password(password)
						.roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found!");
			}
		});
	}
	
//	Phân quyền sử dụng
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
        .antMatchers("/cart/**").authenticated()
        .antMatchers("/admin/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/rest/authorities").hasRole("ADMIN")
        .anyRequest().permitAll();
		
		http.formLogin()
		.loginPage("/login/signin") // khai báo địa chỉ form (get)
		.loginProcessingUrl("/login/signin") // địa chỉ submit
		.defaultSuccessUrl("/login/signin/success", false) // đăng nhập thành công thì chuyển tới địa chỉ
		.failureUrl("/login/signin/error"); // đăng nhập thất bại thì chuyển tới địa chỉ
		
		http.rememberMe()
		.tokenValiditySeconds(86400);
		
		http.exceptionHandling()
		.accessDeniedPage("/login/access/denied");
		
		http.logout()
		.logoutUrl("/signin/logoff") // địa chỉ logoff
		.logoutSuccessUrl("/login/logoff/success"); // logoff thành công thì chuyển về địa chỉ
	}
	
//	Cơ chế mã hoá mật khẩu
	@Bean
	public static BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	Cho phép truy xuất REST API từ bên ngoài (domain khác)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
	
	
}
