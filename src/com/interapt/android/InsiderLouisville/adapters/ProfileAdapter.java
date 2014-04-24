package com.interapt.android.InsiderLouisville.adapters;

import java.net.URI;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.interapt.android.InsiderLouisville.R;
import com.interapt.android.InsiderLouisville.requestresponse.News;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileAdapter extends BaseAdapter{
	
	Context context;
	LayoutInflater inflater;
	private DisplayImageOptions options;
	private ArrayList<News> news;
	private ImageLoader imageLoader;

	public ProfileAdapter(Context cont,ArrayList<News> list)
	{
		context = cont;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		news = list;
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.cacheInMemory()
		.cacheOnDisc()

		.showStubImage(R.drawable.drawer_shadow)
		.build();
	}

	@Override
	public int getCount() {

		return news!=null?news.size():0;
	}

	@Override
	public Object getItem(int position) {
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	class ViewHolder {

		ImageView imgView;
		TextView txtView;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		News  localNews = news.get(position);
		ViewHolder holder;
		View v = null;
		if(convertView == null){
			holder = new ViewHolder();
			v = inflater.inflate(R.layout.profile_item, parent, false);
			holder.txtView = (TextView) v.findViewById(R.id.textView);
			holder.imgView= (ImageView) v.findViewById(R.id.image);
			v.setTag(holder);
		}
		else{
			v = convertView;
			holder = (ViewHolder) v.getTag();
		}

		holder.txtView.setVisibility(View.VISIBLE);
		holder.txtView.setText(localNews.getDc_creator());
		 
		imageLoader.displayImage(localNews.getImgLink(),
				holder.imgView, options);

		return v;
	}
}
