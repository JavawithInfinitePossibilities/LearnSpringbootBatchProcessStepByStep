/**
 * 
 */
package com.sid.tutorials.springboot.batch.configuration;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sid.tutorials.springboot.batch.module.db.Customer;
import com.sid.tutorials.springboot.batch.process.writer.ClassifierItemWriter;

/**
 * @author Lenovo
 *
 */
@Configuration
@EnableBatchProcessing
public class DBInputAppJobConfiguration {
	Random random = new Random();
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	/**
	 * This is the section of Item reader.
	 * 
	 * @return
	 */

	@Bean
	public JdbcCursorItemReader<Customer> customItemReader() {
		JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();
		reader.setSql("select id, firstName, lastName, birthdate from customer order by lastName, firstName");
		reader.setDataSource(this.dataSource);
		reader.setRowMapper((ResultSet resultSet, int i) -> {
			return new Customer(resultSet.getLong("id"), resultSet.getString("firstName"),
					resultSet.getString("lastName"), resultSet.getDate("birthdate"));
		});
		return reader;
	}

	@Bean
	public JdbcPagingItemReader<Customer> pagingItemReader() {
		JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(100);
		reader.setRowMapper((ResultSet resultSet, int i) -> {
			return new Customer(resultSet.getLong("id"), resultSet.getString("firstName"),
					resultSet.getString("lastName"), resultSet.getDate("birthdate"));
		});

		MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("id, firstName, lastName, birthdate");
		queryProvider.setFromClause("from customer");
		Map<String, Order> sortKeys = new HashMap<>(1);
		sortKeys.put("id", Order.ASCENDING);
		queryProvider.setSortKeys(sortKeys);
		reader.setQueryProvider(queryProvider);
		return reader;
	}

	/**
	 * This is the section of writer.
	 * 
	 * @return
	 */

	@Bean
	public ItemWriter<Customer> customItemWriter() {
		return (List<? extends Customer> customers) -> {
			for (Customer currentItem : customers) {
				System.out.println("Current Item : " + currentItem);
			}
		};
	}

	@Bean
	@StepScope
	public StaxEventItemWriter<Customer> customerXMLItemWriter() throws Exception {
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(Collections.singletonMap("customer", Customer.class));
		StaxEventItemWriter<Customer> itemWriter = new StaxEventItemWriter<>();
		Resource exportFileResource = new FileSystemResource(
				File.createTempFile("customerOutput", ".xml").getAbsolutePath());
		itemWriter.setRootTagName("customers");
		itemWriter.setMarshaller(marshaller);
		String customerOutputPath = File.createTempFile("customerOutput", ".xml").getAbsolutePath();
		System.out.println(">> Output Path: " + customerOutputPath);
		itemWriter.setResource(new FileSystemResource(customerOutputPath));
		itemWriter.afterPropertiesSet();
		return itemWriter;
	}

	@Bean
	@StepScope
	public FlatFileItemWriter<Customer> customerJSONItemWriter() throws Exception {
		FlatFileItemWriter<Customer> itemWriter = new FlatFileItemWriter<>();
		ObjectMapper objectMapper = new ObjectMapper();
		itemWriter.setLineAggregator((Customer item) -> {
			try {
				return objectMapper.writeValueAsString(item);
			} catch (JsonProcessingException e) {
				throw new RuntimeException("Unable to serialize Customer", e);
			}
		});
		String customerOutputPath = File.createTempFile("customerOutput", ".out").getAbsolutePath();
		System.out.println(">> Output Path: " + customerOutputPath);
		itemWriter.setResource(new FileSystemResource(customerOutputPath));
		itemWriter.afterPropertiesSet();
		return itemWriter;
	}

