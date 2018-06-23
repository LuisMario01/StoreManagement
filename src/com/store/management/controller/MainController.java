package com.store.management.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.domain.Product;
import com.store.management.domain.User;
import com.store.management.dto.BuyDTO;
import com.store.management.dto.LikeDTO;
import com.store.management.dto.Login;
import com.store.management.dto.ProductDTO;
import com.store.management.repository.UserRepository;
import com.store.management.service.ProductService;

/*
 * Class MainController - Contains methods that receive requests and show data accordingly 
 * */
@Controller
public class MainController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepository userRepository;
	

	//Login method
	//See readme to have instances of admin/user credentials
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody Login login){
		User user = userRepository.findByUsername(login.getUsername());
		if(user!=null) {
			if(login.getUsername().equals(user.getUsername()) && login.getPassword().equals(user.getPassword())) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(user);
				byte[] bytesEncoded = Base64.encodeBase64(json.getBytes());
				return new ResponseEntity<>(new String(bytesEncoded), HttpStatus.OK);
			}
			else {			
				return new ResponseEntity<>("Incorrect credentials", HttpStatus.BAD_REQUEST);
			}
		}
		else {
			return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Showing all available products
	@CrossOrigin
	@RequestMapping(value = {"/products"}, 
	method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showAllProducts(HttpServletRequest request){
		ResponseEntity<String> results = productService.showAllProducts();
		return results;
	}
	
	//Showing all available products ascendantly sorted by product name. 
	//5 elements per page.
	@RequestMapping(value = {"/products"}, 
			params = {"pageNumber"}, 
			method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showAllProductsDefault(@RequestParam("pageNumber") String pageid){
		ResponseEntity<String> results = productService.showAllProductsDefault(pageid);
		return results;
	}
	
	//Showing all available products ascendantly sorted by product name. 
	//5 elements per page.
	@RequestMapping(value = {"/products"}, 
				params = {"sortType", "pageNumber"}, 
				method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showAllProductsSortedByName(@RequestParam("sortType") String sortType, @RequestParam("pageNumber") String pageid){
		ResponseEntity<String> results = productService.showAllProductsSortedByName(sortType, pageid);
		return results;
	}
	
	//Show one product searching it by name
	@RequestMapping(value = "/products/{product}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> showProductByName(@PathVariable("product")String productParam) {
		ResponseEntity<String> results = productService.showProductByName(productParam);
		return results;   
	}
	
	//Save a product
	@Transactional
	@RequestMapping(value="/products/addProduct", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveProduct(HttpServletRequest request, @RequestBody ProductDTO productDTO) {	
		ResponseEntity<String> results = productService.saveProduct(request, productDTO);
		return results;
	}
	
	//Buying a product - Performed with a DTO object of the purchase.
	//Requires admin authorization
	@Transactional
	@RequestMapping(value="/products/buyProduct", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> buyProduct(HttpServletRequest request, @RequestBody BuyDTO purchaseDTO) {
		ResponseEntity<String> results = productService.buyProduct(request, purchaseDTO);
		return results;
	}
	
	//Liking a product
	@Transactional
	@RequestMapping(value="/products/likeProduct", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> likeProduct(HttpServletRequest request, @RequestBody LikeDTO likeDTO) {
		ResponseEntity<String> results = productService.likeProduct(request, likeDTO);
		return results;
	}
	
	//Updating product price.
	//Authorization required
	@Transactional
	@RequestMapping(value="/products/updateProduct", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> alterProductPrice(HttpServletRequest request, @RequestBody Product product) {
		ResponseEntity<String> results = productService.alterProductPrice(request, product);
		return results;
	}
	
	//Delete a product by id.
	//Only admins can delete a product.
	@Transactional
	@RequestMapping(value = "/products/{product}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteProduct(HttpServletRequest request, @PathVariable("product")String idProduct) {
		ResponseEntity<String> results = productService.deleteProduct(request, idProduct);
		return results;
	}
}
