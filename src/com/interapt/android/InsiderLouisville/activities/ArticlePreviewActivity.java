package com.interapt.android.InsiderLouisville.activities;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.interapt.android.InsiderLouisville.InsiderLouisevilleApp;
import com.interapt.android.InsiderLouisville.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class ArticlePreviewActivity extends BaseActivity implements OnClickListener {


	private TextView date,title,details,continueRead;
	private ImageView article_image,shareOn,facebook,linkedIn,twitter,upside;
	private DisplayImageOptions options;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.article_preview);
		initControl();

	}
	//Control id's  at runtime
	public void initControl() {
		leftHeaderView = (LinearLayout)findViewById(R.id.back);

		headerTitle= (TextView)findViewById(R.id.headertitle);
		date = (TextView)findViewById(R.id.time);
		title = (TextView)findViewById(R.id.title);
		details = (TextView)findViewById(R.id.detail);
		continueRead = (TextView)findViewById(R.id.continueread);

		article_image = (ImageView)findViewById(R.id.article_image);
		shareOn = (ImageView)findViewById(R.id.share_on_text);
		facebook = (ImageView)findViewById(R.id.facebook);
		linkedIn = (ImageView)findViewById(R.id.linkedin);
		twitter = (ImageView)findViewById(R.id.twitter);
		upside = (ImageView)findViewById(R.id.upside);

		initializeClickEvents();
		setValue() ;
		setProperties();
	}



	private void setValue() {
		options = new DisplayImageOptions.Builder()
		.cacheInMemory()
		.cacheOnDisc()

		.showStubImage(R.drawable.drawer_shadow)
		.build();
		String date = getIntent().getExtras().getString("date");
		String title = getIntent().getExtras().getString("title");
		String imglink = getIntent().getExtras().getString("imglink");
		String detailurl = getIntent().getExtras().getString("detailurl");
		String detail = getIntent().getExtras().getString("details");
		this.date.setText(getFormatedDateString(date));
		this.details.setText(detail);
		this.title.setText(title);
		
		try {
			InsiderLouisevilleApp.Instance().getImageLoader().getInstance().displayImage(imglink,
					article_image, options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addElipses(details,detail.length());

	}

	//Control events at runtime
	private void initializeClickEvents() {
		leftHeaderView.setOnClickListener(this);

		continueRead.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String detailurl = getIntent().getExtras().getString("detailurl");
				openWeb(detailurl);
				
			}
		});
	}
	//Control properties at runtime
	private void setProperties()
	{
		setHeaderTitleProp();
	}
	//headerTitle properties at runtime
	private void setHeaderTitleProp() {
		headerTitle.setText("News");
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

			case R.id.continueread:
				String detailurl = getIntent().getExtras().getString("detailurl");
				openWeb(detailurl);
				break;
		default:
			break;
		}

	}


	private void openWeb(String url){
		if (url!=null) {
			Intent intent = new Intent(getApplicationContext(), WebviewActivity.class);
			intent.putExtra("url", url);
			startActivity(intent);
		}

	}

	
	private void addElipses(TextView tv,int len) {
		String str = tv.getText().toString();
		if(str.length()<len)
		{
			String st = str.substring(0, str.length()-6) + "....";
			tv.setText(st);
		}

	}
	
}

