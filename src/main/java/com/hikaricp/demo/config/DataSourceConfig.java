package com.hikaricp.demo.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);
	
	@Bean
	@Primary
	/*
	 * Put spring-boot-configuration-processor dependency in pom.xml to remove this warning.
	 */
	@ConfigurationProperties("datasource.appds")
	public DataSourceProperties dataSourceProperties() {
		LOG.info("Preparing DataSourceProperties...");
		return new DataSourceProperties();
	}

	@Bean("appDataSource")
	public DataSource dataSource() {
		LOG.info("Initializing DataSource...");
		DataSourceProperties dsp = dataSourceProperties();
		return dataSourceProperties().initializeDataSourceBuilder().build();
	}
}
