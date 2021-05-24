package com.kk.excelreadpoc.utils;

/**
 * @author kartikkarnayil
 *
 */
public class CommonUtils {

	private CommonUtils() {
	}

	/**
	 * Method to return double value formatted to string value
	 * 
	 * @param doubleValue
	 * @return String value
	 */
	public static String getStringValue(double doubleValue) {

		return String.format("%.0f", doubleValue);
	}
}
