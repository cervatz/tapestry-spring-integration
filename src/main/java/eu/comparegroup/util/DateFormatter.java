package eu.comparegroup.util;

import java.util.Date;
import org.joda.time.DateTime;

public class DateFormatter {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String getCurrentDate() {
		return new DateTime(new Date()).toString(DATE_FORMAT);
	}

	public static Date parseDate(String dateString) {
		DateTime dateTime = new DateTime(dateString);
		return dateTime.toDate();
	}
}
