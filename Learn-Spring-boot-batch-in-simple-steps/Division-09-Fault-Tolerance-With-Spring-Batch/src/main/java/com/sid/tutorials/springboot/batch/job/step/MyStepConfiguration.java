/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.bean.Customer;
import com.sid.tutorials.springboot.batch.bean.GeneralBean;
import com.sid.tutorials.springboot.batch.customlistener.CustomSkipPolicyForError;
import com.sid.tutorials.springboot.batch.customlistener.CustomStepListener;
import com.sid.tutorials.springboot.batch.job.task.customItemReader.CustomItemReader;
import com.sid.tutorials.springboot.batch.job.task.customItemWriter.CustomItemWriter;
import com.sid.tutorials.springboot.batch.job.task.customItemprocessor.CustomItemProcessor;

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
	@Qualifier("jsonItemReader")
	private ItemReader<Car> jsonItemReaderCar;

	@Autowired
	@Qualifier("customerItemProcessorForAll")
	private ItemProcessor<GeneralBean, GeneralBean> itemProcessorCar;

	@Autowired
	@Qualifier("customerItemWriterIngeneralForJSON")
	private ItemWriter<? extends GeneralBean> customerItemWriterIngeneralForJSON;

	@Autowired
	private CustomSkipPolicyForError customSkipPolicyForError;

	@Autowired
	private CustomStepListener customStepListener;

	@SuppressWarnings("unchecked")
	@Bean
	public Step firstStep() {
		return stepBuilderFactory.get("First-Step").<Car, GeneralBean>chunk(10).reader(jsonItemReaderCar)
				.processor(itemProcessorCar)
				.writer((ItemWriter<? super GeneralBean>) customerItemWriterIngeneralForJSON).faultTolerant()
				.skip(Throwable.class).skipPolicy(new AlwaysSkipItemSkipPolicy()).listener(customSkipPolicyForError)
				.listener(customStepListener).build();
	}
}
