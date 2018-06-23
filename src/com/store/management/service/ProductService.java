package com.store.management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.domain.Product;
import com.store.management.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<String> showAllProducts(){
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<Product> products = productRepository.findAllByOrderByProductAsc();
			String json = gson.toJson(products);
			return new ResponseEntity<>(json, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Error when loading results", HttpStatus.NO_CONTENT);			
		}
	}
}
