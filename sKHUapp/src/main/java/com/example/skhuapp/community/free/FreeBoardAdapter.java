package com.example.skhuapp.community.free;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.skhuapp.manager.DetailServerFreeBoardData;

public class FreeBoardAdapter extends BaseAdapter implements FreeBoardItemView.OnCommentClickListener {
	
	ArrayList<DetailServerFreeBoardData> items = new ArrayList<DetailServerFreeBoardData>();
	Context mContext;
	
	public void clear(){
		items.clear();
		notifyDataSetChanged();
	}
	
	public FreeBoardAdapter(Context context){
		mContext = context;
	}
	
	public void add(DetailServerFreeBoardData data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void addAll(ArrayList<DetailServerFreeBoardData> items) {
		this.items.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public DetailServerFreeBoardData getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FreeBoardItemView v;
		
		if(convertView ==null){
			v = new FreeBoardItemView(mContext);
			v.setOnCommentClickListener(this);
		}else{
			v = (FreeBoardItemView)convertView;
		}
		v.setData(items.get(position));
		return v;
	}
	
	public interface OnAdapterCommentClickListener{
		public void onCommentClick(View v, DetailServerFreeBoardData data);
	}
	
	OnAdapterCommentClickListener mAdapterListener;
	
	public void setOnAdapterCommentClickListener(OnAdapterCommentClickListener listener){
		mAdapterListener = listener;
	}

	@Override
	public void onCommentClick(View v, DetailServerFreeBoardData data) {
		if(mAdapterListener != null){
			mAdapterListener.onCommentClick(v, data);
		}
		
	}

}
