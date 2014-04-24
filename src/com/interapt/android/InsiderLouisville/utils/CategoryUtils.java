package com.interapt.android.InsiderLouisville.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.interapt.android.InsiderLouisville.InsiderLouisevilleApp;
import com.interapt.android.InsiderLouisville.R;


public class CategoryUtils {

	public static HashMap<String, ArrayList<String>> categories;// = new HashMap<String, ArrayList<String>>();

	public CategoryUtils() {
		String arr[] = InsiderLouisevilleApp.instance().getResources().getStringArray(R.array.category);
		categories = new HashMap<String, ArrayList<String>>(arr.length);

		for (String string : arr) {
			ArrayList<String>  list = new ArrayList<String>();
			if(string.equals("BUSINESS"))
			{
				Collections.addAll(list, InsiderLouisevilleApp.instance().getResources().getStringArray(R.array.business));
				categories.put("BUSINESS", list);
			}else if(string.equals("STARTUPS"))
			{
				Collections.addAll(list, InsiderLouisevilleApp.instance().getResources().getStringArray(R.array.statups));
				categories.put("STARTUPS", list);
			}else if(string.equals("METRO"))
			{
				Collections.addAll(list, InsiderLouisevilleApp.instance().getResources().getStringArray(R.array.metro));
				categories.put("METRO", list);
			}else if(string.equals("LIFESTYLE & CULTURE"))
			{
				Collections.addAll(list, InsiderLouisevilleApp.instance().getResources().getStringArray(R.array.lifestyle_culture));
				categories.put("LIFESTYLE & CULTURE", list);
			}


		}
	}


	public String getCategory(String val) {
		if(isBelongsToBusiness(val)!=null)
		{
			return isBelongsToBusiness(val);
		}else if(isBelongsToStartups(val)!=null)
		{
			return isBelongsToStartups(val);
		}else if(isBelongsToMetro(val)!=null)
		{
			return isBelongsToMetro(val);
		}else if(isBelongsToLif$Cul(val)!=null)
		{
			return isBelongsToLif$Cul(val);
		}
		return null;

	}
	
	public long getCategoryLong(String val) {
		if(isBelongsToBusiness(val)!=null)
		{
			return 1l;
		}else if(isBelongsToStartups(val)!=null)
		{
			return 2l;
		}else if(isBelongsToMetro(val)!=null)
		{
			return 3l;
		}else if(isBelongsToLif$Cul(val)!=null)
		{
			return 4l;
		}
		return 0l;

	}

	public String  isBelongsToBusiness(String val) {

		ArrayList<String> catArr = categories.get("BUSINESS");
		if(catArr.contains(val))
			return "BUSINESS";
		else
			return null;
	}
	
	public String  isBelongsToStartups(String val) {

		ArrayList<String> catArr = categories.get("STARTUPS");
		if(catArr.contains(val))
			return "STARTUPS";
		else
			return null;
	}
	
	public String  isBelongsToMetro(String val) {

		ArrayList<String> catArr = categories.get("METRO");
		if(catArr.contains(val))
			return "METRO";
		else
			return null;
	}
	
	public String  isBelongsToLif$Cul(String val) {

		ArrayList<String> catArr = categories.get("LIFESTYLE & CULTURE");
		if(catArr.contains(val))
			return "LIFESTYLE & CULTURE";
		else
			return null;
	}


}
