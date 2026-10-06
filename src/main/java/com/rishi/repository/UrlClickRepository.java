package com.rishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishi.entity.UrlClick;

public interface UrlClickRepository extends JpaRepository<UrlClick, Long>{
	
	long countByUrlId(Long urlId);
	

}
