/**
 * 
 */
package com.sid.tutorials.springboot.batch.mockData;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resources;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.bean.Person;

/**
 * @author kunmu
 *
 */
@Component
public class MockDataPrep {
	public List<Person> getPeople() throws IOException {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("people.json");
		String json = IOUtils.toString(inputStream);
		Type listType = new TypeToken<ArrayList<Person>>() {
		}.getType();
		List<Person> people = new Gson().fromJson(json, listType);
		return people;
	}

	public List<Car> getCars() throws IOException {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("cars.json");
		String json = IOUtils.toString(inputStream);
		Type listType = new TypeToken<ArrayList<Car>>() {
		}.getType();
		List<Car> cars = new Gson().fromJson(json, listType);
		return cars;
	}
}
