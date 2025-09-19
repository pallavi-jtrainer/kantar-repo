package com.kantar.springbootmongodemo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import com.kantar.springbootmongodemo.model.Product;
import com.mongodb.client.result.UpdateResult;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
	
	Product findByProductName(String name);

	List<Product> findByPriceGreaterThan(double price);
	
	@Query("{'productName': ?0, 'price': {'$lt': ?1}}")
	Product findByProductNameAndPriceLsssThan(String name, double price);
	
	@Query("{'productName': ?0}")
	@Update("{'$set': {'price': ?1}}")
//	@Query(value="{'productName': ?0, '$set': {"
//			+ "'price': $1}}")
//	@Update("{'productName': ?0},{'$set':{'price': ?1}}")
//	@Query(value = "{ 'productName' : ?0 }", update = "{ '$set' : { 'price' : ?1 } }")
	int updatePriceByProductName(String name, double price);
}
