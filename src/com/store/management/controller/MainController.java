package com.store.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.domain.Product;
import com.store.management.domain.User;
import com.store.management.repository.ProductRepository;
/*
 * Class MainController - Contains methods that receive requests and show data accordingly 
 * */
@Controller
public class MainController {
	
	@Autowired
	private ProductRepository productRepository;
	
	//Showing all available products
		@CrossOrigin
		@RequestMapping(value = {"/products"}, 
				method = RequestMethod.GET)
		@ResponseBody
		public String showAllProducts(){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			List<Product> products = productRepository.findAll();
			String json = gson.toJson(products);
			return json;
		}
		
		@RequestMapping(value = "/products/addProduct", method=RequestMethod.POST)
		@ResponseBody
		public String addProduct() {
			return "";
		}
	
	//Showing all available products ascendantly sorted by product name. 
	//5 elements per page.
	@RequestMapping(value = {"/products"}, 
			params = {"pageNumber"}, 
			method = RequestMethod.GET)
	@ResponseBody
	public String showAllProductsDefault(@RequestParam("pageNumber") String pageid){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		int pageNumber = Integer.parseInt(pageid);
		List<Product> products = productRepository.findAllByOrderByProductAsc(new PageRequest(pageNumber-1, 3));
		String json = gson.toJson(products);
		return json;
	}
	
	//Showing all available products ascendantly sorted by product name. 
	//5 elements per page.
	@RequestMapping(value = {"/products"}, 
				params = {"sortType", "pageNumber"}, 
				method = RequestMethod.GET)
	@ResponseBody
	public String showAllProductsSortedByName(@RequestParam("sortType") String sortType, @RequestParam("pageNumber") String pageid){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		int sort = Integer.parseInt(sortType);
		int pageNumber = Integer.parseInt(pageid);
		String json = "";
		//Sorting type, sort=0 for name sorting
		//				sort=1 for likes sorting
		if(sort==0) {
			List<Product> products = productRepository.findAllByOrderByProductAsc(new PageRequest(pageNumber-1, 3));
			json = gson.toJson(products);
		}
		else {
			//TODO: Do sorting using likes amount
			List<Product> products = productRepository.findAllByOrderByProductDesc(new PageRequest(pageNumber-1, 3));
			json = gson.toJson(products);
		}
		return json;
	}
	
	//Show one product searching it by name
	@RequestMapping(value = "/products/{product}", method = RequestMethod.GET)
	@ResponseBody
	public String showProductByName(@PathVariable("product")String productParam) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Product product = productRepository.findByProduct(productParam);
	    String json = gson.toJson(product);
	    return json;   
	}
	
	//Delete a product by id
	@Transactional
		@RequestMapping(value = "/products/{product}", method = RequestMethod.DELETE)
		@ResponseBody
		public boolean deleteProduct(@PathVariable("product")String idProduct) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			int product = Integer.parseInt(idProduct);
			productRepository.deleteByIdProduct(product);	
		    return true;   
		}
}
