/**
 * 
 */
package com.sid.tutorials.springboot.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sid.tutorials.springboot.batch.customlistener.CustomJobListener;

/**
 * @author kunmu
 *
 */
@Configuration
public class SampleTaskletJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private Step firstStep;

	@Autowired
	private Step secondStep;

	@Autowired
	private CustomJobListener customJobListener;

	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First-Job").incrementer(new RunIdIncrementer()).start(firstStep).next(secondStep)
				.listener(customJobListener).build();
	}

}
