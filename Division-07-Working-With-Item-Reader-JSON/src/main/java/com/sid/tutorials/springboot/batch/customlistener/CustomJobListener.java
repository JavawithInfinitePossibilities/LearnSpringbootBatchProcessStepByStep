/**
 * 
 */
package com.sid.tutorials.springboot.batch.customlistener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
@Component
public class CustomJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("CustomJobListener beforeJob:" + jobExecution.getJobInstance().getJobName());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("CustomJobListener afterJob:" + jobExecution.getJobInstance().getJobName());
	}

}
