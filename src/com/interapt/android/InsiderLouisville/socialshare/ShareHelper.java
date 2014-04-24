package com.interapt.android.InsiderLouisville.socialshare;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.aquevix.framework.socialmedia.EmailMessage;
import com.aquevix.framework.socialmedia.ISocialMedia;
import com.aquevix.framework.socialmedia.PhoneMessage;
import com.aquevix.framework.socialmedia.facebook.FacebookConnect;
import com.aquevix.framework.socialmedia.facebook.FacebookConnectListner;
import com.aquevix.framework.socialmedia.facebook.FacebookMessage;
import com.aquevix.framework.socialmedia.twitter.TwitterMessage;
import com.interapt.android.InsiderLouisville.InsiderLouisevilleApp;


public class ShareHelper {

	public static void doFacebookConnect(Activity activity, FacebookConnectListner listener){

		FacebookConnect fbConnect = new FacebookConnect(activity);
		fbConnect.Connect(listener);
	}

	public static  void doFacebookPost(Activity activity, String msg, String imgUrl){

		FacebookMessage iSocial=new FacebookMessage(activity)
		.setMessage(msg);
		//.setImageUrl(imgUrl);
		iSocial.Post();


	}

	public static void doTwitterPost(Activity activity, String msg, File imgUrl){

		//File f=new File(BitmapManager.getImageUriPathFromImageUrl(ShareImageActivity.this,PhotoFragment.strLink));
		ISocialMedia iSocial=new TwitterMessage(activity)
		.setMessage(msg)
		.setImage(imgUrl);
		iSocial.Post();
	}

	public static void sendSms(Activity activity, String msg){

		ISocialMedia iSocial=new PhoneMessage(activity)
		.setMessageText(msg);
		iSocial.Post();
	}

	public static void sendEmail(Activity activity,String[] mailReceivers, String subject, String msg, String imgUrl){

		ISocialMedia iSocial=new EmailMessage(activity)
		.setMailSubject(subject)
		.setMailReceivers(mailReceivers)
		.setMailBody(msg);
		//.setImagePath(new String[]{BitmapManager.getImageUriPathFromImageUrl(activity,PhotoFragment.strLink)});		
		iSocial.Post();
	}



	public static void sendShareMessage(int shareCode, Activity activity,
			String fbMsg,String twMsg, String mailMsg, String smsMsg){
		switch (shareCode) {
		case 0:
			if(facebookShareIntent(fbMsg)==null)
				ShareHelper.doFacebookPost(activity, fbMsg, null);
			else
				activity.startActivity(facebookShareIntent(fbMsg));
			break;
		case 1:
			if(twitterShareIntent(twMsg)==null)
				ShareHelper.doTwitterPost(activity, twMsg, null);
			else
				activity.startActivity(twitterShareIntent(twMsg));
			break;
		case 2:
			if(gmailShareIntent(mailMsg)==null)
				ShareHelper.sendEmail(activity, null, "", mailMsg, null);
			else
				activity.startActivity(gmailShareIntent(mailMsg));
			break;
		case 3:
			ShareHelper.sendSms(activity, smsMsg);
			break;
		default:
			break;
		}
	}

	public static Intent facebookShareIntent(String message){
		boolean appFound=false;
		Intent shareIntent = new Intent(
				android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,message);
		PackageManager pm = InsiderLouisevilleApp.instance().getPackageManager();
		List<ResolveInfo> activityList = pm.queryIntentActivities(
				shareIntent, 0);
		for (final ResolveInfo app : activityList) 
			if ((app.activityInfo.name.toUpperCase()).contains("facebook".toUpperCase())) {
				appFound=true;
				final ActivityInfo activity = app.activityInfo;
				final ComponentName name = new ComponentName(
						activity.applicationInfo.packageName,
						activity.name);
				shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				shareIntent.setComponent(name);
				break;
			}
		if(appFound)
			return shareIntent;
		else
			return null;
	}

	public static Intent twitterShareIntent(String message){
		boolean appFound=false;;
		Intent shareIntent = new Intent(
				android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,message);
		PackageManager pm = InsiderLouisevilleApp.instance().getPackageManager();
		List<ResolveInfo> activityList = pm.queryIntentActivities(
				shareIntent, 0);
		for (final ResolveInfo app : activityList) 
			if ((app.activityInfo.name.toUpperCase()).contains("twitter".toUpperCase())) {
				appFound=true;
				final ActivityInfo activity = app.activityInfo;
				final ComponentName name = new ComponentName(
						activity.applicationInfo.packageName,
						activity.name);
				shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				shareIntent.setComponent(name);
				break;
			}
		if(appFound)
			return shareIntent;
		else
			return null;
	}


	public static Intent gmailShareIntent(String message){
		boolean appFound=false;;
		Intent shareIntent = new Intent(
				android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,message);
		PackageManager pm = InsiderLouisevilleApp.instance().getPackageManager();
		List<ResolveInfo> activityList = pm.queryIntentActivities(
				shareIntent, 0);
		for (final ResolveInfo app : activityList) 
			if ((app.activityInfo.name.toUpperCase()).contains("gmail".toUpperCase())) {
				appFound=true;
				final ActivityInfo activity = app.activityInfo;
				final ComponentName name = new ComponentName(
						activity.applicationInfo.packageName,
						activity.name);
				shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				shareIntent.setComponent(name);
				break;
			}
		if(appFound)
			return shareIntent;
		else
			return null;
	}
}
