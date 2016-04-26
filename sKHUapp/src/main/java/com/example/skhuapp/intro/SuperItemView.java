package com.example.skhuapp.intro;


import com.example.skhuapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SuperItemView extends FrameLayout {
	
	TextView superTitle;
	SuperItemData data;

	public SuperItemView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.intro_super_item_layout, this);
		superTitle = (TextView)findViewById(R.id.super_item_name);
	}
	
	public void setData(SuperItemData data){
		this.data = data;
		superTitle.setText(data.title);		
	}
}
