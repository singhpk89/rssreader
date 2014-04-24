package com.interapt.android.InsiderLouisville.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SimpleDateFormatter {

	public Date getCurrentTimeGMT(Date date){
		
		Calendar c = Calendar.getInstance();
		int utcOffset = c.get(Calendar.ZONE_OFFSET) + c.get(Calendar.DST_OFFSET);  
		c.setTime(date);
		Long utcMilliseconds = c.getTimeInMillis() + utcOffset;
		c.setTimeInMillis(utcMilliseconds);
		return c.getTime();

	}
	
	public String ChangeFormatInDayDateTime(String message,String outputFormat)
	{
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat(outputFormat);
		//"E, MMM d, yyyy hh:mm aaa"
		Date date;
		try {
			date = format1.parse(message);
			date=getCurrentTimeGMT(date);
			message=format2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

}
