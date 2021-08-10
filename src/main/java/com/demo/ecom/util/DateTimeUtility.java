package com.demo.ecom.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * in window
	 */
	//public static final String path=  "D:/Ecommerce/Images";
	/**
	 * in linux
	 */
	public static final String path=  "D:/Ecommerce/Images";

	public static String dateFormatYearMonthDay() {
		return sdf.format(new Date()).toString();
	}
	
}
