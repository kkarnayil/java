package com.kk.excelreadpoc.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public enum ApplicationProperties {
	INSTANCE;

	private final Properties properties;

	ApplicationProperties() {

		Logger logger = LogManager.getLogger(ApplicationProperties.class);

		properties = new Properties();
		try {
			logger.debug("Reading properties file :: application.properties");
			properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			logger.debug("Error while reading properties file", e);
		}
	}

	public String getValue(final String key) {
		return properties.getProperty(key);
	}
}