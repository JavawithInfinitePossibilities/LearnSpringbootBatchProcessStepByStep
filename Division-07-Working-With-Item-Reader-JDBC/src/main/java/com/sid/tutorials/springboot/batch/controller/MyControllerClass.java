/**
 * 
 */
package com.sid.tutorials.springboot.batch.controller;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sid.tutorials.springboot.batch.services.MyControllerServices;

/**
 * @author kunmu <br/>
 *         Postman Get <br/>
 *         http://localhost:8080/startjob/First-Job
 *         http://localhost:8080/startInputJob/cars.json
 *         http://localhost:8080/secondJob/Customer
 *
 */
@RestController
public class MyControllerClass {

	@Autowired
	private MyControllerServices myControllerServices;

	@RequestMapping(value = "/startjob/{jobName}", method = RequestMethod.GET)
	public String startTheJob(@PathVariable String jobName) throws JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		myControllerServices.controllerServices(jobName);
		return "Job started!!!";

	}

	@RequestMapping(value = "/startInputJob/{inputFiles}", method = RequestMethod.GET)
	public String firstJob(@PathVariable String inputFiles) throws JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		myControllerServices.controllerServices(inputFiles);
		return "Job started!!!";
	}

	@RequestMapping(value = "/secondJob/{inputFiles}", method = RequestMethod.GET)
	public String SecondJob(@PathVariable String inputFiles) throws JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		myControllerServices.controllerServices(inputFiles);
		return "Job started!!!";
	}
}
