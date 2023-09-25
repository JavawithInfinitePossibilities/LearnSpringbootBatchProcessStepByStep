/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kunmu
 *
 */
@SpringBootApplication
@EnableBatchProcessing
@PropertySource("classpath:config.properties")
public class Division05ChunkOrientedStep {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Division05ChunkOrientedStep.class, args);
	}

}
