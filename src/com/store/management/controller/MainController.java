package com.store.management.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.domain.Like;
import com.store.management.domain.Product;
import com.store.management.domain.ProductLog;
import com.store.management.domain.Purchase;
import com.store.management.domain.User;
import com.store.management.dto.BuyDTO;
import com.store.management.dto.LikeDTO;
import com.store.management.dto.Login;
import com.store.management.dto.ProductDTO;
import com.store.management.dto.ProductLikes;
import com.store.management.repository.LikeRepository;
import com.store.management.repository.ProductLogRepository;
import com.store.management.repository.ProductRepository;
import com.store.management.repository.PurchaseRepository;
import com.store.management.repository.UserRepository;
import com.store.management.service.ProductService;
import com.store.management.util.ProductLogUtil;
import com.store.management.util.PurchaseUtil;
/*
 * Class MainController - Contains methods that receive requests and show data accordingly 
 * */
@Controller
public class MainController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private ProductLogRepository productLogRepository;
	
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
		if(sort==0) {//Sorting type, sort=0 for name sorting
			List<Product> products = productRepository.findAllByOrderByProductAsc(new PageRequest(pageNumber-1, 3));
			json = gson.toJson(products);
		}
		else if(sort==1) { //sort=1 for likes sorting
			List<Object> products = productRepository.findAllSortedByLikes();
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
	public String saveProduct(HttpServletRequest request, @RequestBody ProductDTO productDTO) {	
		try {
			byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
			System.out.println(new String(valueDecoded));
			Gson usrGson = new Gson();
			User user= usrGson.fromJson(new String(valueDecoded), User.class);
			
			if(user.getRole()==1) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Product savingProduct = new Product();
				savingProduct.setProduct(productDTO.getProduct());
				savingProduct.setPrice(productDTO.getPrice());
				savingProduct.setStock(productDTO.getStock());
				Product result = new Product();
				result = productRepository.save(savingProduct);
				return gson.toJson(result); //Shows just-saved product*/
			}
			else {
				return "Not allowed here";
			}		
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	//Buying a product - Performed with a DTO object of the purchase.
	@Transactional
	@RequestMapping(value="/products/buyProduct", method=RequestMethod.PUT)
	@ResponseBody
	public String buyProduct(HttpServletRequest request, @RequestBody BuyDTO purchaseDTO) {
		try {
			byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
			Gson usrGson = new Gson();
			User user= usrGson.fromJson(new String(valueDecoded), User.class);
			if(user.getRole()==1 || user.getRole()==2) {
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
			else {
				return "Not allowed";
			}
		}
		catch(Exception e) {
			return "Error when buying a product";
		}
	}
	
	//Liking a product
	@Transactional
	@RequestMapping(value="/products/likeProduct", method=RequestMethod.POST)
	@ResponseBody
	public String likeProduct(HttpServletRequest request, @RequestBody LikeDTO likeDTO) {
		try {
			byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
			Gson usrGson = new Gson();
			User user= usrGson.fromJson(new String(valueDecoded), User.class);
			if(user.getRole()==1 || user.getRole()==2) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Like newLike = new Like();
				newLike.setIdProduct(likeDTO.getIdProduct());
				newLike.setUser(user.getIdUser());
				newLike = likeRepository.save(newLike);
				if(newLike!=null) {
					return gson.toJson(newLike);
				}
				else {
					return "Not allowed";
				}
			}
			else {
				return "Not allowed";
			}
		}
		catch(Exception e) {
			return "Not allowed";
		}
	}
	
	//Updating product price.
	@Transactional
	@RequestMapping(value="/products/updateProduct", method=RequestMethod.PUT)
	@ResponseBody
	public String alterProductPrice(HttpServletRequest request, @RequestBody Product product) {
		if(request.getHeader("token")!=null) {
			byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
			Gson usrGson = new Gson();
			User user= usrGson.fromJson(new String(valueDecoded), User.class);
			if(user.getRole()==1) {
				try {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					Product buyingProduct = new Product();
					buyingProduct = productRepository.findOne(product.getIdProduct());
					
					if(buyingProduct!=null)
					{
						Double previousPrice = buyingProduct.getPrice();
						buyingProduct = productRepository.save(product);
						if(buyingProduct!=null) {
							ProductLog productLog = ProductLogUtil.createProductLog(buyingProduct, previousPrice);
							productLog = productLogRepository.save(productLog);
							if(productLog!=null) return gson.toJson(buyingProduct);
							else return "Log no guardado";
						}
						else return "Producto no guardado";
					}
					else return "Producto no encontrado";
				}
				catch(Exception e) {
					return null;
				}
			}
			else {
				return "Not allowed";
			}
		}
		else return "Not allowed";
	}
	
	//Delete a product by id.
	//Only admins can delete a product.
	@Transactional
	@RequestMapping(value = "/products/{product}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteProduct(HttpServletRequest request, @PathVariable("product")String idProduct) {
		byte[] valueDecoded = Base64.decodeBase64(request.getHeader("token"));
		Gson usrGson = new Gson();
		User user= usrGson.fromJson(new String(valueDecoded), User.class);
		
		if(user.getRole()==1) {
			try {
			int product = Integer.parseInt(idProduct);
			productRepository.deleteByIdProduct(product);	
		    return true;
		    }
			catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		else {
			return false;
		}
	}
}
