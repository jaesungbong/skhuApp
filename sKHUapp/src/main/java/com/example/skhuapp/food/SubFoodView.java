package com.example.skhuapp.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.skhuapp.R;

public class SubFoodView extends FrameLayout {

	TextView text;
	SubFoodData data;

	public SubFoodView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.food_sub_item_layout, this);
		text = (TextView)findViewById(R.id.food_sub_item_layout_textView);
	}
	
	public void setData(SubFoodData data){
		this.data = data;
		text.setText(data.title);
	}
}
