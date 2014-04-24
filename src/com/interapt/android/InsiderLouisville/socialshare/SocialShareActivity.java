package com.interapt.android.InsiderLouisville.socialshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.interapt.android.InsiderLouisville.R;

import com.aquevix.framework.app.AQDetailActivity;

public class SocialShareActivity extends AQDetailActivity implements ICommonIntentRequest{

	/*
	 * Note:Must set all the ids in application class.
	 *
	 * */
	private Button shareBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_socialshare);
		shareBtn = (Button)findViewById(R.id.button1);
		shareBtn.setOnClickListener(shareClk);
	}
	
	private OnClickListener shareClk = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent i = new Intent(SocialShareActivity.this, ShareViewActivity.class);
			startActivityForResult(i, SOCIAL_SHARE);
		}
	};
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		
		case SOCIAL_SHARE:
			if(resultCode==RESULT_OK){
				
				String url = "url";
				String msg = "this is message.";
				ShareHelper.sendShareMessage(data.getIntExtra("Sharecode", -1), SocialShareActivity.this,
						msg, url, msg,msg);
				}
			break;
		
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}
