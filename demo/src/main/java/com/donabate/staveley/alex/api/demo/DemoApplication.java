package com.donabate.staveley.alex.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 12th March. 
 *  
 * 1. Show 3 different exception patterns.  Annotations, APIviolations, Business Violations 
 * 2. Show Delete API
 * 3. Edit API
 * 3. Parent / child resource, Team / Jersey
 * 4. Request filter, logging paraneters
 * 
 * Next:
 * 1. Show link and unlink APIs
 * 
 * @author astaveley
 *
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
