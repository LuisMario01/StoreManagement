package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.domain.ProductLog;

public interface ProductLogRepository extends JpaRepository<ProductLog, Integer>{
	ProductLog save(ProductLog productLog);
}
