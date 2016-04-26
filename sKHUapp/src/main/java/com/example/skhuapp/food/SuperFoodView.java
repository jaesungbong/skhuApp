package com.example.skhuapp.food;

import com.example.skhuapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SuperFoodView extends FrameLayout {
	
	ImageView img;
	TextView type;
	SuperFoodData data;

	public SuperFoodView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.food_super_itme_layout, this);
		img =(ImageView)findViewById(R.id.food_super_itme_layout_img);
		type = (TextView)findViewById(R.id.food_super_itme_layout_text);
	}
	
	public void setData(SuperFoodData data){
		this.data=data;
		img.setImageResource(data.typeimgId);
		type.setText(data.type);
	}
}
