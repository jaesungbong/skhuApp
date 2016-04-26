package com.example.skhuapp.zelkova.generalnotice;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GeneralNoticeAdapter extends BaseAdapter {

	ArrayList<GeneralNoticeData> items = new ArrayList<GeneralNoticeData>();
	Context mContext;
	
	public void clear(){
		items.clear();
	}
	
	public GeneralNoticeAdapter(Context context){
		mContext = context;
	}
	
	public void add(GeneralNoticeData data) {
		items.add(data);
	}
	
	public void addAll(ArrayList<GeneralNoticeData> items) {
		this.items.addAll(items);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public GeneralNoticeData getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GeneralNoticeItemView v;
		
		if(convertView ==null){
			v = new GeneralNoticeItemView(mContext);
		}else{
			v = (GeneralNoticeItemView)convertView;
		}
		v.setData(items.get(position));
		return v;
	}

}
