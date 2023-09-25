/**
 * 
 */
package com.sid.tutorials.springboot.batch.job.task.customItemReader;

import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.sid.tutorials.springboot.batch.bean.Car;
import com.sid.tutorials.springboot.batch.bean.Customer;
import com.sid.tutorials.springboot.batch.bean.GeneralBean;

/**
 * @author kunmu
 *
 */
@Configuration
public class CustomItemReaderForFlatFile {

	@Autowired
	@Qualifier("springBatchCoreRepository")
	private DataSource dataSource;

	@Autowired
	@Qualifier("springBatchCoreDataRepository")
	private DataSource dataSourceDataRepository;

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

	@Bean
	@StepScope
	public JsonItemReader<Car> jsonItemReader(@Value("#{jobParameters['inputFiles']}") ClassPathResource resource) {
		JsonItemReader<Car> jsonItemReader = new JsonItemReader<>();
		jsonItemReader.setResource(resource);
		jsonItemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(Car.class));
		return jsonItemReader;
	}

	@Bean
	@StepScope
	public ItemReader<Customer> jdbcItemReaderCustomer(
			@Value("#{jobParameters['inputFiles']}") ClassPathResource resource) {
		JdbcCursorItemReader<Customer> jdbcCursorItemReader = new JdbcCursorItemReader<>();
		jdbcCursorItemReader.setDataSource(dataSourceDataRepository);
		jdbcCursorItemReader.setSql("select id, firstName, lastName, birthdate from customer order by id");
		jdbcCursorItemReader.setRowMapper((ResultSet resultSet, int rowNum) -> {
			return new Customer(resultSet.getLong("id"), resultSet.getString("firstName"),
					resultSet.getString("lastName"), resultSet.getDate("birthdate"));
		});
		return jdbcCursorItemReader;
	}

	@Bean
	@StepScope
	public JdbcCursorItemReader<? extends GeneralBean> jdbcGeneralItemReaderCustomer(
			@Value("#{jobParameters['inputFiles']}") ClassPathResource resource) {
		JdbcCursorItemReader<Customer> jdbcCursorItemReader = new JdbcCursorItemReader<>();
		jdbcCursorItemReader.setDataSource(dataSourceDataRepository);
		jdbcCursorItemReader.setSql("select id, firstName, lastName, birthdate from customer order by id");
		jdbcCursorItemReader.setRowMapper((ResultSet resultSet, int rowNum) -> {
			return new Customer(resultSet.getLong("id"), resultSet.getString("firstName"),
					resultSet.getString("lastName"), resultSet.getDate("birthdate"));
		});
		return jdbcCursorItemReader;
	}
}
