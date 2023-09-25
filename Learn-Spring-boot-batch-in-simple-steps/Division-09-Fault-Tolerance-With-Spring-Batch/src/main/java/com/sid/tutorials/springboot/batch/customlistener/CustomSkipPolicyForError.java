/**
 * 
 */
package com.sid.tutorials.springboot.batch.customlistener;

import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Date;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.bean.GeneralBean;

/**
 * @author kunmu
 *
 */
@Component
public class CustomSkipPolicyForError {

	@OnSkipInRead
	public void onSkipInRead(Throwable th) {
		String path = System.getProperty("user.dir") + "/src/main/resources/output/ReaderErrorRecord/onSkipInRead.txt";
		createErrorRecord(path, th.getMessage());
	}

	@OnSkipInWrite
	public void onSkipInWrite(GeneralBean item, Throwable th) {
		String path = System.getProperty("user.dir") + "/src/main/resources/output/WriterErrorRecord/onSkipInWrite.txt";
		createErrorRecord(path, item.toString());
	}

	@OnSkipInProcess
	public void onSkipInProcess(GeneralBean item, Throwable th) {
		String path = System.getProperty("user.dir")
				+ "/src/main/resources/output/ProcessorErrorRecord/onSkipInProcess.txt";
		createErrorRecord(path, item.toString());
	}

	private void createErrorRecord(String path, String data) {
		try (FileWriter fileWriter = new FileWriter(path, true)) {
			fileWriter.write(data + " | " + new Date().toString() + System.lineSeparator());
		} catch (Exception e) {
		}
	}
}
