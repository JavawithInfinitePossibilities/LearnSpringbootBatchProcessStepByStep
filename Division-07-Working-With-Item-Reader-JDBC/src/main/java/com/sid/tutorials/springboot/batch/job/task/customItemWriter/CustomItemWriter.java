/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.task.customItemWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
@Component
public class CustomItemWriter implements ItemWriter<Long> {

	@Override
	public void write(List<? extends Long> items) throws Exception {
		System.out.println("Inside the Item Writer");
		items.stream().forEach(System.out::println);
	}

}
