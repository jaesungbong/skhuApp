package com.example.skhuapp.food;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skhuapp.R;

public class DetailFoodFragment extends Fragment {
	ImageView img;
	TextView name,menu,price;
	Button btn;
	String number;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.detailfoodfragment, container,false);
		img=(ImageView)v.findViewById(R.id.foodimg);
		name = (TextView)v.findViewById(R.id.foodname);
		menu = (TextView)v.findViewById(R.id.foodmenu);
		price = (TextView)v.findViewById(R.id.foodmenuprice);
		btn= (Button)v.findViewById(R.id.callbtn);
		initData();
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri n = Uri.parse("tel: "+number);
				startActivity(new Intent(Intent.ACTION_CALL, n));
			}
		});
		return v;
	}
	
	private void initData(){
		int code=FoodManager.getInstance().getFoodCode();
		switch (code) {
		case 1:
			Dondon data = new Dondon();
			img.setImageResource(data.img);
			name.setText(data.title);
			menu.setText(data.menu);
			price.setText(data.price);
			number=data.number;
			break;

		case 2:
			Samjichang data2 = new Samjichang();
			img.setImageResource(data2.img);
			name.setText(data2.title);
			menu.setText(data2.menu);
			price.setText(data2.price);
			number=data2.number;
			break;
		}
	
	}

}
