/**
 * 
 */
package com.sid.tutorials.springboot.batch.customlistener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
@Component
public class CustomStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("CustomStepListener beforeStep :" + stepExecution.getStepName());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("CustomStepListener afterStep :" + stepExecution.getStepName());
		return ExitStatus.COMPLETED;
	}

}
