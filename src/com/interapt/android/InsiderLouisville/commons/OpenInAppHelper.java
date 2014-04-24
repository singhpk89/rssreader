package com.interapt.android.InsiderLouisville.commons;

import android.content.Context;

import android.content.Intent;

import com.interapt.android.InsiderLouisville.activities.WebviewActivity;

public class OpenInAppHelper {

	public static void openWeb(Context ctx,String url){
		if (url!=null) {
			
				Intent intent = new Intent(ctx, WebviewActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("url", url);
				ctx.startActivity(intent);
		}
	}
}
