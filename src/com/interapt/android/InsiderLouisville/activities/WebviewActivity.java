package com.interapt.android.InsiderLouisville.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.utils.Progresss;

import com.aquevix.framework.app.AQActiveWebActivity;

public class WebviewActivity extends AQActiveWebActivity {

	private WebView webView;
	private ProgressBar progressBar;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.layout_webview);	
		super.onCreate(savedInstanceState);

		//Receive url to open
		initControl();
		Bundle b = getIntent().getExtras();
		String url = b.getString("url");		
		initializeWebView(url);
	}

	public void initControl() {
		super.initControls();

		
		webView = (WebView) findViewById(R.id.webView);


	}
	private void initializeWebView(String url){

		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setAllowFileAccess(true);
		webView.requestFocus(View.FOCUS_DOWN);

		webView.setWebViewClient(new WebViewClient() {

			@Override  
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);

				Progresss.stop();
			}  
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				Progresss.start(WebviewActivity.this);
			}
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			}
		});

		webView.loadUrl(url);

	}

}