package com.store.management.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.management.domain.Product;
import com.store.management.domain.User;
import com.store.management.dto.ProductDTO;
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
	
	public ResponseEntity<String> showAllProductsDefault(String pageid){
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			int pageNumber = Integer.parseInt(pageid);
			List<Product> products = productRepository.findAllByOrderByProductAsc(new PageRequest(pageNumber-1, 3));
			String json = gson.toJson(products);
			return new ResponseEntity<>(json, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Error when loading results", HttpStatus.NO_CONTENT);
		}
	}
	
	public ResponseEntity<String> showAllProductsSortedByName(String sortType, String pageid){
		try {
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
			return new ResponseEntity<>(json, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Error when loading results", HttpStatus.NO_CONTENT);
		}
	}
	
	public ResponseEntity<String> showProductByName(String productParam) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Product product = productRepository.findByProduct(productParam);
		    String json = gson.toJson(product);
		    return new ResponseEntity<>(json, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> saveProduct(HttpServletRequest request, ProductDTO productDTO) {	
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
				return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK); //Shows just-saved product*/
			}
			else {
				return new ResponseEntity<>("Not allowed", HttpStatus.UNAUTHORIZED);
			}		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Product not saved", HttpStatus.BAD_REQUEST);
		}
	}
	
}
