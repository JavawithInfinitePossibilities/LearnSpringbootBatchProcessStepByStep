/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.task.customItemReader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.sid.tutorials.springboot.batch.bean.Car;

/**
 * @author kunmu
 *
 */
@Configuration
public class CustomItemReaderForFlatFile {

	@Bean
	public FlatFileItemReader<Car> customFlatFileItemReaderForCSV() {
		FlatFileItemReader<Car> fileItemReaderCSV = new FlatFileItemReader<>();
		Resource resource = new ClassPathResource("/data/MOCK_DATA.csv");
		fileItemReaderCSV.setResource(resource);
		DefaultLineMapper<Car> defaultLineMapperCar = new DefaultLineMapper<>();
		// Line tokenizer
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames("ID", "MAKE", "MODEL", "COLOR", "YEAR", "PRICE");
		defaultLineMapperCar.setLineTokenizer(delimitedLineTokenizer);
		// bean Mapper
		defaultLineMapperCar.setFieldSetMapper((FieldSet fieldSet) -> {
			return new Car(fieldSet.readInt("ID"), fieldSet.readString("MAKE"), fieldSet.readString("MODEL"),
					fieldSet.readString("COLOR"), fieldSet.readInt("YEAR"), fieldSet.readDouble("PRICE"));
		});
		defaultLineMapperCar.afterPropertiesSet();
		// Setting up the Line Mapper
		fileItemReaderCSV.setLineMapper(defaultLineMapperCar);
		fileItemReaderCSV.setLinesToSkip(1);
		return fileItemReaderCSV;
	}
}
