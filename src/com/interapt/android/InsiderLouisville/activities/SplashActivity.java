package com.interapt.android.InsiderLouisville.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.helper.ScreenUtility;
import com.interapt.android.InsiderLouisville.requestresponse.News;

public class SplashActivity extends BaseActivity{

	private final static long DELAY = 1500;
	private ImageView animateView,afterAnimate;
	private TextView spalshText,textBtnText;
	private int textViewleft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.layout_splash);
		super.onCreate(savedInstanceState);



	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		textViewleft = spalshText.getLeft();
		new RunSplash().execute();
	}


	@Override
	public void initControls() {
		animateView = (ImageView)findViewById(R.id.animateview);
		afterAnimate = (ImageView)findViewById(R.id.afteranimate);
		spalshText = (TextView)findViewById(R.id.splashtext);
		textBtnText = (TextView)findViewById(R.id.textView2);


		textBtnText.setVisibility(View.INVISIBLE);
		spalshText.setVisibility(View.INVISIBLE);
		afterAnimate.setVisibility(View.INVISIBLE);
		animateView.setVisibility(View.VISIBLE);



	}

	private class RunSplash extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			doTranslate();
		}
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}


	private void doTranslate() {
		float toXdelta = animateView.getLeft() - ScreenUtility.dpToPx(15);
		Animation animation = new TranslateAnimation(0, -toXdelta,0, 0);
		animation.setDuration(1500);
		animation.setFillAfter(true);
		animateView.startAnimation(animation);
		animateView.setVisibility(0);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {

				animateView.setVisibility(View.INVISIBLE);
				afterAnimate.setVisibility(View.VISIBLE);
				setVisibleView();
			}
		});
	}

	private void showNextStep()
	{
		startActivity(new Intent(SplashActivity.this, HomeFeedActivity.class));
		//startActivity(new Intent(SplashActivity.this, NewsActivity.class));
		finish();
	}

	private void setVisibleView() {

		animateView.clearAnimation();
		textBtnText.setVisibility(View.VISIBLE);
		spalshText.setVisibility(View.VISIBLE);
		afterAnimate.setVisibility(View.VISIBLE);
		final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
		textBtnText.startAnimation(animationFadeIn);
		spalshText.startAnimation(animationFadeIn);
		showNextStep();

	}
}
