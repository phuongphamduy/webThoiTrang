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
	String username;
	String password;
	String email;
	String fullname;
	Boolean gender;
	@Temporal(TemporalType.DATE)
	Date birthday;
	String address;
	@OneToMany(mappedBy = "account")
	List<Authority> authorities;
	@OneToMany(mappedBy = "account")
	List<Order> orders;
}
