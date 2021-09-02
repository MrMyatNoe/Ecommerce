package com.demo.ecom.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static String path=  "D:/Ecommerce/Images";
	public static final String testPath = System.getProperty("user.home");
	public static String remarkForDaily = "-";

	public static String dateFormatYearMonthDay() {
		return sdf.format(new Date()).toString();
	}
	
	public static String getSystemPhotoPath() {
	    final String window = "Window";
        //final String linux = "Linux";
        if (System.getProperty("os.name").equalsIgnoreCase(window)) {
            path = "D:/Ecommerce/Images";
        } else {
            path = "/home/tmn/git/Ecommerce/src/main/resources/static/images";
        }
        return path;
	}
	
}
