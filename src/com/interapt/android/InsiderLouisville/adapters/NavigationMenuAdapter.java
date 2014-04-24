package com.interapt.android.InsiderLouisville.adapters;


import com.interapt.android.InsiderLouisville.InsiderLouisevilleApp;
import com.interapt.android.InsiderLouisville.R;

import com.interapt.android.InsiderLouisville.activities.AboutActivity;
import com.interapt.android.InsiderLouisville.activities.NewsActivity;
import com.interapt.android.InsiderLouisville.activities.NotificationsActivity;
import com.interapt.android.InsiderLouisville.activities.PrivatePolicyActivity;
import com.interapt.android.InsiderLouisville.activities.TOSActivity;
import com.interapt.android.InsiderLouisville.activities.UpdateProfileActivity;
import com.interapt.android.InsiderLouisville.commons.AppIntentsHelper;
import com.interapt.android.InsiderLouisville.interfaces.OnNavigationClickListner;
import com.interapt.android.InsiderLouisville.socialshare.ShareViewActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class NavigationMenuAdapter extends BaseAdapter {
	Context context;
	String[] podTitle;
	int[] mIcon;
	LayoutInflater inflater;
	int position = -1;

	int[] icon ={R.drawable.news,R.drawable.notification,
			R.drawable.share,R.drawable.appsupport,R.drawable.policy,R.drawable.privacy,
			R.drawable.about};
	int[] icon_white ={R.drawable.news_active,R.drawable.notification_active,
			R.drawable.share_active,R.drawable.appsupport_active,R.drawable.policy_active,R.drawable.privacy_active,
			R.drawable.about_active};

	public NavigationMenuAdapter(Context cont)
	{
		context = cont;
		podTitle = context.getResources().getStringArray(R.array.nav_items);;
		mIcon = icon;

	}

	@Override
	public int getCount() {
		return mIcon.length+1;
	}

	@Override
	public Object getItem(int position) {
		if (position == mIcon.length+1)
			return null;
		return mIcon[position];
		}

	@Override
	public long getItemId(int position) {return position;}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		TextView podtxtTitle;
		ImageView forward;
		ImageView podimgIcon;
		RelativeLayout isSelectsItem;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View itemView = null;


		if (position != mIcon.length) {
			itemView = inflater.inflate(R.layout.nav_menu_list_item, parent,
					false);
			podtxtTitle = (TextView) itemView
					.findViewById(R.id.header_logo_event_txt);
			podtxtTitle.setText(podTitle[position]);
			podimgIcon = (ImageView) itemView.findViewById(R.id.menu_icon);
			podimgIcon.setImageResource(mIcon[position]);
			isSelectsItem = (RelativeLayout) itemView
					.findViewById(R.id.relative_header);
		}
		else if (position == mIcon.length)
		{
			itemView = inflater.inflate(R.layout.social_networks, parent,false);
			ImageView twt = (ImageView)itemView.findViewById(R.id.twt);
			twt.setOnClickListener(twtlistner);
			ImageView fb  = (ImageView)itemView.findViewById(R.id.fb_img);
			fb.setOnClickListener(twtlistner);
			ImageView cloud  = (ImageView)itemView.findViewById(R.id.cloud_img);
			cloud.setOnClickListener(twtlistner);

		}

		if (position != mIcon.length) {
			itemView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					return onTouchEvent(v, event,position);
				}
			});
			this.position = position;

		}

		itemView.setTag(String.valueOf(position));
		return itemView;
	}


	OnTouchListener touch = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {


			return true;


		}


	};


	private boolean onTouchEvent(View v, MotionEvent event,int position) {
		if(event.getAction()==MotionEvent.ACTION_DOWN)
		{
			ImageView podimgIcon = (ImageView) v.findViewById(R.id.menu_icon);
			podimgIcon.setImageResource(icon_white[position]);

			RelativeLayout isSelectsItem= (RelativeLayout) v.findViewById(R.id.relative_header);
			isSelectsItem.setBackgroundColor(context.getResources().getColor(R.color.yellow_orange));
			//isSelectsItem.setVisibility(View.VISIBLE);
			TextView podtxtTitle = (TextView) v
					.findViewById(R.id.header_logo_event_txt);
			podtxtTitle.setTextColor(Color.WHITE);
			//return true;
		}
		else if(event.getAction()==MotionEvent.ACTION_CANCEL)
		{

			ImageView podimgIcon = (ImageView) v.findViewById(R.id.menu_icon);
			podimgIcon.setImageResource(mIcon[position]);
			RelativeLayout isSelectsItem= (RelativeLayout) v.findViewById(R.id.relative_header);
			//isSelectsItem.setVisibility(View.GONE);
			isSelectsItem.setBackgroundColor(context.getResources().getColor(R.color.white_shade));

			TextView podtxtTitle = (TextView) v
					.findViewById(R.id.header_logo_event_txt);
			podtxtTitle.setTextColor(context.getResources().getColor(R.color.drawer_item_color));
			//return true;
		}
		else if(event.getAction()==MotionEvent.ACTION_UP)
		{
			ImageView podimgIcon = (ImageView) v.findViewById(R.id.menu_icon);
			podimgIcon.setImageResource(mIcon[position]);
			RelativeLayout isSelectsItem= (RelativeLayout) v.findViewById(R.id.relative_header);
			//isSelectsItem.setVisibility(View.GONE);
			isSelectsItem.setBackgroundColor(context.getResources().getColor(R.color.white_shade));
			TextView podtxtTitle = (TextView) v
					.findViewById(R.id.header_logo_event_txt);
			podtxtTitle.setTextColor(context.getResources().getColor(R.color.drawer_item_color));
			int i  = Integer.valueOf(v.getTag().toString());
			onAdapterClick(i);
			//adaterListner.onItemClick(v, i);
			return false;
		}

		return true;

	}

	OnClickListener twtlistner = new OnClickListener() {

		@Override
		public void onClick(View v) {


			switch (v.getId()) 
			{
			case R.id.twt:
				AppIntentsHelper.openTwitter(context,"15108012" , "https://twitter.com/wfpk");
				//openWeb("https://twitter.com/wfpk");

				break;
			case R.id.fb_img:
				AppIntentsHelper.openFacebook(context, "61323505448", "https://www.facebook.com/wfpklouisville");
				//openWeb("https://www.facebook.com/wfpklouisville");

				break;
			case R.id.cloud_img:
				AppIntentsHelper.openSoundCloud(context, "" , "https://soundcloud.com/wfpk");

				//openWeb("https://soundcloud.com/wfpk");

				break;


			}

		}
	};
	private OnNavigationClickListner adaterListner;


	public void registerAdapterClickListner(OnNavigationClickListner listner)
	{
		adaterListner = listner;
	}
	private void onAdapterClick(int i) {
		switch (i) {
		case 0:

			Intent i0 = new Intent(context,NewsActivity.class);
			context.startActivity(i0);

			break;
		case 1:

			Intent i1 = new Intent(context,NotificationsActivity.class);
			context.startActivity(i1);
			break;
		case 2:

			Intent i2 = new Intent(context,ShareViewActivity.class);
			context.startActivity(i2);
			break;
		
		case 3:

			Intent email = new Intent(Intent.ACTION_SEND);
			email.putExtra(Intent.EXTRA_EMAIL, new String[]{"wfpk@wfpk.org"});	//wfpk@wfpk.org	  studio@wfpk.org
			email.putExtra(Intent.EXTRA_SUBJECT, "Email the DJ");
			email.putExtra(Intent.EXTRA_CC,new String[]{"app@wfpk.org"});
			//email.putExtra(Intent.EXTRA_TEXT, "message");
			email.setType("message/rfc822");
			//email.putExtra(Intent.EXTRA_TEXT, "I\'m streaming [Song Title] by [Artist] on the WFPK app! http://bit.ly/I04g8j");

			context.startActivity(Intent.createChooser(email, "Choose an Email client :"));
			break;
		case 4:

			Intent i5 = new Intent(context,TOSActivity.class);
			context.startActivity(i5);
			break;
		case 5:

			Intent i6 = new Intent(context,PrivatePolicyActivity.class);
			context.startActivity(i6);
			break;
		case 6:

			Intent i7 = new Intent(context,AboutActivity.class);
			context.startActivity(i7);
			break;
		case 8:



			//Intent i8 = new Intent(context,MyFavSongActivity.class);
			//context.startActivity(i8);
			break;

		default:
			break;
		}

		if((InsiderLouisevilleApp.Instance()).getmDrawerLayout().isDrawerOpen(GravityCompat.START))
		{
			(InsiderLouisevilleApp.Instance()).getmDrawerLayout().closeDrawer(GravityCompat.START);
		}


	}




}
