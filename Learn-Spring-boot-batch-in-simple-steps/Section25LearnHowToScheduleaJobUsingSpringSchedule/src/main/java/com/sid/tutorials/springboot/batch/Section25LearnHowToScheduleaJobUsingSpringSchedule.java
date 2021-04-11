/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Lenovo
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableBatchProcessing
@EnableScheduling
public class Section25LearnHowToScheduleaJobUsingSpringSchedule {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Section25LearnHowToScheduleaJobUsingSpringSchedule.class, args);
	}

}
