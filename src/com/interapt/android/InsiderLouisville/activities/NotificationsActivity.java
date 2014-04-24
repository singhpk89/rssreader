package com.interapt.android.InsiderLouisville.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.adapters.NotificationAdapter;

public class NotificationsActivity extends BaseActivity implements OnClickListener {


	private ListView list;
	private NotificationAdapter adapter;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.notificatons);

		initControl();
	}
	//Control id's  at runtime
	public void initControl() {
		leftHeaderView = (LinearLayout)findViewById(R.id.back);
		headerTitle= (TextView)findViewById(R.id.headertitle);
		list = (ListView)findViewById(R.id.list_notif);
		initializeClickEvents();
		setProperties();
		listProperties(); 
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
		headerTitle.setText("Notifications");
		headerTitle.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL);

	}
	
	//List properties at runtime
	private void listProperties() {
		adapter = new NotificationAdapter(this);
		list.setAdapter(adapter);
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

