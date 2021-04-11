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
public class Section06LearnAboutSpringBatchListeners {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Section06LearnAboutSpringBatchListeners.class, args);
	}

}
