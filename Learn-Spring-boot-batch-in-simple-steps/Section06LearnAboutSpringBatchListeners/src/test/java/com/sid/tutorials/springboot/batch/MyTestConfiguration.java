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

/**
 * @author kunmu
 *
 */
@TestConfiguration
public class MyTestConfiguration {

	@Autowired
	private DataSource dataSource;

	@Bean("jobBatchListener")
	public JobLauncherTestUtils getJobLauncherTestUtil() {
		return new JobLauncherTestUtils() {
			@Override
			@Autowired
			public void setJob(@Qualifier("listenerJob") Job job) {
				super.setJob(job);
			}
		};
	}

}
