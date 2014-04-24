package com.interapt.android.InsiderLouisville.adapters;

import com.interapt.android.InsiderLouisville.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationAdapter extends BaseAdapter
{

	private LayoutInflater inflater;
	private Context context;
	boolean isSelected = false;
	//private String arr[] = {"All","New Content","Breaking Stories Only","New Events"};
	private String arr[] = {"Push Notifications"};
	
	public NotificationAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		return arr.length;
	}

	@Override
	public Object getItem(int position) {
		return arr[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null)
			convertView = inflater.inflate(R.layout.notifications_lititem, parent, false);
		
			TextView text = (TextView)convertView.findViewById(R.id.text);
			ImageView image = (ImageView)convertView.findViewById(R.id.selector);
			text.setText(arr[position]);
			if(isSelected)
				image.setImageResource(R.drawable.push_on);
			else {
				image.setImageResource(R.drawable.push_off);
			}
			
			image.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(isSelected)
						isSelected = false;
					else {
						isSelected= true;
					}
					
					notifyDataSetChanged();
					
				}
			});
		
		return convertView;
	}
	
	
}
