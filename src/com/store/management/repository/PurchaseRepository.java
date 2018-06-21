package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.management.domain.Purchase;

public interface PurchaseRepository  extends JpaRepository<Purchase, Integer>{
	Purchase save(Purchase purchase);
}
