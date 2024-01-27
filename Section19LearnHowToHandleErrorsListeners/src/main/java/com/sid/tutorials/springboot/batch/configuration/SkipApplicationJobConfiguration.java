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
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sid.tutorials.springboot.batch.component.CustomException;
import com.sid.tutorials.springboot.batch.component.CustomSkipListener;
import com.sid.tutorials.springboot.batch.component.SkipItemProcessor;
import com.sid.tutorials.springboot.batch.component.SkipItemWriter;

/**
 * @author Lenovo
 *
 */
@Configuration
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
		IntStream.range(0, 100).forEach(i->items.add(String.valueOf(i)));
		return new ListItemReader<>(items);
	}

	@Bean
	@StepScope
	public SkipItemProcessor processor() {
		SkipItemProcessor processor = new SkipItemProcessor();
		return processor;
	}

	@Bean
	@StepScope
	public SkipItemWriter writer() {
		SkipItemWriter writer = new SkipItemWriter();
		return writer;
	}

	@Bean
	public Step step1() {
		int value = random.nextInt(10);
		return stepBuilderFactory.get("skipRetryListenersApplicationStep-" + value).<String, String>chunk(10).reader(
				reader()).processor(processor()).writer(writer()).faultTolerant().skip(CustomException.class)
				.skipLimit(15).listener(new CustomSkipListener()).build();
	}

	@Bean
	public Job job() {
		int value = random.nextInt(10);
		return jobBuilderFactory.get("FirstSkipRetryListenersApplicationSpringBatchProject-" + value).start(step1())
				.build();
	}
}
