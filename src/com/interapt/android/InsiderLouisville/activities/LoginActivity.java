package com.interapt.android.InsiderLouisville.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.adapters.NotificationAdapter;
import com.interapt.android.InsiderLouisville.helper.ScreenUtility;

import com.aquevix.framework.api.Response;
import com.aquevix.framework.api.ServiceManager.ServiceListener;
import com.aquevix.framework.app.AQActiveDetailActivity;

public class LoginActivity extends BaseActivity implements OnClickListener {


	private EditText txtUser,txtPass;
	private TextView btnEnter;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.layout_login);
		initControl();

	}
	//Control id's  at runtime
	public void initControl() {
		leftHeaderView = (LinearLayout)findViewById(R.id.back);
		headerTitle= (TextView)findViewById(R.id.headertitle);

		txtUser = (EditText)findViewById(R.id.txtemail);
		txtPass = (EditText)findViewById(R.id.txtpwd);
		btnEnter = (TextView)findViewById(R.id.txtcreate);

		initializeClickEvents();
		setProperties();
	}


	//Control events at runtime
	private void initializeClickEvents() {
		leftHeaderView.setOnClickListener(this);
		btnEnter.setOnClickListener(this);
	}
	//Control properties at runtime
	private void setProperties()
	{
		setHeaderTitleProp();
	}
	//headerTitle properties at runtime
	private void setHeaderTitleProp() {
		headerTitle.setText("Login");
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

		case R.id.txtcreate:
			Intent i = new Intent(getApplicationContext(), HomeFeedActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}

	}


}
