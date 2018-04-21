package com.parkway.medical.appointment.util;

import java.sql.Date;

public class Util {
	
	public static String getDateTime(Date apptDate, String time) {
		String date = apptDate.toString();
		String times = time;
		String result = date.replaceAll("-", "") + times.replaceAll(":", "");
		return result;
	}


}
