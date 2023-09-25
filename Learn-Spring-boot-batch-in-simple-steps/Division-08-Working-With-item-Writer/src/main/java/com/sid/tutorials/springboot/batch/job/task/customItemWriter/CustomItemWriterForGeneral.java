/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.task.customItemWriter;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.sid.tutorials.springboot.batch.bean.Customer;
import com.sid.tutorials.springboot.batch.bean.GeneralBean;

/**
 * @author kunmu
 *
 */
@Configuration
public class CustomItemWriterForGeneral {

	@Bean
	public ItemWriter<String> customItemWriterIngeneral() {
		return items -> items.stream().forEach(System.out::println);
	}

	@Bean
	@StepScope
	public FlatFileItemWriter<? extends GeneralBean> customerItemWriterIngeneral(
			@Value("#{jobParameters['outputFile']}") String resource) throws Exception {
		FlatFileItemWriter<Customer> itemWriter = new FlatFileItemWriter<>();

		DelimitedLineAggregator<Customer> delimitedLineAggregator = new DelimitedLineAggregator<>();
		delimitedLineAggregator.setDelimiter(",");
		BeanWrapperFieldExtractor<Customer> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
		/*Didn't find any solution for the Data data type */
		beanWrapperFieldExtractor.setNames(new String[] { "id", "firstName", "lastName", "birthDate" });
		beanWrapperFieldExtractor.afterPropertiesSet();
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);

		/*this will set the column header for the CSV file */
		itemWriter.setHeaderCallback((Writer writer) -> {
			writer.write("id, first-Name, last-Name,Birth-date");
		});
		itemWriter.setLineAggregator(delimitedLineAggregator);
		itemWriter.setAppendAllowed(true);
		itemWriter.setShouldDeleteIfEmpty(true);
		itemWriter.setShouldDeleteIfExists(true);
		FileSystemResource fileSystemResource = new FileSystemResource("src/main/resources/output/" + resource);
		itemWriter.setResource(fileSystemResource);
		itemWriter.afterPropertiesSet();
		return itemWriter;
	}
}
