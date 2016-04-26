package com.example.skhuapp.community.free;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.skhuapp.R;
import com.example.skhuapp.manager.DetailServerFreeBoardData;

public class FreeBoardItemView extends FrameLayout {
	
	TextView name,date,contents,comment;
	DetailServerFreeBoardData mData;
	
	public interface OnCommentClickListener {
		public void onCommentClick(View v, DetailServerFreeBoardData data);
	}
	OnCommentClickListener mListenr;
	
	public void setOnCommentClickListener(OnCommentClickListener listener){
		mListenr = listener;
	}

	public FreeBoardItemView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.freeboard_itemview_layout, this);
		name = (TextView)findViewById(R.id.freeboard_name);
		date = (TextView)findViewById(R.id.freeboard_date);
		contents = (TextView)findViewById(R.id.freeboard_contents);
		comment =(TextView)findViewById(R.id.freeboard_comment);
		comment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListenr !=null){
					mListenr.onCommentClick(FreeBoardItemView.this, mData);
				}
			}
		});
	}
	
	public void setData(DetailServerFreeBoardData data){
		mData = data;
		name.setText(data.name);
		date.setText(data.enroll_time);
		contents.setText(data.content);
	}

}
