package com.rishi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.dto.CreateUrlRequest;
import com.rishi.dto.UrlResponse;
import com.rishi.service.UrlService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> createUrl(
            @Valid @RequestBody CreateUrlRequest request) {

        UrlResponse response = urlService.createShortUrl(request);

        return ResponseEntity.ok(response);
    }
}