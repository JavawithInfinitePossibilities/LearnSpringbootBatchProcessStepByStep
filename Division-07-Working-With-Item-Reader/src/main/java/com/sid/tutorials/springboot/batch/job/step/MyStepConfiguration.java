/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.customlistener.CustomStepListener;
import com.sid.tutorials.springboot.batch.job.task.CustomItemProcessor;
import com.sid.tutorials.springboot.batch.job.task.CustomItemReader;
import com.sid.tutorials.springboot.batch.job.task.CustomItemWriter;

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
	private CustomItemReader customItemReader;

	@Autowired
	private CustomItemProcessor customItemProcessor;

	@Autowired
	private CustomItemWriter customItemWriter;

	@Autowired
	@Qualifier("customFlatFileItemReaderForCSV")
	private ItemReader<Car> itemReaderCar;

	@Autowired
	@Qualifier("customItemProcessorForAll")
	private ItemProcessor<Car, String> itemProcessorCar;

	@Autowired
	@Qualifier("customItemWriterIngeneral")
	private ItemWriter<String> itemWriterCar;

	@Autowired
	private CustomStepListener customStepListener;

	@Bean
	public Step firstStep() {
		return stepBuilderFactory.get("First-Step").<Integer, Long>chunk(10).reader(customItemReader)
				.processor(customItemProcessor).writer(customItemWriter).listener(customStepListener).build();
	}

	@Bean
	public Step secondStep() {
		return stepBuilderFactory.get("Second-Step")
				.tasklet((StepContribution stepContribution, ChunkContext ChunkContext) -> {
					System.out.println("===============>" + welcomeMessage);
					return RepeatStatus.FINISHED;
				}).listener(customStepListener).build();
	}

	@Bean
	public Step thirdStep() {
		return stepBuilderFactory.get("third-Step").<Car, String>chunk(10).reader(itemReaderCar)
				.processor(itemProcessorCar).writer(itemWriterCar).listener(customStepListener).build();
	}
}
