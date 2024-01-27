package com.sid.tutorials.springboot.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = Division03IntroductionToSpringBatchMySqlDB.class)
@SpringBatchTest
@Import(MyTestConfigClass.class)
class Division03IntroductionToSpringBatchMySqlDBTest {

	@Autowired
	@Qualifier("customJoblauncherTestutils")
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	void test() throws Exception {
		JobExecution launchJob = jobLauncherTestUtils.launchJob();
		assertEquals("First-Job", launchJob.getJobInstance().getJobName());
		assertEquals(ExitStatus.COMPLETED, launchJob.getExitStatus());
	}

}
