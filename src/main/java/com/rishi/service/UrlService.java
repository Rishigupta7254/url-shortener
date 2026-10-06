package com.rishi.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.rishi.dto.CreateUrlRequest;
import com.rishi.dto.UrlResponse;
import com.rishi.entity.Url;
import com.rishi.entity.UrlClick;
import com.rishi.exception.urlNotFoundException;
import com.rishi.repository.UrlClickRepository;
import com.rishi.repository.UrlRepository;

import jakarta.transaction.Transactional;

@Service
public class UrlService {
	
	private final UrlRepository urlRepository;
	private final UrlClickRepository urlClickRepository;
	
	
	private static final String CHARACTERS=
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private final Random random=new Random();
	
	public  UrlService(UrlRepository urlRepository,UrlClickRepository urlClickRepository) {
		
		this.urlClickRepository=urlClickRepository;
		this.urlRepository=urlRepository;
	}
	
	public UrlResponse createShortUrl(CreateUrlRequest request) {
		
		String shortCode;
		
		if(request.getCustomsAlias()!=null && !request.getCustomsAlias().isBlank()) {
			
			String alias=request.getCustomsAlias().trim();
			
			if(urlRepository.existsByShortUrl(alias)) {
				throw new IllegalArgumentException("custome alias already exists");
			}
			
			shortCode=alias;
		}else {
			shortCode=generateUniqueCode();
		}
		Url url =new Url();
		
		url.setOriginalUrl(request.getOriginalUrl());
		url.setShortUrl(shortCode);
		url.setCreatedAt(LocalDateTime.now());
		url.setActive(true);
		url.setClickCount(0L);
		
		Url savedUrl=urlRepository.save(url);
		
		String shortUrl  ="http://localhost:8080/"+savedUrl.getShortUrl();
		
		return new UrlResponse(
			    savedUrl.getShortUrl(),
			    shortUrl,
			    savedUrl.getOriginalUrl(),
			    savedUrl.getClickCount()
			);
	}
	
	private String generateUniqueCode() {
		
		String code;
		do{
			StringBuilder builder=new StringBuilder();
			
			for(int i=0;i<6;i++) {
				int index=random.nextInt(CHARACTERS.length());
				builder.append(CHARACTERS.charAt(index));
				
			}
			code=builder.toString();
		}while(urlRepository.existsByShortUrl(code));
		
		return code;
		
	}
	@Transactional
	public Url redirect(String shortCode, String ipAddress,String userAgent,String referrer) {
		
		Url url=urlRepository.findByShortUrl(shortCode).orElseThrow(()->new urlNotFoundException("short URl Not Found"));
		
		
		if(!url.getActive()) {
			throw new urlNotFoundException("this URL is inactive");
			
		}
		if(url.getExpiresAt()!=null && LocalDateTime.now().isAfter(url.getExpiresAt())) {
			
			throw new urlNotFoundException("tjis URl has Expired");
		}
		
		url.setClickCount(url.getClickCount()+1);
		urlRepository.save(url);
		
		
		UrlClick click=new UrlClick();
		
		
		click.setUrl(url);
        click.setClickedAt(LocalDateTime.now());
        click.setIpAddress(ipAddress);
        click.setUserAgent(userAgent);
        click.setReferrer(referrer);
        
        urlClickRepository.save(click);
  
		
		return url;
		
	}
	
	public Url getUrl(String shortcode) {
		
		return urlRepository.findByShortUrl(shortcode).orElseThrow(()->new urlNotFoundException("Short Url Not Found"));
	}

}
