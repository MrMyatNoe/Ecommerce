package com.demo.ecom.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static String dateFormatYearMonthDay() {
		return sdf.format(new Date()).toString();
	}
}
