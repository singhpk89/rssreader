package com.interapt.android.InsiderLouisville.activities;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.adapters.HorizontalListAdapter;
import com.interapt.android.InsiderLouisville.adapters.NotificationAdapter;
import com.interapt.android.InsiderLouisville.adapters.StickyListAdapter;
import com.interapt.android.InsiderLouisville.customlistview.HorizontalListView;
import com.interapt.android.InsiderLouisville.customlistview.StickyListHeadersAdapter;
import com.interapt.android.InsiderLouisville.customlistview.StickyListHeadersListView;
import com.interapt.android.InsiderLouisville.customlistview.StickyListHeadersListView.OnStickyHeaderChangedListener;
import com.interapt.android.InsiderLouisville.feedparsers.RssReader;
import com.interapt.android.InsiderLouisville.interfaces.ListClick;
import com.interapt.android.InsiderLouisville.interfaces.ListScrollListnr;
import com.interapt.android.InsiderLouisville.requestresponse.News;
import com.interapt.android.InsiderLouisville.utils.Progresss;

public class NewsActivity extends BaseActivity implements OnClickListener,ListScrollListnr,ListClick,OnStickyHeaderChangedListener{


	private StickyListHeadersListView listVertical;
	private HorizontalListView listHorizontal;
	private HorizontalListAdapter horizontalAdapter;
	private StickyListAdapter stickyAdapter;
	private ArrayList<News> news;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.news);
		initControl();

	}
	//Control id's  at runtime
	public void initControl() {
		
		
		
		leftHeaderView = (LinearLayout)findViewById(R.id.back);
		headerTitle= (TextView)findViewById(R.id.headertitle);

		listVertical = (StickyListHeadersListView)findViewById(R.id.stickyheaders_list);
		listHorizontal = (HorizontalListView)findViewById(R.id.HorizontalListView);

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
		headerTitle.setText("Terms of Service");
		headerTitle.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL);

	}

	//List properties at runtime
	private void listProperties() {

		RssData data = new RssData();
		data.execute();
		listVertical.setOnStickyHeaderChangedListener(this);
		listVertical.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				News localNews = (News)arg0.getAdapter().getItem(arg2);
				Intent i = new Intent(getApplicationContext(), ArticlePreviewActivity.class);
				i.putExtra("date", localNews.getPubDate());
				i.putExtra("title", localNews.getTitle());
				i.putExtra("imglink", localNews.getImgLink());
				i.putExtra("detailurl", localNews.getUrl().toString());
				try {
			    i.putExtra("details", localNews.getJsonObject().get("text").toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				startActivity(i);
			}
		});
		listHorizontal.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String catName = arg0.getAdapter().getItem(arg2).toString();
				horizontalAdapter.curentSelected = arg2;
				horizontalAdapter.notifyDataSetChanged();
				setSelectionOfList(catName);
			}
		});

	}



	private void setSelectionOfList(String catName) {

		int i = 0;
		for (News localNews : news) {
			if(localNews.getCategoryName().equals(catName))
			{
				listVertical.setSelection(i);
				return;
			}
			i++;
		}
		
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
	//listen for horizontal list item click
	@Override
	public void onListClick(String title) {

	}
	//listen vertical list for collect current header
	@Override
	public void getCurrentTitle(String title,View view) {

		
		
	}


	class RssData extends AsyncTask<Void, Void, Void>
	{


		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Progresss.start(NewsActivity.this);
		}
		@Override
		protected Void doInBackground(Void... params) {
			news = RssReader.getLatestRssFeed();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Progresss.stop();

			try {
				sortList();
				stickyAdapter = new StickyListAdapter(getApplicationContext(),news);
				listVertical.setAdapter(stickyAdapter);
				stickyAdapter.registerListScrollListnr(NewsActivity.this);
				horizontalAdapter = new HorizontalListAdapter(NewsActivity.this, new ArrayList<News>(news));
				listHorizontal.setAdapter(horizontalAdapter);
				listHorizontal.setSelection(0);
				horizontalAdapter.registerListClick(NewsActivity.this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	public void sortList() {
		if(news!=null)
		{
			Collections.sort(news, new Comparator<News>() {

				@Override
				public int compare(News lhs, News rhs) {
					int b  = (lhs.getArticleId() != rhs.getArticleId())?(lhs.getArticleId()>rhs.getArticleId())?1:-1:0;
					return b;
				}
			});
		}

	}
	@Override
	public void onStickyHeaderChanged(StickyListHeadersListView l, View header,
			int itemPosition, long headerId) {
		int pos = (int)headerId;
		listHorizontal.setSelection(pos);
		horizontalAdapter.curentSelected =pos;
		horizontalAdapter.notifyDataSetChanged();
		
	}
	
}

