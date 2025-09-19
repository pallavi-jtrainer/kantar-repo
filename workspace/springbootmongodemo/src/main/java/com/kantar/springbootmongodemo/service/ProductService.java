package com.kantar.springbootmongodemo.service;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kantar.springbootmongodemo.model.Product;
import com.kantar.springbootmongodemo.repository.ProductRepository;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repo;
	
//	@Autowired
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}
	
	public Product addNewProduct(Product p) {
		return repo.save(p);
	}
	
	public Product getProductDetails(String id) {
		return repo.findById(id).get();
	}
	
	public Product getProductByName(String name) {
		return repo.findByProductName(name);
	}
	
	public String updateProductPrice(String name, double price) {
		String str = "Unable to update";
		
		int res = repo.updatePriceByProductName(name, price);
		
		if(res > 0) {
			str= "Product updated";
		}
		
		return str;
	}
	
	public List<Product> listAll() {
		return repo.findAll();
	}
}
