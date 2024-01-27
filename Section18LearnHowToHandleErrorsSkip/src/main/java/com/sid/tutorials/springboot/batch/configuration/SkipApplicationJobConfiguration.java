/**
 * 
 */
package com.sid.tutorials.springboot.batch.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.sid.tutorials.springboot.batch.component.CustomRetryableException;
import com.sid.tutorials.springboot.batch.component.SkipItemProcessor;
import com.sid.tutorials.springboot.batch.component.SkipItemWriter;

/**
 * @author Lenovo
 *
 */
@Configuration
@EnableBatchProcessing
public class SkipApplicationJobConfiguration {

	Random random = new Random();
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	@StepScope
	public ListItemReader<String> reader() {
		List<String> items = new ArrayList<>();
		IntStream.range(0, 100).forEach(i -> items.add(String.valueOf(i)));
		return new ListItemReader<>(items);
	}

	@Bean
	@StepScope
	public SkipItemProcessor processor(@Value("#{jobParameters['skip']}") String skip) {
		SkipItemProcessor processor = new SkipItemProcessor();
		processor.setSkip(StringUtils.hasText(skip) && skip.equalsIgnoreCase("processor"));
		return processor;
	}

	@Bean
	@StepScope
	public SkipItemWriter writer(@Value("#{jobParameters['skip']}") String skip) {
		SkipItemWriter writer = new SkipItemWriter();
		writer.setSkip(StringUtils.hasText(skip) && skip.equalsIgnoreCase("writer"));
		return writer;
	}

	@Bean
	public Step step1() {
		int value = random.nextInt(10);
		return stepBuilderFactory.get("skipApplicationStep-" + value).<String, String>chunk(10).reader(reader())
				.processor(processor(null)).writer(writer(null)).faultTolerant().skip(CustomRetryableException.class)
				.skipLimit(15).build();
	}

	@Bean
	public Job job() {
		int value = random.nextInt(10);
		return jobBuilderFactory.get("FirstSkipApplicationSpringBatchProject-" + value).start(step1()).build();
	}
}