	@Bean
	@StepScope
	public CompositeItemWriter<Customer> compositeItemWriter() throws Exception {
		List<ItemWriter<? super Customer>> itemWriters = new ArrayList<ItemWriter<? super Customer>>();
		itemWriters.add(customerXMLItemWriter());
		itemWriters.add(customerJSONItemWriter());
		CompositeItemWriter<Customer> compositeItemWriter = new CompositeItemWriter<Customer>();
		compositeItemWriter.setDelegates(itemWriters);
		compositeItemWriter.afterPropertiesSet();
		return compositeItemWriter;
	}

	@Bean
	@StepScope
	public ClassifierCompositeItemWriter<Customer> classifierCompositeItemWriter() throws Exception {
		ClassifierCompositeItemWriter<Customer> classifierCompositeItemWriter = new ClassifierCompositeItemWriter<Customer>();
		classifierCompositeItemWriter
				.setClassifier(new ClassifierItemWriter(customerXMLItemWriter(), customerJSONItemWriter()));
		return classifierCompositeItemWriter;
	}

	/**
	 * This is the section of Item processor.
	 * 
	 * @return
	 */

	@Bean
	public ItemProcessor<Customer, Customer> itemProcesser() {
		ItemProcessor<Customer, Customer> processor = (Customer item) -> {
			return new Customer(item.getId(), item.getFirstName().toUpperCase(), item.getLastName().toUpperCase(),
					item.getBirthDate());
		};
		return processor;
	}

	@Bean
	public ItemProcessor<Customer, Customer> filterItemProcesser() {
		ItemProcessor<Customer, Customer> processor = (Customer item) -> {
			if (item.getId() % 3 == 0) {
				return null;
			} else {
				return item;
			}
		};
		return processor;
	}

	@Bean
	public ValidatingItemProcessor<Customer> validationItemProcessor() {
		ValidatingItemProcessor<Customer> customerValidatingItemProcessor = new ValidatingItemProcessor<>(
				(Customer value) -> {
					if (value.getFirstName().startsWith("A")) {
						throw new ValidationException("First names that begin with A are invalid: " + value);
					}
				});
		/**
		 * This property will set the flow even there is an exception. The batches or
		 * the Steps will not fail in this case
		 */
		customerValidatingItemProcessor.setFilter(true);
		return customerValidatingItemProcessor;
	}

	@Bean
	public CompositeItemProcessor<Customer, Customer> compositeItemProcesser() throws Exception {
		List<ItemProcessor<Customer, Customer>> delegates = Arrays.asList(filterItemProcesser(), itemProcesser());
		CompositeItemProcessor<Customer, Customer> compositeItemProcessor = new CompositeItemProcessor<Customer, Customer>();
		compositeItemProcessor.setDelegates(delegates);
		compositeItemProcessor.afterPropertiesSet();
		return compositeItemProcessor;
	}

	/**
	 * This is the Steps.
	 * 
	 * @return
	 * @throws Exception
	 */

	@Bean
	public Step step1() throws Exception {
		int value = random.nextInt(10);
		return stepBuilderFactory.get("stepReaderCustomerStart-" + value).<Customer, Customer>chunk(100)
				/*.reader(customItemReader())*/
				.reader(pagingItemReader())
				/*.<Customer, Customer>processor(itemProcesser())*/
				/*.<Customer, Customer>processor(filterItemProcesser())*/
				/*.<Customer, Customer>processor(validationItemProcessor())*/
				/*.<Customer, Customer>processor(compositeItemProcesser())*/
				/*.writer(customItemWriter())*/
				/*.writer(customerXMLItemWriter())*/
				/*.writer(customerJSONItemWriter())*/
				/*.writer(compositeItemWriter())*/
				.writer(classifierCompositeItemWriter()).stream(customerXMLItemWriter())
				.stream(customerJSONItemWriter()).build();
	}

	/**
	 * This is the JOB section
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Job listenerJob() throws Exception {
		int value = random.nextInt(10);
		return jobBuilderFactory.get("CustomCustomerReadWriteJobConfiguration-" + value).start(step1()).build();
	}

}
