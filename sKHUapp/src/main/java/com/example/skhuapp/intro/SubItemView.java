package com.example.skhuapp.intro;


import com.example.skhuapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SubItemView extends FrameLayout {
	
	ImageView image;
	TextView title;
	TextView content;
	SubItemData data;

	public SubItemView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.intro_sub_item_layout, this);
		image = (ImageView)findViewById(R.id.sub_item_image);
		title = (TextView)findViewById(R.id.sub_item_title);
		content = (TextView)findViewById(R.id.sub_item_content);
	}
	
	public void setData(SubItemData data){
		this.data=data;
		image.setImageResource(data.imageId);
		title.setText(data.title);
		content.setText(data.content);
	}

}
