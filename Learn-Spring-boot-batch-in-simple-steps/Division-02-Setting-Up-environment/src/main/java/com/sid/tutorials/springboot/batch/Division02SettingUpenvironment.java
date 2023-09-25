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
public class Division02SettingUpenvironment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Division02SettingUpenvironment.class, args);
	}

}
