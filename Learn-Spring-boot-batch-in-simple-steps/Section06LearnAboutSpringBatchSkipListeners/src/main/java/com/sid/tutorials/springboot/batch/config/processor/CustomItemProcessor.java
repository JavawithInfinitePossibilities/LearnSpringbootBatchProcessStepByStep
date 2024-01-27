/**
 * 
 */
package com.sid.tutorials.springboot.batch.config.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.customException.MyCustomException;

/**
 * @author kunmu
 *
 */
@Component
public class CustomItemProcessor implements ItemProcessor<Car, Car> {

	@Override
	public Car process(Car car) throws MyCustomException {
		System.out.println("===============>This is the Processer class !!!!");
		if (car.getYear() > 2000) {
			return car;
		} else {
			System.out.println("===============>This is the Processer class error section !!!!");
			throw new MyCustomException("This model is old. We are not accept this model!!!");
		}
	}

}
