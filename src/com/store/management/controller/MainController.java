package com.store.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.domain.Product;
import com.store.management.domain.User;
import com.store.management.repository.ProductRepository;
/*
 * Class MainController - Contains methods 
 * */
@Controller
public class MainController {
	
	@Autowired
	private ProductRepository productRepository;
	
	//Showing all available products ascendantly sorted by product name
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	@ResponseBody
	public String showAllProductsSortedByName(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<Product> products = productRepository.findAllByOrderByProductAsc();
		String json = gson.toJson(products);
		return json;
	}
	
	//Showing all available products sorted by like amount
	@RequestMapping(value = "/products?sort=1", method = RequestMethod.GET)
	@ResponseBody
	public String showAllProductsSortedByLikes(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<Product> products = productRepository.findAllByOrderByProductDesc();
		String json = gson.toJson(products);
		return json;
	}
	
	//Show one product by name
	@RequestMapping(value = "/products/{product}", method = RequestMethod.GET)
	   @ResponseBody
	   public String showProductByName(@PathVariable("product")String productParam) {
	       Gson gson = new GsonBuilder().setPrettyPrinting().create();
	       Product product = productRepository.findByProduct(productParam);
	       String json = gson.toJson(product);
	       return json;
	       
	   }
	 
}
