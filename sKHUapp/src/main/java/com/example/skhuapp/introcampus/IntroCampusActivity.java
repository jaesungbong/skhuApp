package com.example.skhuapp.introcampus;

import com.example.skhuapp.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class IntroCampusActivity extends Activity {

	LinearLayout layout;
	ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10,
			img11, img12;
	int check1 = 0, check2 = 0, check3 = 0, check4 = 0, check5 = 0, check6 = 0,
			check7 = 0, check8 = 0, check9 = 0, check10 = 0, check11 = 0,
			check12 = 0;// 0안보임, 1보임

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_introcampus);
	    
	    layout = (LinearLayout)findViewById(R.id.l1);
	    img1 = (ImageView)findViewById(R.id.de1);
	    layout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(check1==0&&check2==0){
					img1.setVisibility(View.VISIBLE);
					check1=1;
				}else if(check1==0&&check2==1){
					img2.setVisibility(View.GONE);
					img1.setVisibility(View.VISIBLE);
					check1=1;
					check2=0;
				}else{
					img1.setVisibility(View.GONE);
					check1=0;
				}
			}
		});
	    
	    layout = (LinearLayout)findViewById(R.id.l2);
	    img2 = (ImageView)findViewById(R.id.de2);
	    layout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(check2==0&&check1==0){
					img2.setVisibility(View.VISIBLE);
					check2=1;
				}else if(check2==0&check1==1){
					img1.setVisibility(View.GONE);
					img2.setVisibility(View.VISIBLE);
					check1=0;
					check2=1;
				}else{
					img2.setVisibility(View.GONE);
					check2=0;
				}
			}
		});
	    
//	    layout = (LinearLayout)findViewById(R.id.l3);
//	    img3 = (ImageView)findViewById(R.id.de3);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img3.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l4);
//	    img4 = (ImageView)findViewById(R.id.de4);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img4.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l5);
//	    img5 = (ImageView)findViewById(R.id.de5);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img5.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l6);
//	    img6 = (ImageView)findViewById(R.id.de6);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img6.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l7);
//	    img7 = (ImageView)findViewById(R.id.de7);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img7.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l8);
//	    img8 = (ImageView)findViewById(R.id.de8);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img8.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l9);
//	    img9 = (ImageView)findViewById(R.id.de9);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img9.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l10);
//	    img10 = (ImageView)findViewById(R.id.de10);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img10.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l11);
//	    img11 = (ImageView)findViewById(R.id.de11);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img11.setVisibility(View.VISIBLE);
//			}
//		});
//	    
//	    layout = (LinearLayout)findViewById(R.id.l12);
//	    img12 = (ImageView)findViewById(R.id.de12);
//	    layout.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				img12.setVisibility(View.VISIBLE);
//			}
//		});
	    
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}
}
