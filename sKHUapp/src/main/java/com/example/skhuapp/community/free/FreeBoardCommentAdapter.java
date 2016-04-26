package com.example.skhuapp.community.free;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.skhuapp.manager.CommentResult;

public class FreeBoardCommentAdapter extends BaseAdapter {
	
	ArrayList <CommentResult> items = new ArrayList<CommentResult>();
	Context mContext;

	public void clear(){
		items.clear();
		notifyDataSetChanged();
	}
	
	public FreeBoardCommentAdapter(Context context){
		mContext = context;
	}
	
	public void add(CommentResult data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void addAll(ArrayList<CommentResult> items) {
		this.items.addAll(items);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public CommentResult getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FreeBoardCommentItemView v;
		
		if(convertView == null){
			v= new FreeBoardCommentItemView(mContext);
		}else{
			v=(FreeBoardCommentItemView)convertView;
		}
		v.setData(items.get(position));
		return v;
	}

}
