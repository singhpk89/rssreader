package com.interapt.android.InsiderLouisville;

import android.content.SharedPreferences;


import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;

import com.aquevix.framework.api.Request;
import com.aquevix.framework.app.AQApplication;
import com.aquevix.framework.socialmedia.SocialMediaKeys;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.testflightapp.lib.TestFlight;

public class InsiderLouisevilleApp extends AQApplication{


	private static  InsiderLouisevilleApp louisevilleApp;
	private DrawerLayout mDrawerLayout;
	private SharedPreferences sharedPreferences;
	private ImageLoader imageLoader;
	public DrawerLayout getmDrawerLayout() {
		return mDrawerLayout;
	}

	public void setmDrawerLayout(DrawerLayout mDrawerLayout) {
		this.mDrawerLayout = mDrawerLayout;
	}

	public static InsiderLouisevilleApp Instance() {
		return louisevilleApp;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		louisevilleApp = (InsiderLouisevilleApp) getApplicationContext();
		initialize();
		startTestFlight();
	}
	
	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	public void setImageLoader(ImageLoader imageLoader) {
		this.imageLoader = imageLoader;
	}

	private void initialize(){
		
		Request.defaultBaseUrl = "";
		
		SocialMediaKeys.FACEBOOK_APP_ID = "";
		SocialMediaKeys.TWITTER_CONSUMER_KEY="";
		SocialMediaKeys.TWITTER_CONSUMER_SECRET="";
		
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getBaseContext()));
		
	}
	
	private void startTestFlight(){
		
		TestFlight.takeOff(this, "23368d10-053d-4847-b2a5-4694e40ae5b0");
	}
	
	
	public static void savePreferences(String key, String value) {

		SharedPreferences sharedPreferences = PreferenceManager

				.getDefaultSharedPreferences(louisevilleApp);

		Editor editor = sharedPreferences.edit();

		editor.putString(key, value);

		editor.commit();

	}

	public static String getPreferences(String key, String defaultValue){
		
		SharedPreferences sharedPreferences = PreferenceManager

				.getDefaultSharedPreferences(louisevilleApp);
		return sharedPreferences.getString(key, defaultValue);
		
	}
	
}
