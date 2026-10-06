package com.rishi.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateUrlRequest {

	@NotBlank(message="Original URL is required")
	@Pattern(regexp="^(https?://).+",
			 message="URL must start with http:// or https://")
	
	private String originalUrl;
	private String customsAlias;
	private LocalDateTime expiresAt;
}
