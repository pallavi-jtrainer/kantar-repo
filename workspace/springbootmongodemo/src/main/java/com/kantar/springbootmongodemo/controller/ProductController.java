package com.kantar.springbootmongodemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kantar.springbootmongodemo.model.Product;
import com.kantar.springbootmongodemo.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService service;
	
	@PostMapping
	public Product addNewProduct(@RequestBody Product p) {
		return service.addNewProduct(p);
	}
	
	public List<Product> listAll() {
		return service.listAll();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable String id) {
		return service.getProductDetails(id);
	}
	
	@PatchMapping("/update/{name}")
	public String updateProduct(@PathVariable String name, @RequestParam double price) {
		return service.updateProductPrice(name, price);
	}
}
