package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder pe;

	// Mã hóa mật khẩu
	@Bean
	public static BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Quản lý dữ liệu người sử dụng
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
			.withUser("user1").password(pe.encode("123")).roles("GUEST").and()
			.withUser("user2").password(pe.encode("123")).roles("USER","GUEST").and()
			.withUser("user3").password(pe.encode("123")).roles("USER","ADMIN","GUEST");
		}
		
		// Phân quyền sử dụng and hình thức đăng nhập
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// CSRF,CORS
			http.csrf().disable().cors().disable();
			
			// Phân quyền sử dụng
//			http.authorizeHttpRequests() //demo2
//			.antMatchers("/home/index","/auth/login/**").permitAll() // "home/index" cho phép sử dụng
//			.anyRequest().authenticated(); // all page còn lại thì bắt buộc đăng nhập
			
			// Phân quyền sử dụng
//			http.authorizeHttpRequests() //demo3
//			.antMatchers("/home/admins").hasRole("ADMIN")
//			.antMatchers("/home/users").hasAnyRole("ADMIN","USER")
//			.antMatchers("/home/authenticated").authenticated()
//			.anyRequest().permitAll();
			
			// Phân quyền sử dụng
			http.authorizeRequests().anyRequest().permitAll();
			
			// Lỗi truy cập không đúng vai trò
			http.exceptionHandling()
			.accessDeniedPage("/signin/access/denied");
			
			// Giao diện đăng nhập
//			http.httpBasic(); // bài demo1
			http.formLogin()
			.loginPage("/login/signin") // khai báo địa chỉ form (get)
			.loginProcessingUrl("/login/signin") // địa chỉ submit
			.defaultSuccessUrl("/login/signin/success", false) // đăng nhập thành công thì chuyển tới địa chỉ
			.failureUrl("/login/signin/error") // đăng nhập thất bại thì chuyển tới địa chỉ
			.usernameParameter("username")	// lấy param user
			.passwordParameter("password"); // lấy param pass
			
			http.rememberMe()
			.rememberMeParameter("remember"); // param remember
			
			// Đăng xuất
			http.logout()
			.logoutUrl("/signin/logoff") // địa chỉ logoff
			.logoutSuccessUrl("/signin/logoff/seccess"); // logoff thành công thì chuyển về địa chỉ
		}
		

}
