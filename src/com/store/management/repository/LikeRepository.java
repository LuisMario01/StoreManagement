package com.store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.management.domain.Like;

public interface LikeRepository  extends JpaRepository<Like, Integer>{
	Like save(Like like);
}
