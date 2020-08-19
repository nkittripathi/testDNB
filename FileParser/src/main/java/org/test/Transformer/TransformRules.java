package org.test.Transformer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.test.JSONParser.JSONParser;

public class TransformRules {

	private static Logger log = Logger.getLogger(JSONParser.class);
	public static String activityDescription(int code) {

		String activity_desc="";     
		switch(code) {
		case 1 :
			activity_desc="Viewed";
			break;
		case 2 :
			activity_desc="Purchased";
			break;
		default :
			log.warn("No Other Code is supported");
		}
		return activity_desc;

	}

	public static String jsonDateFormat(String date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String formattedDate = null;
		Date convertedDate = new Date();
		try {
			convertedDate = dateFormat.parse(date);
			System.out.println(date);
			formattedDate = targetFormat.format(convertedDate);
		} catch (ParseException e) {
			log.error("Failed to parse date :: " + e.getMessage());
		}
		return formattedDate;
	}

	public static String xmlDateFormat(String date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String formattedDate = null;
		Date convertedDate = new Date();
		try {
			convertedDate = dateFormat.parse(date);
			System.out.println(date);
			formattedDate = targetFormat.format(convertedDate);
		} catch (ParseException e) {
			log.error("Failed to parse date :: " + e.getMessage());
		}
		return formattedDate;

	}

}
