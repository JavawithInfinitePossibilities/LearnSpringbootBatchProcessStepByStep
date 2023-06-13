/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Lenovo
 *
 */
@SpringBootApplication
@EnableBatchProcessing
public class Section03LearnAboutSpringBatchFlows {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Section03LearnAboutSpringBatchFlows.class, args);
		SpringApplication.exit(run);
	}

}
