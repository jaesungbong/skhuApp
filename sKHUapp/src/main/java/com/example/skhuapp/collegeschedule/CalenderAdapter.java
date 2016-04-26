package com.example.skhuapp.collegeschedule;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CalenderAdapter extends BaseAdapter {
	
	ArrayList<DayData> mDayList = new ArrayList<DayData>();
	Context mContext;
	
	public CalenderAdapter(Context context,ArrayList<DayData> daylist) {
		this.mContext=context;
		this.mDayList=daylist;
	}

	@Override
	public int getCount() {
		return mDayList.size();
	}

	@Override
	public DayData getItem(int position) {
		return mDayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DayView v;
		
		if(convertView ==null){
			v = new DayView(mContext);
		}else{
			v=(DayView)convertView;
		}
		v.setDayData(mDayList.get(position),position);
		
		return v;
	}

}
