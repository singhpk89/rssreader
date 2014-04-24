package com.interapt.android.InsiderLouisville.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.interapt.android.InsiderLouisville.R;

public class UpdateProfileActivity extends BaseActivity implements OnClickListener {


	private TextView email,fName,zipCode;
	private TextView save;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.update_profile);

	}
	//Control id's  at runtime
	@Override
	public void initControls() {
		leftHeaderView = (LinearLayout)findViewById(R.id.back);
		headerTitle= (TextView)findViewById(R.id.headertitle);
		email = (TextView)findViewById(R.id.email);
		fName = (TextView)findViewById(R.id.fname);
		zipCode = (TextView)findViewById(R.id.pin);
		save = (TextView)findViewById(R.id.save);
		initializeClickEvents();
		setProperties();
	}


	//Control events at runtime
	private void initializeClickEvents() {
		leftHeaderView.setOnClickListener(this);
		save.setOnClickListener(this);
	}
	//Control properties at runtime
	private void setProperties()
	{
		setHeaderTitleProp();
	}
	//headerTitle properties at runtime
	private void setHeaderTitleProp() {
		headerTitle.setText("Profile");
		headerTitle.setGravity(Gravity.CENTER);

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
		case R.id.save:

			break;

		default:
			break;
		}

	}


}

