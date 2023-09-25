/**
 * 
 */
package com.sid.tutorials.springboot.batch.mycontroller;

import java.util.HashMap;
import java.util.Map;

import javax.batch.operations.JobOperator;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kunmu <br/>
 *         Postman Get <br/>
 *         http://localhost:8080/startjob/First-Job
 *
 */
@RestController
public class MyControllerClass {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("firstJob")
	private Job firstJob;

	@RequestMapping(value = "/startjob/{jobName}", method = RequestMethod.GET)
	public String startTheJob(@PathVariable String jobName) throws JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> parameters = new HashMap<>();
		parameters.put("current-time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(parameters);
		if (jobName.equals("First-Job")) {
			this.jobLauncher.run(firstJob, jobParameters);
		}
		return "Job started!!!";

	}
}
