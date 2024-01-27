/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.JdbcTransactionManager;

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
		/*ConfigurableApplicationContext run = SpringApplication.run(Section03LearnAboutSpringBatchFlows.class, args);
		SpringApplication.exit(run);*/

		SpringApplication.run(Section03LearnAboutSpringBatchFlows.class, args);
	}

}
