package com.sid.tutorials.springboot.batch;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = Division07WorkingWithItemReaderJSON.class)
@SpringBatchTest
@Import(MyTestConfigClass.class)
class Division07WorkingWithItemReaderJSONTest {

	@Autowired
	@Qualifier("customJoblauncherTestutils")
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Disabled
	@Test
	void test() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addString("inputFiles", "cars.json").toJobParameters();
		JobExecution launchJob = jobLauncherTestUtils.launchJob(jobParameters);
		assertEquals("First-Job", launchJob.getJobInstance().getJobName());
		assertEquals(ExitStatus.COMPLETED, launchJob.getExitStatus());
	}

	@Test
	void testStep() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addString("inputFiles", "cars.json").toJobParameters();
		JobExecution launchJob = jobLauncherTestUtils.launchStep("fourth-Step", jobParameters);
		launchJob.getStepExecutions().forEach(item -> System.out.println("Step name is : " + item.getStepName()));
		assertEquals("fourth-Step", ((List<StepExecution>) launchJob.getStepExecutions()).get(0).getStepName());
		assertEquals(ExitStatus.COMPLETED, launchJob.getExitStatus());
	}

}
