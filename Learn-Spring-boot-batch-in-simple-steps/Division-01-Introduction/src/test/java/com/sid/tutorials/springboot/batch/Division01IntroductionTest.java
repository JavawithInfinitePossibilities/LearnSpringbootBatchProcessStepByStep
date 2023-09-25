package com.sid.tutorials.springboot.batch;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = Division01Introduction.class)
@SpringBatchTest
@Import(MyTestConfigClass.class)
public class Division01IntroductionTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
