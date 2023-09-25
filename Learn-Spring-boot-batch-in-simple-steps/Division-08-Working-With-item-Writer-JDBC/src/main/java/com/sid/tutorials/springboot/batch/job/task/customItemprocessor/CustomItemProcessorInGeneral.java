/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.task.customItemprocessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.bean.GeneralBean;

/**
 * @author kunmu
 *
 */
@Configuration
public class CustomItemProcessorInGeneral {

	@Bean
	public ItemProcessor<GeneralBean, String> customItemProcessorForAll() {
		return item -> item.toString();
	}

	@Bean
	public ItemProcessor<GeneralBean, GeneralBean> customerItemProcessorForAll() {
		return item -> item;
	}
}
