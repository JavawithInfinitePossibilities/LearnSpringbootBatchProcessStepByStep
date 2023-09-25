package com.sid.tutorials.springboot.batch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = Division02SettingUpenvironment.class)
@SpringBatchTest
@Import(MyTestConfigClass.class)
class Division02SettingUpenvironmentTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
