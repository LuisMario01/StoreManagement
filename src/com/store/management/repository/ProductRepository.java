package com.store.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Product findByProduct(String product);
	public List<Product> findAllByOrderByProductDesc();
	public List<Product> findAllByOrderByProductAsc();
}
