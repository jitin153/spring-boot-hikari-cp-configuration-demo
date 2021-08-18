package com.hikaricp.demo.config;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ApplicationShutdownHook {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationShutdownHook.class);

	@Autowired
	@Qualifier("appDataSource")
	private HikariDataSource hikariDataSource;

	@PreDestroy
	public void onShutdown() throws Exception {
		LOG.info("Closing hikari data source...");
		hikariDataSource.close();
		LOG.info("Hikari data source closed!");
	}
}
