/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author kunmu
 *
 */
@SpringBootApplication
@EnableBatchProcessing
@PropertySources(@PropertySource("classpath:config.properties"))
public class Division03IntroductionToSpringBatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Division03IntroductionToSpringBatch.class, args);
	}

}
