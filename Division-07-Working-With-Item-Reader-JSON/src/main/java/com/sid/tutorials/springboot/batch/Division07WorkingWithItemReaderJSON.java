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
public class Division07WorkingWithItemReaderJSON {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Division07WorkingWithItemReaderJSON.class, args);
	}

}
