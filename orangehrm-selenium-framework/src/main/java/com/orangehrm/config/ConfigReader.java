package com.orangehrm.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;

	public ConfigReader() throws Throwable, Exception {
		try (FileInputStream ip = new FileInputStream(
				"C:\\Users\\Mukesh_Rajbhar\\eclipse-workspace\\orangehrm-selenium-framework\\src\\main\\resources\\config.properties")) {
			prop = new Properties();
			prop.load(ip);

		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}
