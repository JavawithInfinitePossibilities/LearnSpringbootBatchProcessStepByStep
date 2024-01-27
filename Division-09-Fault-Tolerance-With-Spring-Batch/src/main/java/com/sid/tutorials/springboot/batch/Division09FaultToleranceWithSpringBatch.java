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
@EnableAsync
@PropertySource("classpath:config.properties")
public class Division09FaultToleranceWithSpringBatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Division09FaultToleranceWithSpringBatch.class, args);
	}

}
