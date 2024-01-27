/**
 * 
 */
package com.sid.tutorials.springboot.batch.config.reader;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.mockData.MockDataPrep;

/**
 * @author kunmu
 *
 */
@Component
public class CustomItemReader implements ItemReader<Car> {

	@Autowired
	private MockDataPrep mockDataPrep;

	private List<Car> cars;

	@PostConstruct
	public void setCarList() throws IOException {
		this.cars = mockDataPrep.getCars();
	}

	@Override
	public Car read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("===============>This is the Reader class !!!!");
		if (!this.cars.isEmpty()) {
			return this.cars.remove(0);
		}
		return null;
	}

}
