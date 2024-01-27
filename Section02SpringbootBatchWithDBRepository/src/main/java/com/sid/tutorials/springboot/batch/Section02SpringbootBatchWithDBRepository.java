/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.JdbcTransactionManager;

/**
 * @author Lenovo
 *
 */
@SpringBootApplication
@EnableBatchProcessing
public class Section02SpringbootBatchWithDBRepository {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Section02SpringbootBatchWithDBRepository.class, args);
	}

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcTransactionManager batchTransactionManager(DataSource dataSource) {
		return new JdbcTransactionManager(dataSource);
	}
}
