/**
 * 
 */
package com.sid.tutorials.springboot.batch.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.sid.tutorials.springboot.batch.Section03LearnAboutSpringBatchFlows;

/**
 * @author Lenovo
 *
 */
@SpringBootTest(classes = Section03LearnAboutSpringBatchFlows.class)
@Import(MyTestConfigClass.class)
public class Section03LearnAboutSpringBatchFlowsTest {

	@Autowired
	@Qualifier("myJobLauncherTestUtilsJob1")
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testMyFirstMultiJob() throws Exception {
		JobExecution launchJob = jobLauncherTestUtils.launchJob();
		assertEquals("SixthFirstSpringBatchProject", launchJob.getJobInstance().getJobName());
		assertEquals(ExitStatus.COMPLETED, launchJob.getExitStatus());
	}
}
