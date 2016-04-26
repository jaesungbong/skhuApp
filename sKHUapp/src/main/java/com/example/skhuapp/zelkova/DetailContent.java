package com.example.skhuapp.zelkova;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.skhuapp.R;

public class DetailContent extends Activity {

	TextView topTitle, title, writer, date, content;

	String strTopTitle, strTitle, strWriter, strDate, strContent;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailcontent);
		Intent i = getIntent();
		strTopTitle = i.getStringExtra("topTilte");
		strTitle = i.getStringExtra("title");
		strWriter = i.getStringExtra("writer");
		strDate = i.getStringExtra("date");
		strContent = i.getStringExtra("content");

		topTitle = (TextView) findViewById(R.id.activity_detailcontent_toptitle);
		title = (TextView) findViewById(R.id.activity_detailcontent_title);
		writer = (TextView) findViewById(R.id.activity_detailcontent_writer);
		date = (TextView) findViewById(R.id.activity_detailcontent_date);
		content = (TextView) findViewById(R.id.activity_detailcontent_content);
		init();

		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	private void init() {
		topTitle.setText(strTopTitle);
		title.setText(strTitle);
		writer.setText(strWriter);
		date.setText(strDate);
		content.setText(strContent);
	}
}
