package com.example.skhuapp.menu;

import java.util.Calendar;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.skhuapp.R;

public class MenuActivity extends Activity {

	int today;
	private parseMenu parseMenu;
	WeekMenu menu;
	String todayDate;
	String todayLunch;
	String lunchKcal;
	String todaySpecial;
	String specialKcal;
	String todayDinner;
	String dinnerKcal;
	
	TextView date,dayofweek,lunch,special,dinner,klunch,kspecial,kdinner;

	private final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			today = whatIsTheDateToday();
			initData();
		};
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		menu = new WeekMenu();
		parseMenu = new parseMenu(MenuActivity.this, handler, menu);
		parseMenu.parsing(); 
		
		date = (TextView)findViewById(R.id.activity_menu_date);
		dayofweek = (TextView)findViewById(R.id.activity_menu_dayofweek);
		lunch = (TextView)findViewById(R.id.activity_menu_lunch);
		special = (TextView)findViewById(R.id.activity_menu_special);
		dinner = (TextView)findViewById(R.id.activity_menu_dinner);
		klunch = (TextView)findViewById(R.id.activity_menu_klunch);
		kspecial = (TextView)findViewById(R.id.activity_menu_kspecial);
		kdinner = (TextView)findViewById(R.id.activity_menu_kdinner);

		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	private int whatIsTheDateToday() {
		Calendar mCal = Calendar.getInstance();
		switch (mCal.get(Calendar.DAY_OF_WEEK)) {
		case 1: //일요일이면
			return 4; //금요일 리턴
		case 2: //월요일이면
			return 0; //월요일
		case 3:
			return 1;
		case 4:
			return 2;
		case 5:
			return 3;
		case 6:
			return 4;
		case 7:
			return 4;
		}
		return -1;
	}
	
	private void initData(){
		date.setText(menu.weekDate.get(today));
		dayofweek.setText(menu.dayOfweek.get(today));
		lunch.setText(menu.weekLunch.get(today));
		special.setText(menu.weekSpecial.get(today));
		dinner.setText(menu.weekDinner.get(today));
		klunch.setText(menu.lunchKcal.get(today));
		kspecial.setText(menu.specialKcal.get(today));
		kdinner.setText(menu.dinnerKcal.get(today));
	}

}
