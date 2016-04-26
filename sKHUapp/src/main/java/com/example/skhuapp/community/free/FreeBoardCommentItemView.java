package com.example.skhuapp.community.free;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.skhuapp.R;
import com.example.skhuapp.manager.CommentResult;

public class FreeBoardCommentItemView extends FrameLayout {
	
	TextView commentContent,date;
	CommentResult mData;

	public FreeBoardCommentItemView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.freeboardcomment_item_layout, this);
		commentContent = (TextView)findViewById(R.id.freeboardcomment_item_layout_comment_content);
		date = (TextView)findViewById(R.id.freeboardcomment_item_layout_date);
	}
	
	public void setData(CommentResult data){
		mData = data;
		commentContent.setText(data.content);
		date.setText(data.enroll_time);
	}

}
