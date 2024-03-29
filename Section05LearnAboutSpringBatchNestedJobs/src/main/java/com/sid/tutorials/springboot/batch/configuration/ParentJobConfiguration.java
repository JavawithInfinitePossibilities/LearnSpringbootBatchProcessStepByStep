/**
 * 
 */
package com.sid.tutorials.springboot.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Lenovo
 *
 */
@Configuration
@EnableBatchProcessing
public class ParentJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("childJob")
	private Job childJobN;

	@Autowired
	private JobLauncher jobLauncher;

	@Bean
	public Step step1Parent() {
		return stepBuilderFactory.get("step1StartParent").tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
			System.out.println("This is step in Start flow step1Start Parent : Starting --->"
					+ String.format("%s has been executed on thread %s ", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
			Thread.sleep(5000);
			System.out.println("This is step in Start flow step1Start Parent : Ending --->"
					+ String.format("%s has been executed on thread %s ", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public Job parentJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		Step childJobStep = new JobStepBuilder(new StepBuilder("childJobStep"))
				.job(childJobN)
				.launcher(jobLauncher)
				.repository(jobRepository)
				.transactionManager(platformTransactionManager)
				.build();
		return jobBuilderFactory.get("parentJob").incrementer(new RunIdIncrementer())
				.start(step1Parent())
				.next(childJobStep)
				.build();
	}
}
