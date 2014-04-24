package com.interapt.android.InsiderLouisville.viewindicator;

import com.interapt.android.InsiderLouisville.R;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SwipeFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    protected static final String[] CONTENT = 
    		new String[] {"We invite you to join the ranks of Louisville Metro's most informed and influential readers! Registration is fast,simple and FREE.Insiders enjoy a host of benefits. ",
    	 "News happens fast in this town and you need to stay on top of it.  Start your day informed and enlightened with the latest posts from Insider Louisville.  Our daily email newsletter brings the best and hottest news and opinion directly to your inbox.", 
    	 "Only Insiders can access exclusive content, such as our ever-popular Monday Business Briefing and various other features we create for our loyal base of registered members. ",
    	 "If you have not experienced one of Insider Louisville’s signature events, such as our weekly Monday afternoon meetup – where Insiders rub shoulders with some of the area’s most important and influential leaders – then you are missing out! We are always planning new events at exciting new venues where Insiders have a chance to network and learn more about our vibrant community. You will be the first to know!" };
    
    protected static final String[] HEADING = 
    		new String[] {"Become an  Insider!",
    	"The Daily Insider",
    	"Insider-Only Articles and Features",
    	"“Insider-Only” Events"
    	
    };
    protected static final int[] ICONS = new int[] {
            R.drawable.circle_selector,
            R.drawable.circle_selector,
            R.drawable.circle_selector,
            R.drawable.circle_selector
    };

    private int mCount = CONTENT.length;


    public SwipeFragmentAdapter( FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

    	
    		return FragmentHelp.newInstance(position % HEADING.length ,HEADING[position % HEADING.length],CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//      return TestFragmentAdapter.CONTENT[position % CONTENT.length];
//    }

    @Override
    public int getIconResId(int index) {
      return ICONS[index % ICONS.length];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}