package com.demo.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//(exclude = { SecurityAutoConfiguration.class })
//@EnableAutoConfiguration
public class EcommerceApplication {
//extends SpringBootServletInitializer

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(EcommerceApplication.class);
//	}
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
