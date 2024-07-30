package com.retailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
 * This is the main method which will start the application.
 * Starts the spring boot application and enables auto-configurations.
 * @param args Unused. 
 * @return Nothing. 
 */
@SpringBootApplication
public class RetailerOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailerOffersApplication.class, args);
	}

}
