/**
 * 
 */
package com.sid.tutorials.springboot.batch.bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
@Component
public class CustomErrorItem {

	private Map<Car, List<String>> errorCars;

	@PostConstruct
	private void initErrorCars() {
		this.errorCars = new HashMap<>();
	}

	public Map<Car, List<String>> getErrorCars() {
		return errorCars;
	}

	public void setErrorCars(Car car, String errormessage) {
		if (errorCars.containsKey(car)) {
			errorCars.get(car).add(errormessage);
		} else {
			errorCars.put(car, Arrays.asList(errormessage));
		}
	}
}
