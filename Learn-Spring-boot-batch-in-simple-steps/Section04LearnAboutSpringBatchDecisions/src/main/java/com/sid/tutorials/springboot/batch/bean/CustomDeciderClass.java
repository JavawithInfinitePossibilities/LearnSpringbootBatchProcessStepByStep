/**
 * 
 */
package com.sid.tutorials.springboot.batch.bean;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

/**
 * @author Lenovo
 *
 */
@Component
public class CustomDeciderClass {
	int nextInt = (int) (Math.random()*10);

	public JobExecutionDecider customDecider() {
		return (JobExecution jobExecution, StepExecution stepExecution) -> {
			nextInt++;
			System.out.println("Count value : " + nextInt);
			if (nextInt % 2 == 0) {
				System.out.println("EVEN");
				return new FlowExecutionStatus("EVEN");
				/*return FlowExecutionStatus.COMPLETED;*/
			} else {
				System.out.println("ODD");
				return new FlowExecutionStatus("ODD");
			}
		};
	}
}
