package com.interapt.android.InsiderLouisville.adapters;

import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.activities.BaseActivity;
import com.interapt.android.InsiderLouisville.customlistview.StickyListHeadersAdapter;
import com.interapt.android.InsiderLouisville.interfaces.ListScrollListnr;
import com.interapt.android.InsiderLouisville.requestresponse.News;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter{

	ArrayList<Integer> headerPositions = new ArrayList<Integer>();
 	private Context context;
	private LayoutInflater  inflater;
	ListScrollListnr listScrollListnr;
	ArrayList<News> news;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	public StickyListAdapter(Context context,ArrayList<News> arrayList) {
		this.context = context;
		inflater= LayoutInflater.from(context);
		news = arrayList;
		
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.cacheInMemory()
		.cacheOnDisc()

		.showStubImage(R.drawable.drawer_shadow)
		.build();

	}

	public void registerListScrollListnr(ListScrollListnr listScrollListnr) {
		this.listScrollListnr = listScrollListnr;
	}

	@Override
	public int getCount() {
		return news.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		News localNews = news.get(position); 
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.news_item, parent, false);
			holder.date = (TextView)convertView.findViewById(R.id.news_date);
			holder.detail = (TextView)convertView.findViewById(R.id.news_detail);
			holder.title = (TextView)convertView.findViewById(R.id.news_title);
			holder.newsImage = (ImageView)convertView.findViewById(R.id.news_image);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}

		holder.date.setText(BaseActivity.getFormatedDateString(localNews.getPubDate()));
		
		try {
			
			holder.detail.setText(localNews.getJsonObject().getString("text"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holder.title.setText(localNews.getTitle());
		imageLoader.displayImage(localNews.getImgLink(),
				holder.newsImage, options);
		
		return convertView;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HeaderViewHolder holder;
		if(convertView == null)
		{
			holder = new HeaderViewHolder();
			convertView = inflater.inflate(R.layout.news_header, parent, false);
			holder.newsType = (TextView)convertView.findViewById(R.id.news_type);
			convertView.setTag(holder);
		}
		else
		{
			holder = (HeaderViewHolder)convertView.getTag();
		}

		listScrollListnr.getCurrentTitle(news.get(position).getCategoryName(),convertView);

		holder.newsType.setText(news.get(position).getCategoryName());
		
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		return news.get(position).getArticleId();
	}
	
	public void changeCurrentNews(int pos){
	//	news.get(index)
	//	news.get(pos).currentNews =
	}

	class ViewHolder 
	{
		ImageView newsImage;
		TextView title,date,detail;
	}
	class HeaderViewHolder 
	{
		TextView newsType;
	}


}
