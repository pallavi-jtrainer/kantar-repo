package com.kantar.springbootmongodemo.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kantar.springbootmongodemo.model.Product;
import com.kantar.springbootmongodemo.repository.ProductRepository;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repo;
	
	private final MongoTemplate mongoTemplate;
	
	
//	@Autowired
	public ProductService(ProductRepository repo, MongoTemplate mongoTemplate) {
		this.repo = repo;
		this.mongoTemplate = mongoTemplate;
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
	
//	public String updateProductPrice(String name, double price) {
//		Query query = new Query(Criteria.where("productName")
//				.is(name));
//		Update update = new Update().set("price", price);
//		mongoTemplate.updateFirst(query, update, Product.class);
//		return "Tried update";
//	}
	
	public String updateProductPrice(String name, double price) {
		String str = "Unable to update";
		
//		Product p = repo.findByProductName(name);
//		
//		p.setPrice(price);
//		p = repo.save(p);
		int res = repo.updatePriceByProductName(name, price);
		
		if(res > 0) {
			str= "Product updated";
		}
		
//		if(p != null) {
//			str = "Product updated";
//		}
		
		return str;
	}
	
	public List<Product> listAll() {
		return repo.findAll();
	}
}
