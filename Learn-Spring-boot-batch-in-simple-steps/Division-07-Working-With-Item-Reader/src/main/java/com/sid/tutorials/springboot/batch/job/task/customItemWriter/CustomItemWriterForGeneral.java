/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.task.customItemWriter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
