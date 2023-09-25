/**
 * 
 */
package com.sid.tutorials.springboot.batch.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author kunmu
 *
 */
@Service
public class MyControllerServices {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("firstJob")
	private Job firstJob;

	@Async
	public void controllerServices(String jobName) throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> parameters = new HashMap<>();
		parameters.put("current-time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(parameters);
		if (jobName.equals("First-Job")) {
			JobExecution jobExecution = this.jobLauncher.run(firstJob, jobParameters);
			System.out.println("Job Execution ID : " + jobExecution.getId());
		}
	}
}
