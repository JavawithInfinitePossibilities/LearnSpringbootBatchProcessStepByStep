/**
 * 
 */
package com.sid.tutorials.springboot.batch.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author kunmu
 *
 */
@Configuration
public class DataSourcesConfigDetails {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource springBatchCoreRepository() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.customer.datasource")
	public DataSource springBatchCoreDataRepository() {
		return DataSourceBuilder.create().build();
	}
}
