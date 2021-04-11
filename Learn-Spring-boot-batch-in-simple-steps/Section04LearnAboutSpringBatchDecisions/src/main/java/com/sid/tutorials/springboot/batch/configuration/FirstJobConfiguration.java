/**
 * 
 */
package com.sid.tutorials.springboot.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sid.tutorials.springboot.batch.bean.CustomDeciderClass;

/**
 * @author Lenovo
 *
 */
@Configuration
@EnableBatchProcessing
public class FirstJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CustomDeciderClass customDeciderClass;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1Start").tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
			System.out.println("This is step in Start flow step1Start:Starting --->"
					+ String.format("%s has been executed on thread %s ", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
			Thread.sleep(5000);
			System.out.println("This is step in Start flow step1Start:Ending --->"
					+ String.format("%s has been executed on thread %s ", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2Even")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("This is step in Start flow step2Even:Starting --->"
							+ String.format("%s has been executed on thread %s ",
									chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
					Thread.sleep(5000);
					System.out.println("This is step in Start flow step2Even:Ending --->"
							+ String.format("%s has been executed on thread %s ",
									chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
					return RepeatStatus.FINISHED;
				}).build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3Odd")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("This is step in Start flow step3Odd:Starting --->"
							+ String.format("%s has been executed on thread %s ",
									chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
					Thread.sleep(5000);
					System.out.println("This is step in Start flow step3Odd:Ending --->"
							+ String.format("%s has been executed on thread %s ",
									chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
					return RepeatStatus.FINISHED;
				}).build();
	}

	@Bean
	public Job firstFlowJob() {
		return jobBuilderFactory.get("EighthDeciderSpringBatchProject")
				.start(step1())
				/*.next(customDeciderClass.customDecider())*/
				.next(customDeciderClass.customDecider()).on("EVEN").to(step2())
				.next(customDeciderClass.customDecider()).on("ODD").to(step3())
				.from(step2()).on("*").to(customDeciderClass.customDecider())
				.from(step3()).on("*").to(customDeciderClass.customDecider())
				/*.from(customDeciderClass.customDecider()).on("EVEN").to(step2())
				.from(customDeciderClass.customDecider()).on("ODD").to(step3())*/
				.end()
				.build();
	}

}
