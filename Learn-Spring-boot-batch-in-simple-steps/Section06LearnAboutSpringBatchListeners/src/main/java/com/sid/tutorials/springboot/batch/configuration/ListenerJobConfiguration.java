/**
 * 
 */
package com.sid.tutorials.springboot.batch.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sid.tutorials.springboot.batch.ListItemReader;
import com.sid.tutorials.springboot.batch.listener.ChunkListener;
import com.sid.tutorials.springboot.batch.listener.CustomJobListener;

/**
 * @author Lenovo
 *
 */
@Configuration
@EnableBatchProcessing
public class ListenerJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CustomJobListener customJobListener;

	@Autowired
	private ChunkListener chunkListener;

	@Bean
	public ItemReader<String> reader() {
		System.out.println("READER");
		List<String> newList = new ArrayList<String>();
		newList.add("One");
		newList.add("Two");
		newList.add("Three");
		newList.add("Four");
		newList.add("Five");
		System.out.println("Size of the list:" + newList.size());
		ListItemReader<String> itemReader=new ListItemReader<String>();
		itemReader.setList(newList);
		return itemReader;
	}

	@Bean
	public ItemWriter<String> writer() {
		System.out.println("WRITER");
		return ((List<? extends String> items) -> {
			for (String item : items) {
				System.out.println("Writer is writting the items : " + item);
			}
		});
	}

	@Bean
	public Step step1() {
		System.out.println("STEP");
		return stepBuilderFactory.get("step1ListenerStart")
				.<String, String>chunk(2)
				.faultTolerant()
				.listener(chunkListener)
				.reader(reader())
				.writer(writer())
				.build();
	}

	@Bean
	public Job listenerJob() {
		System.out.println("JOB");
		return jobBuilderFactory.get("ListenerJobConfiguration").start(step1()).listener(customJobListener).build();
	}

}
