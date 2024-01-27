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

	@Bean("skipListenerComplexJobTestUtil")
	public JobLauncherTestUtils getJobLauncherTestUtil() {
		return new JobLauncherTestUtils() {
			@Override
			@Autowired
			public void setJob(@Qualifier("skipListenerComplexJob") Job job) {
				super.setJob(job);
			}
		};
	}

}
