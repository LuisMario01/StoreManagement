package com.store.management.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.management.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	//Saving
	public Product save(Product product);
	
	//Searching
	public Product findOne(Integer idProduct);
	public boolean exists(Integer id);
	public Product findByProduct(String product);
	
	
	//Deleting
	public void deleteByIdProduct(Integer id);
	
	//Listing methods
	public List<Product> findAll();
	
	@Query(value="select p.id_product, p.product, p.stock, p.price, count(l.id_like) likes from store.product p" + 
			"FULL JOIN store.likes l ON p.id_product = l.id_product GROUP BY (p.id_product) ORDER by likes", nativeQuery=true)
	public List<Object> findAllSortedByLikes();
	
	public List<Product> findAllByOrderByProductDesc();
	public List<Product> findAllByOrderByProductAsc();
	
	public List<Product> findAllByOrderByProductDesc(Pageable page);
	public List<Product> findAllByOrderByProductAsc(Pageable page);
}
