package com.example.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	ProductService service;
	
	@GetMapping()
	public List<Product> getListProduct() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Product getProduct(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return service.create(product);
	}

	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return service.update(product);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
}
