package com.interapt.android.InsiderLouisville.adapters;

import java.util.ArrayList;


import com.interapt.android.InsiderLouisville.interfaces.ListClick;
import com.interapt.android.InsiderLouisville.requestresponse.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.interapt.android.InsiderLouisville.R;
import android.widget.TextView;

public class HorizontalListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater  inflater;
	ArrayList<News> news;
	String arr[];
	ListClick listClick;
	public int curentSelected =-1;
	public HorizontalListAdapter(Context context,ArrayList<News> arrayList) {
		this.context = context;
		inflater= LayoutInflater.from(context);
		news = arrayList;
		arr = context.getResources().getStringArray(R.array.category);
	}

	public void registerListClick(ListClick listClick) {
		this.listClick = listClick;
	}
	
	@Override
	public int getCount() {
		return arr.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arr[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final HorizontalHolder holder;
		
		if(convertView==null)
		{
			holder = new HorizontalHolder();
			convertView = inflater.inflate(R.layout.horizontal_list_item, parent, false);
			holder.newsType = (TextView)convertView.findViewById(R.id.textView);
			convertView.setTag(holder);
		}
		else
		{
			holder = (HorizontalHolder)convertView.getTag();
		}

		holder.newsType.setTextColor(context.getResources().getColor(R.color.white));
		
			
		holder.newsType.setText(arr[position]);
		/*holder.newsType.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				holder.newsType.setTextColor(context.getResources().getColor(R.color.yellow_orange));
				curentSelected = position;
				notifyDataSetChanged();
			}
		});*/
		if(curentSelected==position)
			holder.newsType.setTextColor(context.getResources().getColor(R.color.yellow_orange));
		
		return convertView;
	}

	class HorizontalHolder 
	{
		TextView newsType;
	}

}
