package com.interapt.android.InsiderLouisville.commons;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AppIntentsHelper {


	public static void openFacebook(Context ctx,String id, String url){



		try {
			//WfpkApp.instance().getPackageManager().getPackageInfo("com.facebook.katana", 0);
			ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/"+id)));
		} catch (Exception e) {
			OpenInAppHelper.openWeb(ctx, url);
			//ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
		}
	}

	public static void openTwitter(Context ctx,String id, String url){

		try {
			ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id="+id)));
		} catch (Exception e) {
			OpenInAppHelper.openWeb(ctx, url);
			//ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
		}
	}

	public static void openSoundCloud(Context ctx,String id, String url){

		try {
			ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("soundcloud"/*"soundcloud://tracks:" + id*/)));
		} catch (Exception e) {
			OpenInAppHelper.openWeb(ctx, url);
			//ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
		}
	}
	public static void openInstagram(Context ctx,String id, String url){

		/*try {
			ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=wfpk""instagram://media?id=" + id)));
		} catch (Exception e) {
			ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
		}*/

		try {
			Intent iIntent = ctx.getPackageManager().getLaunchIntentForPackage("com.instagram.android");

			iIntent.setComponent(new ComponentName( "com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
			iIntent.setData( Uri.parse( "http://instagram.com/919wfpk") );

			ctx.startActivity(iIntent);
		} catch (Exception e) {
			OpenInAppHelper.openWeb(ctx, url);
			//ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));		
		}

	}

}

