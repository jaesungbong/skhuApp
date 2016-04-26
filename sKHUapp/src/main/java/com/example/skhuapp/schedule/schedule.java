package com.example.skhuapp.schedule;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import com.example.skhuapp.R;

public class schedule extends Activity implements OnClickListener{
	
	private Button make_timetable, road_timetable, delete_timetable, help, exit;	
	private TextView main_text;														
	private Intent intent;															
	private AlertDialog.Builder message_box;							
	private AlertDialog ad;												
	private Database db = null;											
	private SQLiteDatabase sql_db;												
	private Cursor cursor;													
	private int num = 1;													
	private Drawable make_btn_alpha, road_btn_alpha, delete_btn_alpha;	
	private static int road_or_clear = 0;									

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prj1);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		try {
			db_create();					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}

		init();					
		message_box();					
		makealpha();				

		//	�̺�Ʈ ����
		make_timetable.setOnClickListener(this);
		registerForContextMenu(road_timetable);
		registerForContextMenu(delete_timetable);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	private void db_create() throws IOException{
		File folder = new File("/data/data/abc.prj1/databases/");
		folder.mkdirs();
		File outfile = new File("/data/data/abc.prj1/databases/table.db");
		AssetManager am = getResources().getAssets();
		InputStream is = am.open("database/table.db", AssetManager.ACCESS_BUFFER);
		long filesize = is.available();

		if(outfile.length() < filesize){
			byte[] data = new byte[(int)filesize];
			is.read(data);
			is.close();
			outfile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outfile);
			fos.write(data);
			fos.close();
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		db = new Database(this);
		message_box = new AlertDialog.Builder(this);

		make_timetable = (Button)findViewById(R.id.make_timetable);
		road_timetable = (Button)findViewById(R.id.road_timetable);
		delete_timetable = (Button)findViewById(R.id.delete_timetable);


		make_btn_alpha = make_timetable.getBackground();
		road_btn_alpha = road_timetable.getBackground();
		delete_btn_alpha = delete_timetable.getBackground();

	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		db = new Database(this);
		if(v == road_timetable){
			menu.setHeaderTitle("어떤 시간표를 불러올까요?");
			sql_db = db.getReadableDatabase();
			cursor = sql_db.rawQuery("select name from SaveTable", null);
			if(cursor.moveToNext()){
				cursor.close();
				String name = "";
				cursor = sql_db.rawQuery("select name from SaveTable", null);
				while(cursor.moveToNext()){
					if(name.equals(cursor.getString(0)))
						continue;
					else{
						name = cursor.getString(0);
						menu.add(num, 0, 0, name);
						num++;
					}
				}
				num = 0;
				cursor.close();
				db.close();
			}
			road_or_clear = 0;
		}
		else{
			menu.setHeaderTitle("어떤 시간표를 삭제할까요?");

			sql_db = db.getReadableDatabase();
			cursor = sql_db.rawQuery("select name from SaveTable", null);

			if(cursor.moveToNext()){
				cursor.close();
				String name = "";
				cursor = sql_db.rawQuery("select name from SaveTable", null);
				while(cursor.moveToNext()){
					if(name.equals(cursor.getString(0)))
						continue;
					else{
						name = cursor.getString(0);
						menu.add(0, num, 0, name);
						num++;
					}
				}
				num = 0;
				cursor.close();
				db.close();
			}
			road_or_clear = 1;
		}
	}

	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(road_or_clear == 0){	
			finish();
			intent = new Intent(schedule.this, TimeTable.class);
			intent.putExtra("table_name", item.getTitle());
			startActivity(intent);
			return true;
		}
		else{	
			sql_db = db.getWritableDatabase();
			sql_db.execSQL("delete from SaveTable where name='"+item.getTitle()+"';");
			db.close();
			ad.show();
		}
		return true;
	}

	private void message_box() {
		// TODO Auto-generated method stub
		message_box.setIcon(R.drawable.android_logo);
		message_box.setTitle("정말 삭제 하시겠습니까?");
		message_box.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		ad = message_box.create();
	}

	private void makealpha(){
		// TODO Auto-generated method stub
		make_btn_alpha.setAlpha(200);
		road_btn_alpha.setAlpha(200);
		delete_btn_alpha.setAlpha(200);
	}

	public void onClick(View v) {
		if(v == make_timetable){
			finish();
			intent = new Intent(schedule.this, TimeTableSetting.class);
			startActivity(intent);
		}
//		else if(v == help){
//			intent = new Intent(schedule.this, Help.class);
//			startActivity(intent);
//		}
		else{
			finish();
		}
	}
}