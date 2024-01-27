/**
 * 
 */
package com.sid.tutorials.springboot.batch.config.writer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sid.tutorials.springboot.batch.bean.Car;

/**
 * @author kunmu
 *
 */
@Component
public class CustomItemWriter implements ItemWriter<Car> {
	private static final String NEW_LINE = System.lineSeparator();

	@Override
	public void write(List<? extends Car> items) throws Exception {
		System.out.println("===============>This is the Writer class !!!!");
		ObjectMapper objectMapper = new ObjectMapper();
		String rootPath = System.getProperty("user.dir");
		Path path = Paths.get(StringUtils.join(rootPath, "/src/main/resources/outputdirectory/", "result.out"));
		items.stream().forEach((car) -> {
			try {
				Files.writeString(path, objectMapper.writeValueAsString(car) + NEW_LINE, StandardCharsets.UTF_8,
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

}
