/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kunmu
 *
 */
@SpringBootApplication
@EnableBatchProcessing
@PropertySource("classpath:config.properties")
@EnableAsync
public class Division06JobHandlingwithRESTAPIAndSpringSchedularAsync {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Division06JobHandlingwithRESTAPIAndSpringSchedularAsync.class, args);
	}
}
