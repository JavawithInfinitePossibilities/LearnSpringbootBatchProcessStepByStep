/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kunmu
 *
 */
@SpringBootApplication
@EnableBatchProcessing
public class Division01Introduction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Division01Introduction.class, args);
	}

}
