package com.interapt.android.InsiderLouisville.activities;

import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.helper.ScreenUtility;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateAccountActivity extends BaseActivity implements OnClickListener {


	private EditText txtUser,txtPass;
	private TextView btnBecomeInsider;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.layout_createaccount);
		initControl();

	}
	//Control id's  at runtime
	public void initControl() {
		leftHeaderView = (LinearLayout)findViewById(R.id.back);
		headerTitle= (TextView)findViewById(R.id.headertitle);

		txtUser = (EditText)findViewById(R.id.txtemail);
		txtPass = (EditText)findViewById(R.id.txtpwd);
		btnBecomeInsider = (TextView)findViewById(R.id.txtbecomeinsider);

		initializeClickEvents();
		setProperties();
	}


	//Control events at runtime
	private void initializeClickEvents() {
		leftHeaderView.setOnClickListener(this);
		btnBecomeInsider.setOnClickListener(this);
	}
	//Control properties at runtime
	private void setProperties()
	{
		setHeaderTitleProp();
	}
	//headerTitle properties at runtime
	private void setHeaderTitleProp() {
		headerTitle.setText("Create");
		headerTitle.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
		headerTitle.setPadding(0, 0, ScreenUtility.dpToPx(10), 0);

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

		case R.id.txtbecomeinsider:
			Intent i = new Intent(getApplicationContext(), HomeFeedActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}

	}


}
