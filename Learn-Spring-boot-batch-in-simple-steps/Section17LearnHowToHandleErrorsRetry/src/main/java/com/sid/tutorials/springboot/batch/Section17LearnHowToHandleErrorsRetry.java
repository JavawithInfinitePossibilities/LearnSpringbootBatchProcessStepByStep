/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lenovo
 *
 */
@SpringBootApplication
@EnableBatchProcessing
public class Section17LearnHowToHandleErrorsRetry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Section17LearnHowToHandleErrorsRetry.class, args);
	}

}
