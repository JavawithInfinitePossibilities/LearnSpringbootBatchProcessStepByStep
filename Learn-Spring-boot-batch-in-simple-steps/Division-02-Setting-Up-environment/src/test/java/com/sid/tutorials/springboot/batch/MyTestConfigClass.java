/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.JdbcTransactionManager;

/**
 * @author kunmu
 *
 */
@TestConfiguration
public class MyTestConfigClass {

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcTransactionManager batchTransactionManager(DataSource dataSource) {
		return new JdbcTransactionManager(dataSource);
	}
}
