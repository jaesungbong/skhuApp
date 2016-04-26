package com.example.skhuapp.collegeschedule;

import com.example.skhuapp.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DayView extends FrameLayout {
	
	DayData mData;
	TextView day;
	LinearLayout day_back;

	public DayView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.colleageschedule_day,this);
		day = (TextView)findViewById(R.id.day);
		day_back = (LinearLayout)findViewById(R.id.day_back);
	}
	
	public void setDayData(DayData data,int position){
		mData=data;
		day.setText(data.day);
		if(data.isEvent){
			day_back.setBackgroundResource(R.drawable.border4);
		}

		if(data.isFirstLine){
			day_back.setBackgroundResource(R.drawable.border2);
		}
		if(data.isThisMonth){
			if(position%7==0){
				day.setTextColor(Color.RED);
			}else if(position%7==6){
				day.setTextColor(Color.BLUE);
			}else{
				day.setTextColor(Color.BLACK);
			}
		}else{
			day.setTextColor(Color.GRAY);
		}

	}
	

}
