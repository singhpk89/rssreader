package com.interapt.android.InsiderLouisville.activities;

import com.interapt.android.InsiderLouisville.R;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends BaseActivity implements OnClickListener {


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.about);
		initControl();

	}
	//Control id's  at runtime
	public void initControl() {
		leftHeaderView = (LinearLayout)findViewById(R.id.back);
		headerTitle= (TextView)findViewById(R.id.headertitle);
		initializeClickEvents();
		setProperties();
	}

 
	//Control events at runtime
	private void initializeClickEvents() {
		leftHeaderView.setOnClickListener(this);
	}
	//Control properties at runtime
	private void setProperties()
	{
		setHeaderTitleProp();
	}
	 //headerTitle properties at runtime
	private void setHeaderTitleProp() {
		headerTitle.setText("About");
		headerTitle.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL);

	}
	
	
	//Click Events
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.headertitle:

			break;

		default:
			break;
		}

	}


}
