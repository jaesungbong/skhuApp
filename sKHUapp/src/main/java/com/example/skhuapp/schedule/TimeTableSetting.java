package com.example.skhuapp.schedule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.skhuapp.R;

public class TimeTableSetting extends Activity implements OnClickListener {

	public Intent intent; // 보내는 인텐트

	private Button choice, setting_ok, go_main; // 버튼 3개
	private TableRow one, two, three, four, five, six, seven, eight, nine, ten,
			tenone, tentwo, tenthree, tenfour, tenfive, tensix, tenseven,
			teneight, tennine, twenty, setting_button; // 20교시에 대한 가로줄과 요일을 나타내는
														// 최상단 1줄 : 총 21줄
	private TextView class_1_text, class_2_text, class_3_text, class_4_text,
			class_5_text, class_6_text, class_7_text, class_8_text,
			class_9_text, class_10_text, class_11_text, class_12_text,
			class_13_text, class_14_text, class_15_text, class_16_text,
			class_17_text, class_18_text, class_19_text, class_20_text; // 1교시부터
																		// 20교시까지를
																		// 나타내는
																		// TextView
	private EditText class_1_edit1, class_1_edit2, class_2_edit1,
			class_2_edit2, class_3_edit1, class_3_edit2, class_4_edit1,
			class_4_edit2, class_5_edit1, class_5_edit2, class_6_edit1,
			class_6_edit2, class_7_edit1, class_7_edit2, class_8_edit1,
			class_8_edit2, class_9_edit1, class_9_edit2, class_10_edit1,
			class_10_edit2, class_11_edit1, class_11_edit2, class_12_edit1,
			class_12_edit2, class_13_edit1, class_13_edit2, class_14_edit1,
			class_14_edit2, class_15_edit1, class_15_edit2, class_16_edit1,
			class_16_edit2, class_17_edit1, class_17_edit2, class_18_edit1,
			class_18_edit2, class_19_edit1, class_19_edit2, class_20_edit1,
			class_20_edit2; // 각 교시마다 시간을 설정하는 EditText
	private int select; // 몇교시인지를 담을 값
	private Drawable choice_btn_alpha, setting_ok_btn_alpha, go_main_btn_alpha; // 투명도

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetablesetting);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 화면을 전체화면으로 변경

		init(); // 변수 초기화
		makealpha(); // 투명도 조정

		// 이벤트 선언
		registerForContextMenu(choice);
		setting_ok.setOnClickListener(this);
		go_main.setOnClickListener(this);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	// 폰트 설정
