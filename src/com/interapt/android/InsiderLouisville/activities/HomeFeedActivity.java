package com.interapt.android.InsiderLouisville.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.interapt.android.InsiderLouisville.InsiderLouisevilleApp;
import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.adapters.HorizontalListAdapter;
import com.interapt.android.InsiderLouisville.adapters.NavigationMenuAdapter;
import com.interapt.android.InsiderLouisville.adapters.ProfileAdapter;
import com.interapt.android.InsiderLouisville.adapters.StickyListAdapter;
import com.interapt.android.InsiderLouisville.feedparsers.RssReader;
import com.interapt.android.InsiderLouisville.requestresponse.News;
import com.interapt.android.InsiderLouisville.utils.Progresss;

public class HomeFeedActivity extends DrawerBaseActivity{

	
	RelativeLayout ll;
	LinearLayout lay_left;
	Context context;
	private ArrayList<News> news;
	private HashMap<String, String> home = new HashMap<String, String>();
	private ProgressBar progressBar;
	ActionBarDrawerToggle mDrawerToggle;
	DrawerLayout mDrawerLayout;
	ListView navMenuList,podList;
	private NavigationMenuAdapter navAdapter;
	private ProfileAdapter profileAdapter;
	String[] title;
	Activity act = this;;
	boolean isServiceRunning = false;
	int[] icon ={R.drawable.news,R.drawable.events,R.drawable.profile,R.drawable.notification,
			R.drawable.share,R.drawable.appsupport,R.drawable.policy,R.drawable.privacy,
			R.drawable.about};
	int[] icon_white ={R.drawable.news_active,R.drawable.events_active,R.drawable.profile_active,R.drawable.notification_active,
			R.drawable.share_active,R.drawable.appsupport_active,R.drawable.policy_active,R.drawable.privacy_active,
			R.drawable.about_active};
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_home);
		setActivity(this);
		getIds();
		checkForFirstTime();
		act = this;
		lay_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
				{
					mDrawerLayout.closeDrawer(GravityCompat.START);
				}
				else
				{
					mDrawerLayout.openDrawer(GravityCompat.START);
				}

			}
		});
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.icon_menu, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				navMenuList.setAdapter(navAdapter);

			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);


			}
		};

		
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}
	
	
	
	
	public void getIds()
	{
		context = this;
		title = getResources().getStringArray(R.array.nav_items);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		InsiderLouisevilleApp.Instance().setmDrawerLayout(mDrawerLayout);
		navMenuList = (ListView) findViewById(R.id.listview_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
		navAdapter = new NavigationMenuAdapter(this);
		navMenuList.setAdapter(navAdapter);
		podList = (ListView)findViewById(R.id.podlists);
		
		lay_left = (LinearLayout)findViewById(R.id.back);
		new RssData().execute();
		
		podList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				News localNews = (News)arg0.getAdapter().getItem(arg2);
				Intent i = new Intent(getApplicationContext(), ArticlePreviewActivity.class);
				i.putExtra("date", localNews.getPubDate());
				i.putExtra("title", localNews.getTitle());
				i.putExtra("imglink", localNews.getImgLink());
				i.putExtra("detailurl", localNews.getUrl().toString());
				try {
			    i.putExtra("details", localNews.getJsonObject().get("text").toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				startActivity(i);
				
			}
		});

	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		try {
			mDrawerToggle.syncState();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void checkForFirstTime() {
		String str = InsiderLouisevilleApp.getPreferences("isFirst", "true");
		if(str.equals("true"))
		{
			InsiderLouisevilleApp.savePreferences("isFirst", "false");
			openDialog();
		}
	}

	protected void openDialog() {
       final  Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        dialog.setContentView(R.layout.fullscreen);
        getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        LinearLayout  layout = (LinearLayout)dialog.findViewById(R.id.fullscrn);
        layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
			}
		});
        dialog.show();
    }
	
	class RssData extends AsyncTask<Void, Void, Void>
	{


		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Progresss.start(HomeFeedActivity.this);
		}
		@Override
		protected Void doInBackground(Void... params) {
			news = RssReader.getLatestRssFeed();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Progresss.stop();

			try {
				

				profileAdapter = new ProfileAdapter(getApplicationContext(),news);
				podList.setAdapter(profileAdapter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

