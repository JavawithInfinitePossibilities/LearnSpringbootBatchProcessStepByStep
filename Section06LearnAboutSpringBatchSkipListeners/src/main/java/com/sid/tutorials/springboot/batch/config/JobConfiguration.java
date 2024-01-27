/**
 * 
 */
package com.sid.tutorials.springboot.batch.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.FaultTolerantStepBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.bean.CustomErrorItem;
import com.sid.tutorials.springboot.batch.config.processor.CustomItemProcessor;
import com.sid.tutorials.springboot.batch.config.reader.CustomItemReader;
import com.sid.tutorials.springboot.batch.config.writer.CustomItemWriter;
import com.sid.tutorials.springboot.batch.customException.MyCustomException;
import com.sid.tutorials.springboot.batch.listener.CustomSkipListener;
import com.sid.tutorials.springboot.batch.listener.CustomSkipListenerPolicy;

/**
 * @author kunmu
 *
 */
@Configuration
@EnableBatchProcessing
public class JobConfiguration {

	private static final String NEW_LINE = System.lineSeparator();

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CustomItemReader customItemReader;

	@Autowired
	private CustomItemProcessor customItemProcessor;

	@Autowired
	private CustomItemWriter customItemWriter;

	@Autowired
	private CustomSkipListenerPolicy customSkipListenerPolicy;

	@Autowired
	private CustomSkipListener customSkipListener;

	@Autowired
	private CustomErrorItem customErrorItem;

	@Bean
	public FlatFileItemWriter<Car> getCustomFlatFileItemWriter() throws Exception {
		System.out.println("===============>This is the Writer class !!!!");
		ObjectMapper objectMapper = new ObjectMapper();
		FlatFileItemWriter<Car> flatFileItemWriter = new FlatFileItemWriter<>();
		flatFileItemWriter.setLineAggregator((Car item) -> {
			try {
				return objectMapper.writeValueAsString(item);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e.getMessage());
			}
		});
		String rootPath = System.getProperty("user.dir");
		flatFileItemWriter.setResource(new FileSystemResource(
				StringUtils.join(rootPath, "/src/main/resources/outputdirectory/", "result.out")));
		flatFileItemWriter.afterPropertiesSet();
		return flatFileItemWriter;
	}

	@Bean
	public Step step1() throws MyCustomException {
		return ((FaultTolerantStepBuilder<Car, Car>) stepBuilderFactory.get("CarJobSteps1").<Car, Car>chunk(10)
				.reader(customItemReader).processor(customItemProcessor).writer(customItemWriter)
				/*.writer(getCustomFlatFileItemWriter())*/
				.faultTolerant()
				/*.skip(MyCustomException.class).skipLimit(1000)*/
				.listener(customSkipListener)).skipPolicy(customSkipListenerPolicy).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("ErrorCarJobSteps2")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					ObjectMapper objectMapper = new ObjectMapper();
					String rootPath = System.getProperty("user.dir");
					Path path = Paths
							.get(StringUtils.join(rootPath, "/src/main/resources/outputdirectory/", "error.out"));
					System.out.println(
							"===============>This is the CustomSkipListener class error section number of error cars is : "
									+ customErrorItem.getErrorCars().keySet().size());
					customErrorItem.getErrorCars().entrySet().stream().forEach((entrySetItem) -> {
						try {
							Files.writeString(path,
									objectMapper.writeValueAsString(
											entrySetItem.getKey() + "Error Message is " + entrySetItem.getValue())
											+ NEW_LINE,
									StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
					return RepeatStatus.FINISHED;
				}).build();
	}

	@Bean
	public Job skipListenerComplexJob() throws Exception {
		return jobBuilderFactory.get("SkipListenerComplexItemWriterJob").start(step1()).on("COMPLETED").to(step2())
				.from(step2()).end().build();
	}
}
