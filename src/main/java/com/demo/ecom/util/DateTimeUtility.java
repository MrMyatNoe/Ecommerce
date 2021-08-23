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
	// OS validator
	public static String path=  "D:/Ecommerce/Images";

	public static String dateFormatYearMonthDay() {
		return sdf.format(new Date()).toString();
	}
	
	public static String getSystemPhotoPath() {
	    final String window = "Window";
        //final String linux = "Linux";
        if (System.getProperty("os.name").equalsIgnoreCase(window)) {
            path = "D:/Ecommerce/Images";
        } else {
            path = "/home/tmn/public/Ecommerce/Images";
        }
        return path;
	}
	
}
