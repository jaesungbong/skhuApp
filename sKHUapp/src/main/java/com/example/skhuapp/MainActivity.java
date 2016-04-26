package com.example.skhuapp;

import com.example.skhuapp.collegeschedule.CollegeScheduleActivity;
import com.example.skhuapp.community.CommunityActivity;
import com.example.skhuapp.community.free.FreeBoardActivity;
import com.example.skhuapp.food.FoodActivity;
import com.example.skhuapp.intro.IntroActivity;
import com.example.skhuapp.introcampus.IntroCampusActivity;
import com.example.skhuapp.library.LibraryActivity;
import com.example.skhuapp.menu.MenuActivity;
import com.example.skhuapp.schedule.TimeTable;
import com.example.skhuapp.schedule.TimeTableSetting;
import com.example.skhuapp.schedule.schedule;
import com.example.skhuapp.zelkova.NoticeActivity;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView img;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img = (ImageView) findViewById(R.id.intro);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						IntroActivity.class);
				startActivity(i);
			}
		});

		img = (ImageView) findViewById(R.id.notice);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						NoticeActivity.class);
				startActivity(i);
			}
		});

		img = (ImageView) findViewById(R.id.community);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						FreeBoardActivity.class);
				startActivity(i);
			}
		});

		img = (ImageView) findViewById(R.id.menu);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						MenuActivity.class);
				startActivity(i);
			}
		});

		img = (ImageView) findViewById(R.id.library);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						LibraryActivity.class);
				startActivity(i);
			}
		});

		img = (ImageView) findViewById(R.id.schedule);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						schedule.class);
				startActivity(i);
			}
		});

		img = (ImageView) findViewById(R.id.food);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						FoodActivity.class);
				startActivity(i);
			}
		});

		img = (ImageView) findViewById(R.id.collegeschdule);
		img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						CollegeScheduleActivity.class);
				startActivity(i);
			}
		});

		img = (ImageView) findViewById(R.id.introcampus);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						IntroCampusActivity.class);
				startActivity(i);
			}
		});
	}
}