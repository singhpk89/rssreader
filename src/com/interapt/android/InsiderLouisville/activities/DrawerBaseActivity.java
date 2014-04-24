package com.interapt.android.InsiderLouisville.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class DrawerBaseActivity extends  SherlockFragmentActivity
{
	
	

	private static Activity baseActivity;

	public static Activity getActivity() {
		return baseActivity;
	}

	public static void setActivity(Activity activity) {
		DrawerBaseActivity.baseActivity = activity;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}




}
