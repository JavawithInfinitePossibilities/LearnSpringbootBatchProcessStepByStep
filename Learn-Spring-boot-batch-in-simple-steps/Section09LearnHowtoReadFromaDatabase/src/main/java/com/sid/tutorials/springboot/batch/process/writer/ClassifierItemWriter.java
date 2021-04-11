/**
 * 
 */
package com.sid.tutorials.springboot.batch.process.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import com.sid.tutorials.springboot.batch.module.db.Customer;

/**
 * @author Lenovo
 *
 */
public class ClassifierItemWriter implements Classifier<Customer, ItemWriter<? super Customer>> {

	private ItemWriter<Customer> customerXMLItemWriter;
	private ItemWriter<Customer> customerJSONItemWriter;

	/**
	 * @param customerXMLItemWriter
	 * @param customerJSONItemWriter
	 */
	public ClassifierItemWriter(ItemWriter<Customer> customerXMLItemWriter,
			ItemWriter<Customer> customerJSONItemWriter) {
		super();
		this.customerXMLItemWriter = customerXMLItemWriter;
		this.customerJSONItemWriter = customerJSONItemWriter;
	}

	@Override
	public ItemWriter<? super Customer> classify(Customer classifiable) {
		return classifiable.getId() % 2 == 0 ? customerXMLItemWriter : customerJSONItemWriter;
	}

}
