/**
 * 
 */
package com.sid.tutorials.springboot.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * @author Lenovo
 *
 */
@SpringBootTest(classes = Section06LearnAboutSpringBatchListeners.class)
@Import(MyTestConfiguration.class)
public class Section06LearnAboutSpringBatchListenersTest {

	@Autowired
	@Qualifier("jobBatchListener")
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	void contextLoads() throws Exception {
		JobExecution launchJob = jobLauncherTestUtils.launchJob();
		assertEquals("ListenerJobConfiguration", launchJob.getJobInstance().getJobName());
		assertEquals(ExitStatus.COMPLETED, launchJob.getExitStatus());
	}
}
