package com.interapt.android.InsiderLouisville.helper;

import com.interapt.android.InsiderLouisville.InsiderLouisevilleApp;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

public class ScreenUtility {
	
	//Convert dp unit into px
		public static int dpToPx(int dp)
		{
			return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
		}

		//Convert px unit into dp
		public static int pxToDp(int px)
		{
			return (int) (px / Resources.getSystem().getDisplayMetrics().density);
		}
		
		public static int getWidth() {
			return InsiderLouisevilleApp.Instance().getResources().getDisplayMetrics().widthPixels;
		}
		
		public static void setBottomMargin(View view, int bottomMarginInDips)
		{
		    ViewGroup.MarginLayoutParams layoutParams =    
		        (ViewGroup.MarginLayoutParams)view.getLayoutParams();
		    layoutParams.bottomMargin = dpToPx(bottomMarginInDips);
		    view.requestLayout();
		}

}
