package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.management.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
}
