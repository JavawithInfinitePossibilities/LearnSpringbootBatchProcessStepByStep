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
	private ItemProcessor<GeneralBean, String> itemProcessorCar;

	@Autowired
	@Qualifier("customItemWriterIngeneral")
	private ItemWriter<String> itemWriterCar;

	@Autowired
	@Qualifier("jsonItemReader")
	private ItemReader<Car> jsonItemReaderCar;

	@Autowired
	@Qualifier("jdbcItemReaderCustomer")
	private ItemReader<Customer> jdbcCurserItemReaderCustomer;

	@Autowired
	@Qualifier("jdbcGeneralItemReaderCustomer")
	private ItemReader<? extends GeneralBean> jdbcGeneralItemReaderCustomer;

	@Autowired
	@Qualifier("customerItemProcessorForAll")
	private ItemProcessor<GeneralBean, GeneralBean> customerItemProcessorForAll;

	@Autowired
	@Qualifier("customerItemWriterIngeneral")
	private ItemWriter<? extends GeneralBean> customerItemWriterIngeneral;

	@Autowired
	@Qualifier("customerItemWriterIngeneralForJDBC")
	private ItemWriter<? extends GeneralBean> customerItemWriterIngeneralForJDBC;

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

	@Bean
	public Step fourthStep() {
		return stepBuilderFactory.get("fourth-Step").<Car, String>chunk(10).reader(jsonItemReaderCar)
				.processor(itemProcessorCar).writer(itemWriterCar).listener(customStepListener).build();
	}

	@Bean
	public Step fifthStep() {
		return stepBuilderFactory.get("fifth-Step").<Customer, String>chunk(10).reader(jdbcCurserItemReaderCustomer)
				.processor(itemProcessorCar).writer(itemWriterCar).listener(customStepListener).build();
	}

	@SuppressWarnings("unchecked")
	@Bean
	public Step sixthStep() {
		return stepBuilderFactory.get("sixth-Step").<GeneralBean, GeneralBean>chunk(10)
				.reader(jdbcGeneralItemReaderCustomer).processor(customerItemProcessorForAll)
				.writer((ItemWriter<? super GeneralBean>) customerItemWriterIngeneral).listener(customStepListener)
				.build();
	}

	@SuppressWarnings("unchecked")
	@Bean
	public Step eighthStep() {
		return stepBuilderFactory.get("eighth-Step").<GeneralBean, GeneralBean>chunk(10)
				.reader(jdbcGeneralItemReaderCustomer).processor(customerItemProcessorForAll)
				.writer((ItemWriter<? super GeneralBean>) customerItemWriterIngeneralForJDBC)
				.listener(customStepListener).build();
	}
}
