package com.interapt.android.InsiderLouisville.socialshare;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.aquevix.framework.app.AQDetailActivity;
import com.interapt.android.InsiderLouisville.R;


public class AlertViewActivity extends AQDetailActivity implements ICommonIntentRequest{
	private Button button1,
	button2;
	TextView txtTitle,
	txtMessage;
	String title="",
			message="",
			btn1Text="",
			btn2Text="";
	private OnClickListener okClick=new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			setResult(RESULT_BUTTON1);
			finish();
		}
	};
	private OnClickListener cancelClick=new OnClickListener() {
		@Override
		public void onClick(View v) {
			setResult(RESULT_BUTTON2,getIntent());
			finish();
		}
	};
	boolean canFinishonBackPress=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_alertview);
		Bundle b=getIntent().getExtras();
		if(b!=null&&b.containsKey("title"))
			title=b.getString("title");
		if(b!=null&&b.containsKey("message"))
			message=b.getString("message");
		if(b!=null&&b.containsKey("button1"))
			btn1Text=b.getString("button1");
		if(b!=null&&b.containsKey("button2"))
			btn2Text=b.getString("button2");
		if(b!=null&&b.containsKey("backPressEnable"))
			canFinishonBackPress=b.getBoolean("backPressEnable");
		InitializeControls();
	}

	private void InitializeControls() {
		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		txtTitle=(TextView)findViewById(R.id.txtTitle);
		txtMessage=(TextView)findViewById(R.id.txtMessage);
		txtTitle.setText(title);
		txtMessage.setText(message);
		button1.setOnClickListener(okClick);
		button2.setOnClickListener(cancelClick);
		if(btn2Text.contentEquals(""))
			button2.setVisibility(View.GONE);
		else
			button2.setText(btn2Text);
		button1.setText(btn1Text);

	}
	@Override
	public void onBackPressed() {
		if(canFinishonBackPress)
			super.onBackPressed();
	}
}
