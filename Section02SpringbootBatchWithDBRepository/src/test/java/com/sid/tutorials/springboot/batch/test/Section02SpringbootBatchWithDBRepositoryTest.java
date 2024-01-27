/**
 * 
 */
package com.sid.tutorials.springboot.batch.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sid.tutorials.springboot.batch.Section02SpringbootBatchWithDBRepository;

/**
 * @author Lenovo
 *
 */
@SpringBootTest(classes = Section02SpringbootBatchWithDBRepository.class)
@SpringBatchTest
public class Section02SpringbootBatchWithDBRepositoryTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepository jobRepository;

	@Test
	void contextLoads() throws Exception {
		JobExecution launchJob = jobLauncherTestUtils.launchJob();
		ExitStatus actualJobExitStatus = launchJob.getExitStatus();
		if (actualJobExitStatus.equals(ExitStatus.STOPPED)) {
			launchJob = jobLauncherTestUtils.launchJob();
		}
		JobInstance actualJobInstance = launchJob.getJobInstance();

		assertEquals(actualJobInstance.getJobName(), "ThirdSpringBatchProject");
		assertEquals(ExitStatus.COMPLETED, actualJobExitStatus);
	}
}
