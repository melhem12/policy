package com.policy.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Utility {
	
	//private static final String FILE_CONFIG_LOCATION = "C://Users//User//Desktop//Validator.properties";
private static final String FILE_CONFIG_LOCATION = "C://appsfiles//dataTransferValidator//dataTransferValidator.properties";
	private static final String EMPTY_STRING_VALUE = "";

	public static String getPropStringValues(String propertuValue) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(FILE_CONFIG_LOCATION);
			properties.load(inputStream);
			String value = properties.getProperty(propertuValue);
			if (!isEmpty(value)) {
				return value;
			}
			return propertuValue;
		} catch (Exception e) {
			e.printStackTrace();
			return propertuValue;
		}

	}

	public static String getMainDir(String propertuValue) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(FILE_CONFIG_LOCATION);
			properties.load(inputStream);
			String value = properties.getProperty(propertuValue);
			if (!isEmpty(value)) {
				return value;
			}
			return "C:";
		} catch (Exception e) {
			e.printStackTrace();
			return "C:";
		}

	}

	public static Integer getPropIntegerValues(String propertuValue) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(FILE_CONFIG_LOCATION);
			properties.load(inputStream);
			String value = properties.getProperty(propertuValue);
			if (!isEmpty(value)) {
				return Integer.valueOf(value);
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public static boolean isEmpty(String str) {
		return (str == null || EMPTY_STRING_VALUE.equals(str.trim()));
	}

	public static boolean isProxyEnable(String str) {

		String value = getPropStringValues(str);
		if (value != null && !"".equals(value) && "true".equalsIgnoreCase(value)) {
			return true;
		}

		return false;
	}
	


}
