package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
	@Id
	@NotBlank(message="Tên đăng nhập không được để trống")
	String username;
	@NotBlank(message="Mật khẩu không được để trống")
	String password;
	@NotBlank(message="Email không được để trống")
	@Email(message="Email không đúng định dạng")
	String email;
	@NotBlank(message="Họ tên không được để trống")
	String fullname;
	Boolean gender;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date birthday;
	String address;
	Boolean activated = false;
	Boolean admin = false;
	String otp;
	@OneToMany(mappedBy = "account")
	List<Authority> authorities;
	@OneToMany(mappedBy = "account")
	List<Order> orders;
}
