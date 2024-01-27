package com.sid.tutorials.springboot.batch;

import java.io.IOException;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.bean.Person;
import com.sid.tutorials.springboot.batch.mockdata.MockDataPrep;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = Section01Welcome.class)
@SpringBatchTest
public class Section01WelcomeTest extends TestCase {
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Value("${Welcome.To.Springboot.Batch}")
	private String welcomeMessage;

	@Autowired
	private MockDataPrep mockDataPrep;

	@Disabled
	@Test
	public void testInitialJob() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		JobInstance actualJobInstance = jobExecution.getJobInstance();
		ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

		assertEquals(actualJobInstance.getJobName(), "FirstSpringBatchProject");
		assertEquals(ExitStatus.COMPLETED, actualJobExitStatus);
	}

	@Disabled
	@Test
	public void testListOfPerson() {
		try {
			List<Person> people = mockDataPrep.getPeople();
			System.out.println("Size of the list is : " + people.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListOfCars() {
		try {
			List<Car> cars = mockDataPrep.getCars();
			System.out.println("Size of the list is : " + cars.size());
			System.out.println("============>"+welcomeMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
