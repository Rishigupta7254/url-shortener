package com.rishi.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(urlNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleUrlNotFoundEntity(
			urlNotFoundException ex){
		
		Map<String,Object>response=new HashMap<>();
		
		response.put("timestamp",LocalDateTime.now() );
		response.put("status",404);
		response.put("message",ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		
	}
	
	public ResponseEntity<Map<String,Object>> handleIllegalArgument(IllegalArgumentException ex){
		
		Map<String,Object>response=new HashMap<>();
		
		response.put("timestand",LocalDateTime.now());
		response.put("status",400);
		response.put("message",ex.getMessage());
	
		
		return ResponseEntity.badRequest().body(response);
		
	}
	
	public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex){
		
		Map<String,Object> errors=new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(errors);
		
	}
	 
	

}
 