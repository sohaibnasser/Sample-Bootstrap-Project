package com.cs.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class StartApp {

	public static void main(String[] args) {
		SpringApplication.run(StartApp.class, args);
	}
	
	
//	@Bean
//	public CorsFilter corsFilter() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    final CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowedOrigins(Collections.singletonList("*")); // Provide list of origins if you want multiple origins
//	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
//	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//	    config.setAllowCredentials(true);
//	    source.registerCorsConfiguration("/**", config);
//	    return new CorsFilter(source);
//	}

}
