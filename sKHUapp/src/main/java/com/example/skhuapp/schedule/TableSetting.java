package com.example.skhuapp.schedule;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.skhuapp.R;

public class TableSetting extends Activity implements OnClickListener {
	
	private Intent intent, get_intent;		//	보내는 인텐트와 받는 인텐트
	private Button table_back, table_save, table_delete;				//	뒤로가기, 삭제하기, 저장하기 버튼
	private TextView what_day, what_time, what_contents;				//	무슨 요일인지 몇교시인지 무슨 내용인지를 나타내는 TextView				
	private EditText what_day_text, what_time_text, what_contents_text;	//	위의 TextView에 해당하는 내용을 입력하는 EditText
	private String what_time_text_="";		//	몇교시인지를 넣어둘 변수
	private Database db;					//	Database 클레스 선언
	private SQLiteDatabase sql_db;			//	db정보
	private Cursor cursor;					//	select쿼리문을 쓰기 위해 선언
//	private Drawable back_btn_alpha, save_btn_alpha;	//	투명도 선언
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablesetting);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);	// 화면을 전체화면으로 변경
		
		init();			//	변수 초기화
//		makealpha();	//	투명도 조정
		getintent();	//	받은 인텐트값 설정
		
		//	이벤트 선언
		table_back.setOnClickListener(this);
		table_save.setOnClickListener(this);
		table_delete.setOnClickListener(this);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	//	변수 초기화
	private void init() {
		// TODO Auto-generated method stub
		get_intent = getIntent();
		intent = new Intent(TableSetting.this, TimeTable.class);
		db = new Database(this);
		
		table_back = (Button)findViewById(R.id.table_back);
		table_save = (Button)findViewById(R.id.table_save);
		table_delete = (Button)findViewById(R.id.table_delete);
		
		what_day = (TextView)findViewById(R.id.what_day);
		what_time = (TextView)findViewById(R.id.what_time);
		what_contents = (TextView)findViewById(R.id.what_contents);
		
		what_day_text = (EditText)findViewById(R.id.what_day_text);
		what_time_text = (EditText)findViewById(R.id.what_time_text);
		what_contents_text = (EditText)findViewById(R.id.what_contents_text);
		
//		back_btn_alpha = table_back.getBackground();
//		save_btn_alpha = table_save.getBackground();
	}
	
//	
//	//	투명도 조정
//	private void makealpha(){
//		// TODO Auto-generated method stub
//		back_btn_alpha.setAlpha(200);
//		save_btn_alpha.setAlpha(200);
//	}
//	
	//	받은 인텐트값 설정
	private void getintent(){
		what_day_text.setText(get_intent.getStringExtra("day"));
		what_time_text.setText(get_intent.getStringExtra("time"));
		what_contents_text.setText(get_intent.getStringExtra("contents"));
		what_time_text_ = get_intent.getStringExtra("time_").toString();
	}

	//	이벤트 설정
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == table_back){
			finish();
			intent = new Intent(TableSetting.this, TimeTable.class);
			intent.putExtra("notstart", "ok");
			startActivity(intent);
		}
		else if(v == table_delete){
			finish();
			sql_db = db.getWritableDatabase();
			sql_db.execSQL("delete from TimeTable where time_='"+what_time_text_+"' and day='"+what_day_text.getText().toString()+"';");
			db.close();
			intent.putExtra("notstart", "ok");
			startActivity(intent);
		}
		else{
			finish();
			sql_db = db.getReadableDatabase();
			cursor = sql_db.rawQuery("select contents from TimeTable where time_='"+what_time_text_+"' and day='"+what_day_text.getText().toString()+"';", null);
			sql_db = db.getWritableDatabase();
			if(cursor.moveToNext())
					sql_db.execSQL("UPDATE TimeTable SET contents = '"+what_contents_text.getText().toString()+"' WHERE time_ = '"+what_time_text_+"' and day = '"+what_day_text.getText().toString()+"';");
			else
				if(!what_contents_text.getText().toString().equals(""))
					sql_db.execSQL("insert into TimeTable values ('"+what_time_text_+"', '"+what_time_text.getText().toString()+"', '"+what_day_text.getText().toString()+"', '"+what_contents_text.getText().toString()+"');");
			cursor.close();
			db.close();
			intent.putExtra("notstart", "ok");
			startActivity(intent);
		}
	}
}
