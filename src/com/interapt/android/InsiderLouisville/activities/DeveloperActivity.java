package com.interapt.android.InsiderLouisville.activities;

import com.aquevix.framework.app.AQDetailActivity;
import com.interapt.android.InsiderLouisville.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DeveloperActivity extends AQDetailActivity{

	private ImageView developerImage;
	
	private final String DEVELOPER_URL = "";

	@Override
	protected void onCreate(Bundle arg0) {
		
		setContentView(R.layout.layout_developer);
		super.onCreate(arg0);
		
	}
	
	@Override
	public void initControls() {
		super.initControls();

		developerImage = (ImageView)findViewById(R.id.developerImage);
		
		developerImage.setOnClickListener(devClick);
	}
	
	private OnClickListener devClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {

			Intent i = new Intent(getApplicationContext(), WebviewActivity.class);
			i.putExtra("url", DEVELOPER_URL);
			startActivity(i);
		}
	};
}
