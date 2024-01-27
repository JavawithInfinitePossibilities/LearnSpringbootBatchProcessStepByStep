package com.sid.tutorials.springboot.batch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = { Section06LearnAboutSpringBatchSkipListeners.class })
@Import(MyTestConfiguration.class)
class Section06LearnAboutSpringBatchSkipListenersTest {

	@Autowired
	@Qualifier("skipListenerComplexJobTestUtil")
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	void test() throws Exception {
		JobExecution launchJob = jobLauncherTestUtils.launchJob();
		assertEquals("SkipListenerComplexItemWriterJob", launchJob.getJobInstance().getJobName());
		assertEquals(ExitStatus.COMPLETED, launchJob.getExitStatus());
	}
}
