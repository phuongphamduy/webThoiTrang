package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name ="Products")
public class Product implements Serializable {
	@Id
	Long id;
	String name;
	String image;
	Double price;
	Integer discount;
	@ManyToOne
	@JoinColumn(name = "categoryid")
	Category category;
	
}
