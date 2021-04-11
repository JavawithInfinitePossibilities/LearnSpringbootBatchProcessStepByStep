/**
 * 
 */
package com.sid.tutorials.springboot.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lenovo
 *
 */
@Configuration
@EnableBatchProcessing
public class CompleteFlowConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	@Qualifier("step4")
	private Step step4;

	@Bean
	public Job executeCompleteFlowJob(@Qualifier("firstFlow") Flow firstFlow, @Qualifier("lastFlow") Flow lastFlow) {
		return jobBuilderFactory.get("SixthCompleteSpringBatchProject")
				.start(firstFlow)
				.next(step4).on("COMPLETED").to(lastFlow)
				.end()
				.build();
	}
}
