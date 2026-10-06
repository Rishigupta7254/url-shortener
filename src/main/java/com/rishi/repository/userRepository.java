package com.rishi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishi.entity.User;

public interface userRepository extends JpaRepository<User, Long>{
	
	Optional<User>findByusername(String username);
	
	Optional<User> findByEmail(String email);
	
	

}
