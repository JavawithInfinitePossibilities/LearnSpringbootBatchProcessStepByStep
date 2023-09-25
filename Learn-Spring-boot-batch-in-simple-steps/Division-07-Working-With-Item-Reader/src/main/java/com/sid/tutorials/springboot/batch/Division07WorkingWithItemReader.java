/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author kunmu
 *
 */
@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
@EnableScheduling
@PropertySource("classpath:config.properties")
public class Division07WorkingWithItemReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Division07WorkingWithItemReader.class, args);
	}

}
