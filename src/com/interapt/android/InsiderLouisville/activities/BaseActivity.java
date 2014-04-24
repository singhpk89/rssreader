package com.interapt.android.InsiderLouisville.activities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.aquevix.framework.app.AQBaseActivity;

public class BaseActivity extends AQBaseActivity{
	
	//HeaderViews
	protected LinearLayout leftHeaderView;
	protected TextView headerTitle;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
	}

	
	
	public static String getFormatedDateString(String dateStr)
	{//Wed, 23 Apr 2014 20:25:15 +0000
		SimpleDateFormat parseFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
		SimpleDateFormat outFormat = new SimpleDateFormat("MMMM dd yyyy hh:mm a");
		Date date = new Date();
		try {
			date = parseFormat.parse(dateStr);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ouString = outFormat.format(date);
		return ouString;
		
	}
	
}
