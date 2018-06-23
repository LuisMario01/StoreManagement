package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.management.domain.ProductLog;

@Repository
public interface ProductLogRepository extends JpaRepository<ProductLog, Integer>{
	ProductLog save(ProductLog productLog);
}
