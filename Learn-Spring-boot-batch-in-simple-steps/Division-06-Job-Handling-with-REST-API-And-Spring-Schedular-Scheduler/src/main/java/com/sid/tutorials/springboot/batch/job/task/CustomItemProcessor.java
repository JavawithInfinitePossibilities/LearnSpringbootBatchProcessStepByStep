/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.task;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
@Component
public class CustomItemProcessor implements ItemProcessor<Integer, Long> {

	@Override
	public Long process(Integer item) throws Exception {
		System.out.println("Inside the processor");
		return Long.valueOf(item + 50);
	}

}
