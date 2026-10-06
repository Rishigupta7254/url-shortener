package com.rishi.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="urls")
@Getter
@Setter
public class Url {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=2048)
	private String originalUrl;
	
	@Column(nullable=false,unique=true,length=20)
	private String shortUrl;
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	

	private LocalDateTime expiresAt;
	
	@Column(nullable = false)
	private Boolean active=true;
	
	
	@Column(nullable = false)
	private Long clickCount=0L;
	
	
}
