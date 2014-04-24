package com.interapt.android.InsiderLouisville.utils;


import com.interapt.android.InsiderLouisville.R;

import android.app.ProgressDialog;

import android.content.Context;
import android.view.WindowManager.BadTokenException;



public class Progresss {
	static ProgressDialog dialog;

	public static void start(Context context) {
		dialog = new ProgressDialog(context);

		dialog.show();

		dialog.setCancelable(false);
		dialog.setContentView(R.layout.progressdialog123);
		
	}

	public static void stop() {
		if(dialog!=null)
			dialog.dismiss();
	}

}
