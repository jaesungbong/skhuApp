package com.example.skhuapp.collegeschedule;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.skhuapp.R;

public class CollegeScheduleActivity extends Activity {

	public static int SUNDAY = 1;
	public static int MONDAY = 2;
	public static int TUESDAY = 3;
	public static int WEDNSEDAY = 4;
	public static int THURSDAY = 5;
	public static int FRIDAY = 6;
	public static int SATURDAY = 7;
	ArrayList<String> parsedDay;
	ArrayList<String> period;
	ArrayList<String> content;
	ParseCalender parse;
	Calendar thisMonth;
	TextView current, news;
	GridView calender;
	Button last, next;

	final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			getCalender(thisMonth);
		};
	};

	ArrayList<DayData> mDayList;
	CalenderAdapter mCalenderAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collegeschedule);
		mDayList = new ArrayList<DayData>();
		current = (TextView) findViewById(R.id.current_calender);
		news = (TextView)findViewById(R.id.news);
		calender = (GridView) findViewById(R.id.calender);
		last = (Button) findViewById(R.id.last);
		news = (TextView) findViewById(R.id.news);

		parsedDay = new ArrayList<String>();
		period = new ArrayList<String>();
		content = new ArrayList<String>();

		thisMonth = Calendar.getInstance();
		thisMonth.set(Calendar.DAY_OF_MONTH, 1);

		parse = new ParseCalender(CollegeScheduleActivity.this, handler,
				thisMonth, parsedDay, period, content);
		parse.parsing();

		last.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				parse = new ParseCalender(CollegeScheduleActivity.this,
						handler, getLastMonth(thisMonth), parsedDay, period,
						content);
				parse.parsing();
			}
		});
		next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				parse = new ParseCalender(CollegeScheduleActivity.this,
						handler, getNextMonth(thisMonth), parsedDay, period,
						content);
				parse.parsing();
			}
		});

		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	private void getCalender(Calendar calender) {
		int lastDays;// 이번달 달력에서 표시할 지난달의 날짜들
		int dayOfMonth;// 이 달의 시작 요일
		int totalDayOfThisMonth;// 이 달이 몇일까지 있는지

		mDayList.clear();

		dayOfMonth = calender.get(Calendar.DAY_OF_WEEK); // 현재 달의 시작 요일
		totalDayOfThisMonth = calender.getActualMaximum(Calendar.DAY_OF_MONTH); // 이
																				// 달이
																				// 몇일까지
																				// 있는가

		calender.add(Calendar.MONTH, -1); // 달을 지난 달로 초기화
		lastDays = calender.getActualMaximum(Calendar.DAY_OF_MONTH);// 지난 달이
																	// 몇일까지 있는가

		calender.add(Calendar.MONTH, 1); // 달을 이번 달로 초기화

		if (dayOfMonth == SUNDAY) {
			dayOfMonth += 7;
		}

		lastDays -= (dayOfMonth - 1) - 1; // 지난달 몇일부터 이달 달력에 표시 될 것인가.

		current.setText(thisMonth.get(Calendar.YEAR) + "년 "
				+ (thisMonth.get(Calendar.MONTH) + 1) + "월");
		
		String newsAndContent ="";
		for(int i=0;i<period.size();i++){
			newsAndContent += period.get(i)+"\n"+content.get(i)+"\n\n";
		}
		news.setText(newsAndContent);

		DayData day;

		String dayOfWeek[] = { "일", "월", "화", "수", "목", "금", "토" };

		for (int i = 0; i < 7; i++) {
			day = new DayData();
			day.day = dayOfWeek[i];
			day.isThisMonth = true;
			day.isFirstLine = true;
			mDayList.add(day);
		}

		for (int i = 0; i < dayOfMonth - 1; i++) {
			int date = lastDays + i;
			day = new DayData();
			day.day = Integer.toString(date);
			day.isThisMonth = false;
			day.isFirstLine = false;
			day.isEvent = false;
			mDayList.add(day);
		}// 달력의 지난 달 셋팅
		for (int i = 1; i <= totalDayOfThisMonth; i++) {
			day = new DayData();
			String date = Integer.toString(i);
			day.day = date;
			day.isThisMonth = true;
			day.isFirstLine = false;
			day.isEvent = isEvent(date);
			mDayList.add(day);
		}// 달력의 이번달 셋팅
		for (int i = 1; i < 42 - (totalDayOfThisMonth + dayOfMonth - 1) + 1; i++) {
			day = new DayData();
			day.day = Integer.toString(i);
			day.isThisMonth = false;
			day.isFirstLine = false;
			day.isEvent = false;
			mDayList.add(day);
		}// 달력의 다음달 셋팅
		initCalenderAdapter();
	}

	private Calendar getLastMonth(Calendar calendar) {
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1);
		calendar.add(Calendar.MONTH, -1);
		current.setText(thisMonth.get(Calendar.YEAR) + "년 "
				+ (thisMonth.get(Calendar.MONTH) + 1) + "월");
		return calendar;
	}

	private Calendar getNextMonth(Calendar calendar) {
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1);
		calendar.add(Calendar.MONTH, +1);
		current.setText(thisMonth.get(Calendar.YEAR) + "년 "
				+ (thisMonth.get(Calendar.MONTH) + 1) + "월");
		return calendar;
	}

	private void initCalenderAdapter() {
		mCalenderAdapter = new CalenderAdapter(this, mDayList);
		calender.setAdapter(mCalenderAdapter);
	}

	private boolean isEvent(String date) {
		for (int i = 0; i < parsedDay.size(); i++) {
			if (date.equals(parsedDay.get(i))) {
				return true;
			}
		}
		return false;
	}
}