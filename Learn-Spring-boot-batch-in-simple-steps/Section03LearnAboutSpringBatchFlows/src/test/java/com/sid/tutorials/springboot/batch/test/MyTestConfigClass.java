/**
 * 
 */
package com.sid.tutorials.springboot.batch.test;

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

	@Bean(name = "myJobLauncherTestUtilsJob1")
	public JobLauncherTestUtils myJobLauncherTestUtilsJob1() {
		return new JobLauncherTestUtils() {
			@Override
			@Autowired
			public void setJob(@Qualifier("firstFlowJob") Job job) {
				super.setJob(job);
			}
		};
	}

	@Bean(name = "myJobLauncherTestUtilsJob2")
	public JobLauncherTestUtils myJobLauncherTestUtilsJob2() {
		return new JobLauncherTestUtils() {
			@Override
			@Autowired
			public void setJob(@Qualifier("lastFlowJob") Job job) {
				super.setJob(job);
			}
		};
	}

	@Bean(name = "myJobLauncherTestUtilsJob3")
	public JobLauncherTestUtils myJobLauncherTestUtilsJob3() {
		return new JobLauncherTestUtils() {
			@Override
			@Autowired
			public void setJob(@Qualifier("executeCompleteFlowJob") Job job) {
				super.setJob(job);
			}
		};
	}

	@Bean(name = "myJobLauncherTestUtilsJob4")
	public JobLauncherTestUtils myJobLauncherTestUtilsJob4() {
		return new JobLauncherTestUtils() {
			@Override
			@Autowired
			public void setJob(@Qualifier("executeParallelFlowJob") Job job) {
				super.setJob(job);
			}
		};
	}
}
