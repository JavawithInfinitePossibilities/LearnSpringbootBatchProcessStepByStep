/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sid.tutorials.springboot.batch.customlistener.CustomStepListener;

/**
 * @author kunmu
 *
 */
@Configuration
public class MyStepConfiguration {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Value("${Welcome.To.Springboot.Batch}")
	private String welcomeMessage;

	@Autowired
	private CustomStepListener customStepListener;

	@Bean
	public Step firstStep() {
		return stepBuilderFactory.get("First-Step")
				.tasklet((StepContribution stepContribution, ChunkContext ChunkContext) -> {
					System.out.println(welcomeMessage);
					return RepeatStatus.FINISHED;
				}).listener(customStepListener).build();
	}

	@Bean
	public Step secondStep() {
		return stepBuilderFactory.get("Second-Step")
				.tasklet((StepContribution stepContribution, ChunkContext ChunkContext) -> {
					System.out.println("===============>" + welcomeMessage);
					return RepeatStatus.FINISHED;
				}).listener(customStepListener).build();
	}
}
