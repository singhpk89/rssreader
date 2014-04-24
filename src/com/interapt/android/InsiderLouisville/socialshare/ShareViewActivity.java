package com.interapt.android.InsiderLouisville.socialshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.interapt.android.InsiderLouisville.R;

import com.aquevix.framework.app.AQDetailActivity;
import com.aquevix.framework.socialmedia.facebook.FacebookAccount;
import com.aquevix.framework.socialmedia.facebook.FacebookConnect;
import com.aquevix.framework.socialmedia.facebook.FacebookConnectListner;
import com.aquevix.framework.socialmedia.twitter.TwitterConnect;

public class ShareViewActivity extends AQDetailActivity implements ICommonIntentRequest{
	Button btnCancel;
	LinearLayout layFacebookShare,
	layTwitterShare,
	layEmailShare,
	layMessageShare;
	Intent sendingIntent;
	private OnClickListener facebookClick=new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(!FacebookConnect.isFacebookLogin(ShareViewActivity.this)){
				Intent in=new Intent(ShareViewActivity.this, AlertViewActivity.class);
				in.putExtra("title", "Facebook");
				in.putExtra("message","You must connect to Facebook to post.");
				in.putExtra("button1", "Close");
				in.putExtra("button2", "" +"Connect");
				startActivityForResult(in, FACEBOOK_LOGIN_CHECK);
				
			}
			else{
				setResult(RESULT_OK, sendingIntent.putExtra("Sharecode", 0));
				finish();
			}
			
		}
	};
	private OnClickListener twitterClick=new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(!TwitterConnect.isTwitterLogin(ShareViewActivity.this)){
				Intent in=new Intent(ShareViewActivity.this, AlertViewActivity.class);
				in.putExtra("title", "Twitter");
				in.putExtra("message","You must connect to Twitter to post.");
				in.putExtra("button1", "Close");
				in.putExtra("button2", "" +"Connect");
				startActivityForResult(in, TWITTER_LOGIN_CHECK);
			}
			
		}
	};
	private OnClickListener emailClick=new OnClickListener() {

		@Override
		public void onClick(View v) {
			setResult(RESULT_OK, sendingIntent.putExtra("Sharecode", 2));
			finish();
		}
	};
	private OnClickListener messageClick=new OnClickListener() {

		@Override
		public void onClick(View v) {
			setResult(RESULT_OK, sendingIntent.putExtra("Sharecode", 3));
			finish();
		}
	};
	private OnClickListener cancelClick=new OnClickListener() {

		@Override
		public void onClick(View v) {
			setResult(RESULT_CANCELED);
			finish();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_shareoptions);
		InitializeControls();
	}

	private void InitializeControls() {
		layFacebookShare=(LinearLayout)findViewById(R.id.layFacebookShare);
		layTwitterShare=(LinearLayout)findViewById(R.id.layTwitterShare);
		layEmailShare=(LinearLayout)findViewById(R.id.layEmailShare);
		layMessageShare=(LinearLayout)findViewById(R.id.layMessageShare);
		btnCancel=(Button)findViewById(R.id.btnCancel);

		btnCancel.setOnClickListener(cancelClick);
		layFacebookShare.setOnClickListener(facebookClick);
		layTwitterShare.setOnClickListener(twitterClick);
		layEmailShare.setOnClickListener(emailClick);
		layMessageShare.setOnClickListener(messageClick);
		sendingIntent=new Intent();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case FACEBOOK_LOGIN_CHECK:
			if(resultCode == RESULT_BUTTON2){
				
				FacebookConnect connect=new FacebookConnect(ShareViewActivity.this);
				connect.Connect(new FacebookConnectListner() {

					@Override
					public void onError(String arg0) {
					
					}

					@Override
					public void onComplete(FacebookAccount fbAccount) {
						
						setResult(RESULT_OK, sendingIntent.putExtra("Sharecode", 0));
						finish();
					}
				});
			}
			
			break;
		case TWITTER_LOGIN_CHECK:
			if(resultCode==RESULT_BUTTON2){
				setResult(RESULT_OK, sendingIntent.putExtra("Sharecode", 1));
				finish();
			}
			break;
		default:
			break;
		}
	}
}
