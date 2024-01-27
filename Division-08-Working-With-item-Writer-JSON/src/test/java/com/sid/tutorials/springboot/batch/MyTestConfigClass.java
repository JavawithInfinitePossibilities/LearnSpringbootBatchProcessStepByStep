/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kunmu
 *
 */
@TestConfiguration
public class MyTestConfigClass {

	@Bean(name = "customJoblauncherTestutils")
	public JobLauncherTestUtils getJobLauncherTestutils() {
		return new JobLauncherTestUtils() {
			@Override
			@Autowired
			public void setJob(@Qualifier("firstJob") Job job) {
				super.setJob(job);
			}
		};
	}
}
