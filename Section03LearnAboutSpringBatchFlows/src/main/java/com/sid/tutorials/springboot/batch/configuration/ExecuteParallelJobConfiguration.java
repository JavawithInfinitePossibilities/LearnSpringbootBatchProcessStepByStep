/**
 * 
 */
package com.sid.tutorials.springboot.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @author Lenovo
 *
 */
@Configuration
public class ExecuteParallelJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job executeParallelFlowJob(@Qualifier("firstFlow") Flow firstFlow, @Qualifier("lastFlow") Flow lastFlow) {
		return jobBuilderFactory.get("SeventhParallelJobSpringBatchProject").incrementer(new RunIdIncrementer())
				.start(firstFlow)
				.split(new SimpleAsyncTaskExecutor())
				.add(lastFlow)
				.end()
				.build();
	}
}
