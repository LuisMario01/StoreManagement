package com.store.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.domain.Like;
import com.store.management.domain.Product;
import com.store.management.domain.Purchase;
import com.store.management.domain.User;
import com.store.management.dto.BuyDTO;
import com.store.management.dto.LikeDTO;
import com.store.management.dto.Login;
import com.store.management.dto.ProductDTO;
import com.store.management.repository.LikeRepository;
import com.store.management.repository.ProductRepository;
import com.store.management.repository.PurchaseRepository;
import com.store.management.repository.UserRepository;
import com.store.management.util.PurchaseUtil;
/*
 * Class MainController - Contains methods that receive requests and show data accordingly 
 * */
@Controller
public class MainController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	//Login method
	//See readme to have instances of admin/user credentials
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody Login login) {
		User user = userRepository.findByUsername(login.getUsername());
		if(user!=null) {
			if(login.getUsername().equals(user.getUsername()) && login.getPassword().equals(user.getPassword())) {
				//TODO Token creation
				return new ResponseEntity<>("Ok", HttpStatus.OK);
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
	public String showAllProducts(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<Product> products = productRepository.findAll();
		String json = gson.toJson(products);
		return json;
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
	
	//Save a product
	@Transactional
	@RequestMapping(value="/products/addProduct", method=RequestMethod.POST)
	@ResponseBody
	public String saveProduct(@RequestBody ProductDTO productDTO) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Product savingProduct = new Product();
			savingProduct.setProduct(productDTO.getProduct());
			savingProduct.setPrice(productDTO.getPrice());
			savingProduct.setStock(productDTO.getStock());
			Product result = new Product();
			result = productRepository.save(savingProduct);
			return gson.toJson(result); //Shows just-saved product
		}
		catch(Exception e) {
			return null;
		}
	}
	
	//Buying a product - Performed with a DTO object of the purchase.
	@Transactional
	@RequestMapping(value="/products/buyProduct", method=RequestMethod.PUT)
	@ResponseBody
	public String buyProduct(@RequestBody BuyDTO purchaseDTO) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Product buyingProduct = new Product();
			Purchase newPurchase = new Purchase();
			buyingProduct = productRepository.findOne(purchaseDTO.getIdProduct());
			if(buyingProduct!=null) {
				int newStock = buyingProduct.getStock()-purchaseDTO.getAmount(); //Decreasing stock w/purchase
				if(newStock>=0) {
					buyingProduct.setStock(newStock); //Setting new stock
					buyingProduct = productRepository.save(buyingProduct);
					newPurchase = purchaseRepository.save(PurchaseUtil.createPurchase(purchaseDTO));
					if(newPurchase!=null && buyingProduct != null)
						return gson.toJson(buyingProduct);
					else
						return "Error transaccion no completada";
				}
				else {
					return "Error: no hay suficientes en stock";
				}
			}
			else return "Producto no existente";
		}
		catch(Exception e) {
			return null;
		}
	}
	
	//Liking a product
	@Transactional
	@RequestMapping(value="/products/likeProduct", method=RequestMethod.POST)
	@ResponseBody
	public String likeProduct(@RequestBody LikeDTO likeDTO) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Like newLike = new Like();
			newLike.setIdProduct(likeDTO.getIdProduct());
			newLike.setUser(likeDTO.getIdUser());
			newLike = likeRepository.save(newLike);
			if(newLike!=null) {
				return gson.toJson(newLike);
			}
			else {
				return null;
			}
		}
		catch(Exception e) {
			return null;
		}
	}
	
	//Updating product price.
	@Transactional
	@RequestMapping(value="/products/updateProduct", method=RequestMethod.PUT)
	@ResponseBody
	public String alterProductPrice(@RequestBody Product product) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Product buyingProduct = new Product();
			buyingProduct = productRepository.save(product);
			if(buyingProduct!=null)
			{
				return gson.toJson(buyingProduct);
			}
			else return "Producto no guardado";
		}
		catch(Exception e) {
			return null;
		}
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
