package com.rishi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishi.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long>{
	
	Optional<Url> findByShortUrl(String shortUrl);
	
	boolean existsByShortUrl(String shortUrl);

}
