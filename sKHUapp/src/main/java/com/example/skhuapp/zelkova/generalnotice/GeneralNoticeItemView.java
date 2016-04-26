package com.example.skhuapp.zelkova.generalnotice;

import com.example.skhuapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GeneralNoticeItemView extends FrameLayout {
	
	TextView title,author,date;
	GeneralNoticeData mData;

	public GeneralNoticeItemView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.general_notice_item_layout, this);
		title = (TextView)findViewById(R.id.general_notice_item_layout_title);
		author = (TextView)findViewById(R.id.general_notice_item_layout_author);
		date = (TextView)findViewById(R.id.general_notice_item_layout_date);
	}
	
	public void setData(GeneralNoticeData data){
		mData = data;
		title.setText(data.title);
		author.setText(data.author);
		date.setText(data.date);
	}

}
