package com.hikaricp.demo.controller;

import java.util.Objects;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

	private static final String QUERY = "SELECT COUNT(1) FROM HELP";

	//@Autowired
	//private HikariDataSource appDataSource;
	
	@Autowired
	private DataSource appDataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/rows")
	public Long getRowsFromHelpTable() {
		return jdbcTemplate.queryForObject(QUERY, Long.class);
	}

	@GetMapping("/checkds")
	public Boolean checkDataSourceStatus() {
		return Objects.nonNull(appDataSource);
	}

	/*@GetMapping("/dsprops")
	public String getDataSourceProperties() {
		if (Objects.nonNull(appDataSource)) {
			StringBuilder propsBuilder = new StringBuilder("URL: ");
			propsBuilder.append(appDataSource.getJdbcUrl()).append("\n");
			propsBuilder.append("USERNAME: ").append(appDataSource.getUsername()).append("\n");
			propsBuilder.append("PASSWORD: ").append(appDataSource.getPassword()).append("\n");
			propsBuilder.append("DRIVER CLASS NAME: ").append(appDataSource.getDriverClassName()).append("\n");
			propsBuilder.append("MAXIMUM POOL SIZE: ").append(appDataSource.getMaximumPoolSize()).append("\n");
			propsBuilder.append("MINIMUM IDLE: ").append(appDataSource.getMinimumIdle()).append("\n");
			propsBuilder.append("MAXIMUM LIFE TIME: ").append(appDataSource.getMaxLifetime()).append("\n");
			propsBuilder.append("IS AUTO COMMIT: ").append(appDataSource.isAutoCommit());
			LOG.info(propsBuilder.toString());
			return propsBuilder.toString();
		}
		return "DataSource was null!";
	}*/
	
	/*
	 * To check the working of Shutdown hook.
	 */
	@GetMapping("/shutdown")
	public void restartApplication() {
		LOG.info("Shutting down application...");
		System.exit(0);
	}
}
