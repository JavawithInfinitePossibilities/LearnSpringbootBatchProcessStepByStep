/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.task.customItemReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
@Component
public class CustomItemReader implements ItemReader<Integer> {
	List<Integer> myList = IntStream.iterate(1000, i -> i >= 0, i -> i - 10).mapToObj(i -> i)
			.collect(Collectors.toList());
	int index = 0;

	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Inside the Item reader");
		if (index < myList.size()) {
			return myList.get(index++);
		}
		index = 0;
		return null;
	}

}
