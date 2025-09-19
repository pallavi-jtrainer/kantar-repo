package com.kantar.springbootmongodemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private String _id;
	private long productId;
	private String productName;
	private double price;
	
}
