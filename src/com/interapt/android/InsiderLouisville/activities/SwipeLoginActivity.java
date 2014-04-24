package com.interapt.android.InsiderLouisville.activities;

import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.viewindicator.IconPageIndicator;
import com.interapt.android.InsiderLouisville.viewindicator.PageIndicator;
import com.interapt.android.InsiderLouisville.viewindicator.SwipeFragmentAdapter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SwipeLoginActivity extends FragmentActivity implements OnClickListener{
	SwipeFragmentAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;
	View loginView,createView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_swipe);
		int pNumber = 0;//= getIntent().getExtras().getInt("pNumber", 0);

		mAdapter = new SwipeFragmentAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);

		mPager.setAdapter(mAdapter);

		mIndicator = (IconPageIndicator) findViewById(R.id.indicator);

		mIndicator.setViewPager(mPager);	
		mPager.setCurrentItem(pNumber);
		loginView = (View)findViewById(R.id.txtemail);
		createView = (View)findViewById(R.id.txtcreate);
		loginView.setOnClickListener(this);
		createView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txtcreate:

			Intent  intent1 = new Intent(getApplicationContext(), CreateAccountActivity.class);
			startActivity(intent1);
			break;
		case R.id.txtemail:

			Intent  intent2 = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent2);
			break;

		default:
			break;
		}

	}
}