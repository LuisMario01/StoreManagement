package com.store.management.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	//Saving methods
	public Product save(Product product);
	
	//Searching
	public boolean exists(Integer id);
	public Product findByProduct(String product);
	
	
	//Deleting
	public void deleteByIdProduct(Integer id);
	
	//Listing methods
	public List<Product> findAll();
	
	public List<Product> findAllByOrderByProductDesc();
	public List<Product> findAllByOrderByProductAsc();
	
	public List<Product> findAllByOrderByProductDesc(Pageable page);
	public List<Product> findAllByOrderByProductAsc(Pageable page);
}
