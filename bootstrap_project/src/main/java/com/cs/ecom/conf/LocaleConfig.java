package com.cs.ecom.conf;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class LocaleConfig {

	@PostConstruct
	public void init() {
//		TimeZone.setDefault(TimeZone.getTimeZone("MST"));
		System.out.println("DATE: "+ LocalDateTime.now());
	}
	
}