//	private void setFont() {
//		// TODO Auto-generated method stub
//		choice.setTypeface(font);
//		setting_ok.setTypeface(font);
//		go_main.setTypeface(font);
//
//		class_1_text.setTypeface(font);
//		class_2_text.setTypeface(font);
//		class_3_text.setTypeface(font);
//		class_4_text.setTypeface(font);
//		class_5_text.setTypeface(font);
//		class_6_text.setTypeface(font);
//		class_7_text.setTypeface(font);
//		class_8_text.setTypeface(font);
//		class_9_text.setTypeface(font);
//		class_10_text.setTypeface(font);
//		class_11_text.setTypeface(font);
//		class_12_text.setTypeface(font);
//		class_13_text.setTypeface(font);
//		class_14_text.setTypeface(font);
//		class_15_text.setTypeface(font);
//		class_16_text.setTypeface(font);
//		class_17_text.setTypeface(font);
//		class_18_text.setTypeface(font);
//		class_19_text.setTypeface(font);
//		class_20_text.setTypeface(font);
//
//		class_1_edit1.setTypeface(font);
//		class_2_edit1.setTypeface(font);
//		class_3_edit1.setTypeface(font);
//		class_4_edit1.setTypeface(font);
//		class_5_edit1.setTypeface(font);
//		class_6_edit1.setTypeface(font);
//		class_7_edit1.setTypeface(font);
//		class_8_edit1.setTypeface(font);
//		class_9_edit1.setTypeface(font);
//		class_10_edit1.setTypeface(font);
//		class_11_edit1.setTypeface(font);
//		class_12_edit1.setTypeface(font);
//		class_13_edit1.setTypeface(font);
//		class_14_edit1.setTypeface(font);
//		class_15_edit1.setTypeface(font);
//		class_16_edit1.setTypeface(font);
//		class_17_edit1.setTypeface(font);
//		class_18_edit1.setTypeface(font);
//		class_19_edit1.setTypeface(font);
//		class_20_edit1.setTypeface(font);
//
//		class_1_edit2.setTypeface(font);
//		class_2_edit2.setTypeface(font);
//		class_3_edit2.setTypeface(font);
//		class_4_edit2.setTypeface(font);
//		class_5_edit2.setTypeface(font);
//		class_6_edit2.setTypeface(font);
//		class_7_edit2.setTypeface(font);
//		class_8_edit2.setTypeface(font);
//		class_9_edit2.setTypeface(font);
//		class_10_edit2.setTypeface(font);
//		class_11_edit2.setTypeface(font);
//		class_12_edit2.setTypeface(font);
//		class_13_edit2.setTypeface(font);
//		class_14_edit2.setTypeface(font);
//		class_15_edit2.setTypeface(font);
//		class_16_edit2.setTypeface(font);
//		class_17_edit2.setTypeface(font);
//		class_18_edit2.setTypeface(font);
//		class_19_edit2.setTypeface(font);
//		class_20_edit2.setTypeface(font);
//	}

	// ContextMenu의 메뉴 설정
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("몇교시 시간표를 만들까요?");

		menu.add(0, 1, 0, "1교시");
		menu.add(0, 2, 0, "2교시");
		menu.add(0, 3, 0, "3교시");
		menu.add(0, 4, 0, "4교시");
		menu.add(0, 5, 0, "5교시");
		menu.add(0, 6, 0, "6교시");
		menu.add(0, 7, 0, "7교시");
		menu.add(0, 8, 0, "8교시");
		menu.add(0, 9, 0, "9교시");
		menu.add(0, 10, 0, "10교시");
		menu.add(0, 11, 0, "11교시");
		menu.add(0, 12, 0, "12교시");
		menu.add(0, 13, 0, "13교시");
		menu.add(0, 14, 0, "14교시");
		menu.add(0, 15, 0, "15교시");
		menu.add(0, 16, 0, "16교시");
		menu.add(0, 17, 0, "17교시");
		menu.add(0, 18, 0, "18교시");
		menu.add(0, 19, 0, "19교시");
		menu.add(0, 20, 0, "20교시");
	}

	// ContextMenu의 어느 메뉴가 선택됐을때의 실행 동작
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			visible(1);
			return true;
		case 2:
			visible(2);
			return true;
		case 3:
			visible(3);
			return true;
		case 4:
			visible(4);
			return true;
		case 5:
			visible(5);
			return true;
		case 6:
			visible(6);
			return true;
		case 7:
			visible(7);
			return true;
		case 8:
			visible(8);
			return true;
		case 9:
			visible(9);
			return true;
		case 10:
			visible(10);
			return true;
		case 11:
			visible(11);
			return true;
		case 12:
			visible(12);
			return true;
		case 13:
			visible(13);
			return true;
		case 14:
			visible(14);
			return true;
		case 15:
			visible(15);
			return true;
		case 16:
			visible(16);
			return true;
		case 17:
			visible(17);
			return true;
		case 18:
			visible(18);
			return true;
		case 19:
			visible(19);
			return true;
		case 20:
			visible(20);
			return true;
		default:
			return true;
		}
	}

	// 변수 초기화
	private void init() {
		// TODO Auto-generated method stub
		choice = (Button) findViewById(R.id.choice);
		setting_ok = (Button) findViewById(R.id.setting_ok);
		go_main = (Button) findViewById(R.id.go_main);

		choice_btn_alpha = choice.getBackground();
		setting_ok_btn_alpha = setting_ok.getBackground();
		go_main_btn_alpha = go_main.getBackground();

		one = (TableRow) findViewById(R.id.one);
		two = (TableRow) findViewById(R.id.two);
		three = (TableRow) findViewById(R.id.three);
		four = (TableRow) findViewById(R.id.four);
		five = (TableRow) findViewById(R.id.five);
		six = (TableRow) findViewById(R.id.six);
		seven = (TableRow) findViewById(R.id.seven);
		eight = (TableRow) findViewById(R.id.eight);
		nine = (TableRow) findViewById(R.id.nine);
		ten = (TableRow) findViewById(R.id.ten);
		tenone = (TableRow) findViewById(R.id.tenone);
		tentwo = (TableRow) findViewById(R.id.tentwo);
		tenthree = (TableRow) findViewById(R.id.tenthree);
		tenfour = (TableRow) findViewById(R.id.tenfour);
		tenfive = (TableRow) findViewById(R.id.tenfive);
		tensix = (TableRow) findViewById(R.id.tensix);
		tenseven = (TableRow) findViewById(R.id.tenseven);
		teneight = (TableRow) findViewById(R.id.teneight);
		tennine = (TableRow) findViewById(R.id.tennine);
		twenty = (TableRow) findViewById(R.id.twenty);
		setting_button = (TableRow) findViewById(R.id.setting_button);

		class_1_text = (TextView) findViewById(R.id.class_1_text);
		class_2_text = (TextView) findViewById(R.id.class_2_text);
		class_3_text = (TextView) findViewById(R.id.class_3_text);
		class_4_text = (TextView) findViewById(R.id.class_4_text);
		class_5_text = (TextView) findViewById(R.id.class_5_text);
		class_6_text = (TextView) findViewById(R.id.class_6_text);
		class_7_text = (TextView) findViewById(R.id.class_7_text);
		class_8_text = (TextView) findViewById(R.id.class_8_text);
		class_9_text = (TextView) findViewById(R.id.class_9_text);
		class_10_text = (TextView) findViewById(R.id.class_10_text);
		class_11_text = (TextView) findViewById(R.id.class_11_text);
		class_12_text = (TextView) findViewById(R.id.class_12_text);
		class_13_text = (TextView) findViewById(R.id.class_13_text);
		class_14_text = (TextView) findViewById(R.id.class_14_text);
		class_15_text = (TextView) findViewById(R.id.class_15_text);
		class_16_text = (TextView) findViewById(R.id.class_16_text);
		class_17_text = (TextView) findViewById(R.id.class_17_text);
		class_18_text = (TextView) findViewById(R.id.class_18_text);
		class_19_text = (TextView) findViewById(R.id.class_19_text);
		class_20_text = (TextView) findViewById(R.id.class_20_text);

		class_1_edit1 = (EditText) findViewById(R.id.class_1_edit1);
		class_2_edit1 = (EditText) findViewById(R.id.class_2_edit1);
		class_3_edit1 = (EditText) findViewById(R.id.class_3_edit1);
		class_4_edit1 = (EditText) findViewById(R.id.class_4_edit1);
		class_5_edit1 = (EditText) findViewById(R.id.class_5_edit1);
		class_6_edit1 = (EditText) findViewById(R.id.class_6_edit1);
		class_7_edit1 = (EditText) findViewById(R.id.class_7_edit1);
		class_8_edit1 = (EditText) findViewById(R.id.class_8_edit1);
		class_9_edit1 = (EditText) findViewById(R.id.class_9_edit1);
		class_10_edit1 = (EditText) findViewById(R.id.class_10_edit1);
		class_11_edit1 = (EditText) findViewById(R.id.class_11_edit1);
		class_12_edit1 = (EditText) findViewById(R.id.class_12_edit1);
		class_13_edit1 = (EditText) findViewById(R.id.class_13_edit1);
		class_14_edit1 = (EditText) findViewById(R.id.class_14_edit1);
		class_15_edit1 = (EditText) findViewById(R.id.class_15_edit1);
		class_16_edit1 = (EditText) findViewById(R.id.class_16_edit1);
		class_17_edit1 = (EditText) findViewById(R.id.class_17_edit1);
		class_18_edit1 = (EditText) findViewById(R.id.class_18_edit1);
		class_19_edit1 = (EditText) findViewById(R.id.class_19_edit1);
		class_20_edit1 = (EditText) findViewById(R.id.class_20_edit1);

		class_1_edit2 = (EditText) findViewById(R.id.class_1_edit2);
		class_2_edit2 = (EditText) findViewById(R.id.class_2_edit2);
		class_3_edit2 = (EditText) findViewById(R.id.class_3_edit2);
		class_4_edit2 = (EditText) findViewById(R.id.class_4_edit2);
		class_5_edit2 = (EditText) findViewById(R.id.class_5_edit2);
		class_6_edit2 = (EditText) findViewById(R.id.class_6_edit2);
		class_7_edit2 = (EditText) findViewById(R.id.class_7_edit2);
		class_8_edit2 = (EditText) findViewById(R.id.class_8_edit2);
		class_9_edit2 = (EditText) findViewById(R.id.class_9_edit2);
		class_10_edit2 = (EditText) findViewById(R.id.class_10_edit2);
		class_11_edit2 = (EditText) findViewById(R.id.class_11_edit2);
		class_12_edit2 = (EditText) findViewById(R.id.class_12_edit2);
		class_13_edit2 = (EditText) findViewById(R.id.class_13_edit2);
		class_14_edit2 = (EditText) findViewById(R.id.class_14_edit2);
		class_15_edit2 = (EditText) findViewById(R.id.class_15_edit2);
		class_16_edit2 = (EditText) findViewById(R.id.class_16_edit2);
		class_17_edit2 = (EditText) findViewById(R.id.class_17_edit2);
		class_18_edit2 = (EditText) findViewById(R.id.class_18_edit2);
		class_19_edit2 = (EditText) findViewById(R.id.class_19_edit2);
		class_20_edit2 = (EditText) findViewById(R.id.class_20_edit2);
	}

	// 몇교시까지 보이게 할 것인지에 대한 정보
	private void visible(int a) {
		select = a;
		setting_button.setVisibility(0);
		choice.setVisibility(8);
		while (a > 0) {
			if (a == 1) {
				one.setVisibility(0);
			} else if (a == 2) {
				two.setVisibility(0);
			} else if (a == 3) {
				three.setVisibility(0);
			} else if (a == 4) {
				four.setVisibility(0);
			} else if (a == 5) {
				five.setVisibility(0);
			} else if (a == 6) {
				six.setVisibility(0);
			} else if (a == 7) {
				seven.setVisibility(0);
			} else if (a == 8) {
				eight.setVisibility(0);
			} else if (a == 9) {
				nine.setVisibility(0);
			} else if (a == 10) {
				ten.setVisibility(0);
			} else if (a == 11) {
				tenone.setVisibility(0);
			} else if (a == 12) {
				tentwo.setVisibility(0);
			} else if (a == 13) {
				tenthree.setVisibility(0);
			} else if (a == 14) {
				tenfour.setVisibility(0);
			} else if (a == 15) {
				tenfive.setVisibility(0);
			} else if (a == 16) {
				tensix.setVisibility(0);
			} else if (a == 17) {
				tenseven.setVisibility(0);
			} else if (a == 18) {
				teneight.setVisibility(0);
			} else if (a == 19) {
				tennine.setVisibility(0);
			} else if (a == 20) {
				twenty.setVisibility(0);
			}
			a--;
		}
	}

	// TimeTable로 보낼 값 설정
	private void setintent(int a) {
		// TODO Auto-generated method stub
		while (a > 0) {
			if (a == 1) {
				intent.putExtra("time_1", class_1_edit1.getText().toString()
						+ "\n　~　\n" + class_1_edit2.getText().toString());
			} else if (a == 2) {
				intent.putExtra("time_2", class_2_edit1.getText().toString()
						+ "\n　~　\n" + class_2_edit2.getText().toString());
			} else if (a == 3) {
				intent.putExtra("time_3", class_3_edit1.getText().toString()
						+ "\n　~　\n" + class_3_edit2.getText().toString());
			} else if (a == 4) {
				intent.putExtra("time_4", class_4_edit1.getText().toString()
						+ "\n　~　\n" + class_4_edit2.getText().toString());
			} else if (a == 5) {
				intent.putExtra("time_5", class_5_edit1.getText().toString()
						+ "\n　~　\n" + class_5_edit2.getText().toString());
			} else if (a == 6) {
				intent.putExtra("time_6", class_6_edit1.getText().toString()
						+ "\n　~　\n" + class_6_edit2.getText().toString());
			} else if (a == 7) {
				intent.putExtra("time_7", class_7_edit1.getText().toString()
						+ "\n　~　\n" + class_7_edit2.getText().toString());
			} else if (a == 8) {
				intent.putExtra("time_8", class_8_edit1.getText().toString()
						+ "\n　~　\n" + class_8_edit2.getText().toString());
			} else if (a == 9) {
				intent.putExtra("time_9", class_9_edit1.getText().toString()
						+ "\n　~　\n" + class_9_edit2.getText().toString());
			} else if (a == 10) {
				intent.putExtra("time_10", class_10_edit1.getText().toString()
						+ "\n　~　\n" + class_10_edit2.getText().toString());
			} else if (a == 11) {
				intent.putExtra("time_11", class_11_edit1.getText().toString()
						+ "\n　~　\n" + class_11_edit2.getText().toString());
			} else if (a == 12) {
				intent.putExtra("time_12", class_12_edit1.getText().toString()
						+ "\n　~　\n" + class_12_edit2.getText().toString());
			} else if (a == 13) {
				intent.putExtra("time_13", class_13_edit1.getText().toString()
						+ "\n　~　\n" + class_13_edit2.getText().toString());
			} else if (a == 14) {
				intent.putExtra("time_14", class_14_edit1.getText().toString()
						+ "\n　~　\n" + class_14_edit2.getText().toString());
			} else if (a == 15) {
				intent.putExtra("time_15", class_15_edit1.getText().toString()
						+ "\n　~　\n" + class_15_edit2.getText().toString());
			} else if (a == 16) {
				intent.putExtra("time_16", class_16_edit1.getText().toString()
						+ "\n　~　\n" + class_16_edit2.getText().toString());
			} else if (a == 17) {
				intent.putExtra("time_17", class_17_edit1.getText().toString()
						+ "\n　~　\n" + class_17_edit2.getText().toString());
			} else if (a == 18) {
				intent.putExtra("time_18", class_18_edit1.getText().toString()
						+ "\n　~　\n" + class_18_edit2.getText().toString());
			} else if (a == 19) {
				intent.putExtra("time_19", class_19_edit1.getText().toString()
						+ "\n　~　\n" + class_19_edit2.getText().toString());
			} else if (a == 20) {
				intent.putExtra("time_20", class_20_edit1.getText().toString()
						+ "\n　~　\n" + class_20_edit2.getText().toString());
			}
			--a;
		}
	}

	// 투명도 조정
	private void makealpha() {
		// TODO Auto-generated method stub
		choice_btn_alpha.setAlpha(200);
		setting_ok_btn_alpha.setAlpha(200);
		go_main_btn_alpha.setAlpha(200);
	}

	// 이벤트 설정
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int selected = v.getId();
		switch (selected) {
		case R.id.go_main:
			finish();
			break;
		case R.id.setting_ok:
			finish();
			intent = new Intent(TimeTableSetting.this, TimeTable.class);
			intent.putExtra("select_num", select);
			setintent(select);
			startActivity(intent);
			break;
		}
	}
}