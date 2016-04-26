package com.example.skhuapp.schedule;

import android.app.*;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.*;

import com.example.skhuapp.R;
public class TimeTable extends Activity implements OnClickListener{
	public Intent intent, get_intent;	//	보내는 인텐트와 받는 인텐트
	private TableRow row_1, row_2, row_3, row_4, row_5, row_6, row_7, row_8, row_9, row_10,	//	가로에 대한 정보 (한 교시당)
	row_11, row_12, row_13, row_14, row_15, row_16, row_17, row_18, row_19, row_20;
	private TextView mon, tue, wed, thu, fri, sat, sun;	//	요일에 대한 정보
	private TextView what;								//	상단좌측의 빈공간
	private TextView time_1, time_2, time_3, time_4, time_5, time_6, time_7, time_8, time_9, time_10,//	좌측세로의 각각에 대한 정보 (20교시)
	time_11, time_12, time_13, time_14, time_15, time_16, time_17, time_18, time_19, time_20;
	private TextView[][] day = new TextView[20][7];		//	contents를 담는 곳
	private TextView alert_text;						//	다이얼로그의 입력 텍스트
	private EditText alert_edit;						//	다이얼로그의 텍스트바
	private Database db = null;							//	Database클레스 정보
	private SQLiteDatabase sql_db;						//	db정보
	private Cursor cursor;								//	select쿼리문을 쓰기 위해 선언
	private static int select_num;						//	교시 설정에서 몇 교시인지를 선택한 int변수
	private static String one, two, three, four, five, six, seven, eight, nine, ten,	//	각 교시에 설정된 시간을 담는 변수
	tenone, tentwo, tenthree, tenfour, tenfive, tensix, tenseven, teneight, tennine, twenty;
	private AlertDialog.Builder save_message_box, update_message_box, saveok_message_box;//	다이얼로그
	private Context ctx;								//	Context 변수 선언 (다른 레이아웃을 현재 레이아웃에 적용하기 위해 사용)
	private LayoutInflater factory;						//	LayoutInflater 변수 선언 (다른 레이아웃을 현재 레이아웃에 적용하기 위해 사용)
	private View dialog;								//	뷰 선언 (다른 레이아웃을 현재 레이아웃에 적용하기 위해 사용)
	private AlertDialog save_ad, update_ad, saveok_ad;	//	다이얼로그를 사용하기 위한 변수
	private Drawable what_alpha;						//	투명도 설정
	private Drawable[] time_alpha = new Drawable[20];	//	교시의 투명도
	private Drawable[] day_alpha = new Drawable[7];		//	요일의 투명도
	private Drawable[][] contents_alpha = new Drawable[20][7];	//	내용의 투명도

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);	// 화면을 전체화면으로 변경
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		init();						//	변수 초기화
		save();						//	TimeTableSetting클레스 (교시 선택 후 입력된 시간)로부터 값을 받아 static 변수에 입력
		show(select_num);			//	화면에 보여질 줄 수 (교시)
		setting();					//	초기 TimeTable 화면을 셋팅
		save_message_box();			//	저장을 눌렀을 경우의 다이얼로그
		update_message_box();		//	저장을 눌렀을 경우, update 필요시 사용되는 다이얼로그
		saveok_message_box();		//	저장이 완료되었을 경우의 다이얼로그
		setColor();					//	각 요일마다 해당되는 contents의 배경화면 지정
//		makealpha();				//	각 TextView 투명도 지정

		//	contents 부분의 TextView에 이벤트 지정
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 7; j++)
				day[i][j].setOnClickListener(this);
		
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	//	옵션 메뉴
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	// 메뉴옵션은 두개를 만듦
		menu.add(0, 1, 0, "저장");
		menu.add(0, 2, 0, "종료");
		return true;
	}

	//	옵션 메뉴 호출
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == 1){
			save_ad.show();
		}
		else if(item.getItemId() == 2){
			finish();
		}
		return true;
	}

	//	저장을 눌렀을 경우의 다이얼로그
	private void save_message_box() {
		// TODO Auto-generated method stub
		save_message_box.setTitle("저장할 이름을 입력하세요");
		save_message_box.setView(dialog);
		save_message_box.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				sql_db = db.getReadableDatabase();
				cursor = sql_db.rawQuery("select contents from TimeTable", null);
				if(!cursor.moveToNext())
					Toast.makeText(TimeTable.this, "내용이 없습니다", 1000).show();
				cursor = sql_db.rawQuery("select name from SaveTable where name='"+alert_edit.getText().toString()+"';", null);
				if(cursor.moveToNext()){
					cursor.close();
					db.close();
					update_ad.show();
				}
				else{
					cursor.close();
					sql_db = db.getWritableDatabase();
					cursor = sql_db.rawQuery("select * from TimeTable", null);
					while(cursor.moveToNext()){
						sql_db.execSQL("insert into SaveTable values ("+select_num+", '"+alert_edit.getText().toString()+"', '"+cursor.getString(0)+"', '"+cursor.getString(1)+"', '"+cursor.getString(2)+"', '"+cursor.getString(3)+"');");
					}
					saveok_ad.show();
				}
				cursor.close();
				db.close();
			}
		});
		save_ad = save_message_box.create();
	}

	//	저장을 눌렀을 경우, update 필요시 사용되는 다이얼로그
	private void update_message_box(){
		// TODO Auto-generated method stub
		update_message_box.setIcon(R.drawable.android_logo);
		update_message_box.setTitle("중복된 이름이 있습니다.");
		update_message_box.setMessage("그래도 저장하시겠습니까?");
		update_message_box.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				sql_db = db.getReadableDatabase();
				cursor = sql_db.rawQuery("select name from SaveTable", null);
				sql_db = db.getWritableDatabase();
				while(cursor.moveToNext()){
					sql_db.execSQL("delete from SaveTable where name='"+alert_edit.getText().toString()+"';");
				}
				cursor.close();
				sql_db = db.getReadableDatabase();
				cursor = sql_db.rawQuery("select * from TimeTable", null);
				sql_db = db.getWritableDatabase();
				while(cursor.moveToNext()){					
					sql_db.execSQL("insert into SaveTable values ("+select_num+", '"+alert_edit.getText().toString()+"', '"+cursor.getString(0)+"', '"+cursor.getString(1)+"', '"+cursor.getString(2)+"', '"+cursor.getString(3)+"');");
				}
				cursor.close();
				db.close();
				saveok_ad.show();
			}
		});
		update_message_box.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		update_ad = update_message_box.create();
	}

	//	저장이 완료되었을 경우의 다이얼로그
	private void saveok_message_box(){
		// TODO Auto-generated method stub
		saveok_message_box.setIcon(R.drawable.android_logo);
		saveok_message_box.setTitle("저장이 완료되었습니다.");
		saveok_message_box.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}
		});
		saveok_ad = saveok_message_box.create();
	}

	//	TimeTableSetting클레스 (교시 선택 후 입력된 시간)로부터 값을 받아 static 변수에 입력
	private void save() {
		// TODO Auto-generated method stub
		if(get_intent.getIntExtra("select_num", 0) != 0)
			select_num = get_intent.getIntExtra("select_num", 0);
		if(get_intent.getStringExtra("time_1") != null)
			one = get_intent.getStringExtra("time_1").toString();
		if(get_intent.getStringExtra("time_2") != null)
			two = get_intent.getStringExtra("time_2").toString();
		if(get_intent.getStringExtra("time_3") != null)
			three = get_intent.getStringExtra("time_3").toString();
		if(get_intent.getStringExtra("time_4") != null)
			four = get_intent.getStringExtra("time_4").toString();
		if(get_intent.getStringExtra("time_5") != null)
			five = get_intent.getStringExtra("time_5").toString();
		if(get_intent.getStringExtra("time_6") != null)
			six = get_intent.getStringExtra("time_6").toString();
		if(get_intent.getStringExtra("time_7") != null)
			seven = get_intent.getStringExtra("time_7").toString();
		if(get_intent.getStringExtra("time_8") != null)
			eight = get_intent.getStringExtra("time_8").toString();
		if(get_intent.getStringExtra("time_9") != null)
			nine = get_intent.getStringExtra("time_9").toString();
		if(get_intent.getStringExtra("time_10") != null)
			ten = get_intent.getStringExtra("time_10").toString();
		if(get_intent.getStringExtra("time_11") != null)
			tenone = get_intent.getStringExtra("time_11").toString();
		if(get_intent.getStringExtra("time_12") != null)
			tentwo = get_intent.getStringExtra("time_12").toString();
		if(get_intent.getStringExtra("time_13") != null)
			tenthree = get_intent.getStringExtra("time_13").toString();
		if(get_intent.getStringExtra("time_14") != null)
			tenfour = get_intent.getStringExtra("time_14").toString();
		if(get_intent.getStringExtra("time_15") != null)
			tenfive = get_intent.getStringExtra("time_15").toString();
		if(get_intent.getStringExtra("time_16") != null)
			tensix = get_intent.getStringExtra("time_16").toString();
		if(get_intent.getStringExtra("time_17") != null)
			tenseven = get_intent.getStringExtra("time_17").toString();
		if(get_intent.getStringExtra("time_18") != null)
			teneight = get_intent.getStringExtra("time_18").toString();
		if(get_intent.getStringExtra("time_19") != null)
			tennine = get_intent.getStringExtra("time_19").toString();
		if(get_intent.getStringExtra("time_20") != null)
			twenty = get_intent.getStringExtra("time_20").toString();
	}

	//	초기 TimeTable 화면을 셋팅
	private void setting() {
		// TODO Auto-generated method stub
		if(get_intent.getStringExtra("notstart") == null){	// 완전 처음 시작하는 경우
			sql_db = db.getWritableDatabase();
			sql_db.execSQL("delete from TimeTable");
			db.close();
		}
		if(get_intent.getStringExtra("table_name") != null){	// 불러오기인 경우
			sql_db = db.getReadableDatabase();
			cursor = sql_db.rawQuery("select * from SaveTable where name='"+get_intent.getStringExtra("table_name").toString()+"';", null);
			sql_db = db.getWritableDatabase();
			while(cursor.moveToNext()){
				sql_db.execSQL("insert into TimeTable values ('"+cursor.getString(2)+"', '"+cursor.getString(3)+"', '"+cursor.getString(4)+"', '"+cursor.getString(5)+"');");
				select_num = cursor.getInt(0);
				show(select_num);
			}
			cursor.close();
			sql_db = db.getReadableDatabase();
			cursor = sql_db.rawQuery("select * from TimeTable", null);
			while(cursor.moveToNext()){

				int time = new Filter().Filter_time(cursor.getString(0));
				int day = new Filter().Filter_day(cursor.getString(2));
				this.day[time][day].setText(cursor.getString(3));
				if(time == 0){
					one = cursor.getString(1);
					time_1.setText(one);
				}
				else if(time == 1){
					two = cursor.getString(1);
					time_2.setText(two);
				}
				else if(time == 2){
					three = cursor.getString(1);
					time_3.setText(three);
				}
				else if(time == 3){
					four = cursor.getString(1);
					time_4.setText(four);
				}
				else if(time == 4){
					five = cursor.getString(1);
					time_5.setText(five);
				}
				else if(time == 5){
					six = cursor.getString(1);
					time_6.setText(six);
				}
				else if(time == 6){
					seven = cursor.getString(1);
					time_7.setText(seven);
				}
				else if(time == 7){
					eight = cursor.getString(1);
					time_8.setText(eight);
				}
				else if(time == 8){
					nine = cursor.getString(1);
					time_9.setText(nine);
				}
				else if(time == 9){
					ten = cursor.getString(1);
					time_10.setText(ten);
				}
				else if(time == 10){
					tenone = cursor.getString(1);
					time_11.setText(tenone);
				}
				else if(time == 11){
					tentwo = cursor.getString(1);
					time_12.setText(tentwo);
				}
				else if(time == 12){
					tenthree = cursor.getString(1);
					time_13.setText(tenthree);
				}
				else if(time == 13){
					tenfour = cursor.getString(1);
					time_14.setText(tenfour);
				}
				else if(time == 14){
					tenfive = cursor.getString(1);
					time_15.setText(tenfive);
				}
				else if(time == 15){
					tensix = cursor.getString(1);
					time_16.setText(tensix);
				}
				else if(time == 16){
					tenseven = cursor.getString(1);
					time_17.setText(tenseven);
				}
				else if(time == 17){
					teneight = cursor.getString(1);
					time_18.setText(teneight);
				}
				else if(time == 18){
					tennine = cursor.getString(1);
					time_19.setText(tennine);
				}
				else if(time == 19){
					twenty = cursor.getString(1);
					time_20.setText(twenty);
				}
			}
			cursor.close();
			db.close();
		}
		else{	// 인텐트로 값을 받았을 경우
			sql_db = db.getReadableDatabase();
			cursor = sql_db.rawQuery("select * from TimeTable", null);
			while(cursor.moveToNext()){
				int time = new Filter().Filter_time(cursor.getString(0));
				int day = new Filter().Filter_day(cursor.getString(2));
				this.day[time][day].setText(cursor.getString(3));
			}
			cursor.close();
			db.close();
		}
	}

	//	변수 초기화
	private void init() {
		// TODO Auto-generated method stub
		get_intent = getIntent();
		db = new Database(this);

		save_message_box = new AlertDialog.Builder(this);
		update_message_box = new AlertDialog.Builder(this);
		saveok_message_box = new AlertDialog.Builder(this);
		ctx = getApplicationContext();
		factory = (LayoutInflater)ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
		dialog = factory.inflate(R.layout.alert, null);
		alert_edit = (EditText)dialog.findViewById(R.id.alert_edit);
		alert_text = (TextView)dialog.findViewById(R.id.alert_text);

		what = (TextView)findViewById(R.id.what);

		row_1 = (TableRow)findViewById(R.id.row_1);
		row_2 = (TableRow)findViewById(R.id.row_2);
		row_3 = (TableRow)findViewById(R.id.row_3);
		row_4 = (TableRow)findViewById(R.id.row_4);
		row_5 = (TableRow)findViewById(R.id.row_5);
		row_6 = (TableRow)findViewById(R.id.row_6);
		row_7 = (TableRow)findViewById(R.id.row_7);
		row_8 = (TableRow)findViewById(R.id.row_8);
		row_9 = (TableRow)findViewById(R.id.row_9);
		row_10 = (TableRow)findViewById(R.id.row_10);
		row_11 = (TableRow)findViewById(R.id.row_11);
		row_12 = (TableRow)findViewById(R.id.row_12);
		row_13 = (TableRow)findViewById(R.id.row_13);
		row_14 = (TableRow)findViewById(R.id.row_14);
		row_15 = (TableRow)findViewById(R.id.row_15);
		row_16 = (TableRow)findViewById(R.id.row_16);
		row_17 = (TableRow)findViewById(R.id.row_17);
		row_18 = (TableRow)findViewById(R.id.row_18);
		row_19 = (TableRow)findViewById(R.id.row_19);
		row_20 = (TableRow)findViewById(R.id.row_20);

		mon = (TextView)findViewById(R.id.mon);
		tue = (TextView)findViewById(R.id.tue);
		wed = (TextView)findViewById(R.id.wed);
		thu = (TextView)findViewById(R.id.thu);
		fri = (TextView)findViewById(R.id.fri);
		sat = (TextView)findViewById(R.id.sat);
		sun = (TextView)findViewById(R.id.sun);

		time_1 = (TextView)findViewById(R.id.time_1);
		time_2 = (TextView)findViewById(R.id.time_2);
		time_3 = (TextView)findViewById(R.id.time_3);
		time_4 = (TextView)findViewById(R.id.time_4);
		time_5 = (TextView)findViewById(R.id.time_5);
		time_6 = (TextView)findViewById(R.id.time_6);
		time_7 = (TextView)findViewById(R.id.time_7);
		time_8 = (TextView)findViewById(R.id.time_8);
		time_9 = (TextView)findViewById(R.id.time_9);
		time_10 = (TextView)findViewById(R.id.time_10);
		time_11 = (TextView)findViewById(R.id.time_11);
		time_12 = (TextView)findViewById(R.id.time_12);
		time_13 = (TextView)findViewById(R.id.time_13);
		time_14 = (TextView)findViewById(R.id.time_14);
		time_15 = (TextView)findViewById(R.id.time_15);
		time_16 = (TextView)findViewById(R.id.time_16);
		time_17 = (TextView)findViewById(R.id.time_17);
		time_18 = (TextView)findViewById(R.id.time_18);
		time_19 = (TextView)findViewById(R.id.time_19);
		time_20 = (TextView)findViewById(R.id.time_20);

		day[0][0] = (TextView)findViewById(R.id.mon_1);
		day[0][1] = (TextView)findViewById(R.id.tue_1);
		day[0][2] = (TextView)findViewById(R.id.wed_1);
		day[0][3] = (TextView)findViewById(R.id.thu_1);
		day[0][4] = (TextView)findViewById(R.id.fri_1);
		day[0][5] = (TextView)findViewById(R.id.sat_1);
		day[0][6] = (TextView)findViewById(R.id.sun_1);

		day[1][0] = (TextView)findViewById(R.id.mon_2);
		day[1][1] = (TextView)findViewById(R.id.tue_2);
		day[1][2] = (TextView)findViewById(R.id.wed_2);
		day[1][3] = (TextView)findViewById(R.id.thu_2);
		day[1][4] = (TextView)findViewById(R.id.fri_2);
		day[1][5] = (TextView)findViewById(R.id.sat_2);
		day[1][6] = (TextView)findViewById(R.id.sun_2);

		day[2][0] = (TextView)findViewById(R.id.mon_3);
		day[2][1] = (TextView)findViewById(R.id.tue_3);
		day[2][2] = (TextView)findViewById(R.id.wed_3);
		day[2][3] = (TextView)findViewById(R.id.thu_3);
		day[2][4] = (TextView)findViewById(R.id.fri_3);
		day[2][5] = (TextView)findViewById(R.id.sat_3);
		day[2][6] = (TextView)findViewById(R.id.sun_3);

		day[3][0] = (TextView)findViewById(R.id.mon_4);
		day[3][1] = (TextView)findViewById(R.id.tue_4);
		day[3][2] = (TextView)findViewById(R.id.wed_4);
		day[3][3] = (TextView)findViewById(R.id.thu_4);
		day[3][4] = (TextView)findViewById(R.id.fri_4);
		day[3][5] = (TextView)findViewById(R.id.sat_4);
		day[3][6] = (TextView)findViewById(R.id.sun_4);

		day[4][0] = (TextView)findViewById(R.id.mon_5);
		day[4][1] = (TextView)findViewById(R.id.tue_5);
		day[4][2] = (TextView)findViewById(R.id.wed_5);
		day[4][3] = (TextView)findViewById(R.id.thu_5);
		day[4][4] = (TextView)findViewById(R.id.fri_5);
		day[4][5] = (TextView)findViewById(R.id.sat_5);
		day[4][6] = (TextView)findViewById(R.id.sun_5);

		day[5][0] = (TextView)findViewById(R.id.mon_6);
		day[5][1] = (TextView)findViewById(R.id.tue_6);
		day[5][2] = (TextView)findViewById(R.id.wed_6);
		day[5][3] = (TextView)findViewById(R.id.thu_6);
		day[5][4] = (TextView)findViewById(R.id.fri_6);
		day[5][5] = (TextView)findViewById(R.id.sat_6);
		day[5][6] = (TextView)findViewById(R.id.sun_6);

		day[6][0] = (TextView)findViewById(R.id.mon_7);
		day[6][1] = (TextView)findViewById(R.id.tue_7);
		day[6][2] = (TextView)findViewById(R.id.wed_7);
		day[6][3] = (TextView)findViewById(R.id.thu_7);
		day[6][4] = (TextView)findViewById(R.id.fri_7);
		day[6][5] = (TextView)findViewById(R.id.sat_7);
		day[6][6] = (TextView)findViewById(R.id.sun_7);

		day[7][0] = (TextView)findViewById(R.id.mon_8);
		day[7][1] = (TextView)findViewById(R.id.tue_8);
		day[7][2] = (TextView)findViewById(R.id.wed_8);
		day[7][3] = (TextView)findViewById(R.id.thu_8);
		day[7][4] = (TextView)findViewById(R.id.fri_8);
		day[7][5] = (TextView)findViewById(R.id.sat_8);
		day[7][6] = (TextView)findViewById(R.id.sun_8);

		day[8][0] = (TextView)findViewById(R.id.mon_9);
		day[8][1] = (TextView)findViewById(R.id.tue_9);
		day[8][2] = (TextView)findViewById(R.id.wed_9);
		day[8][3] = (TextView)findViewById(R.id.thu_9);
		day[8][4] = (TextView)findViewById(R.id.fri_9);
		day[8][5] = (TextView)findViewById(R.id.sat_9);
		day[8][6] = (TextView)findViewById(R.id.sun_9);

		day[9][0] = (TextView)findViewById(R.id.mon_10);
		day[9][1] = (TextView)findViewById(R.id.tue_10);
		day[9][2] = (TextView)findViewById(R.id.wed_10);
		day[9][3] = (TextView)findViewById(R.id.thu_10);
		day[9][4] = (TextView)findViewById(R.id.fri_10);
		day[9][5] = (TextView)findViewById(R.id.sat_10);
		day[9][6] = (TextView)findViewById(R.id.sun_10);
		
		day[10][0] = (TextView)findViewById(R.id.mon_11);
		day[10][1] = (TextView)findViewById(R.id.tue_11);
		day[10][2] = (TextView)findViewById(R.id.wed_11);
		day[10][3] = (TextView)findViewById(R.id.thu_11);
		day[10][4] = (TextView)findViewById(R.id.fri_11);
		day[10][5] = (TextView)findViewById(R.id.sat_11);
		day[10][6] = (TextView)findViewById(R.id.sun_11);
		
		day[11][0] = (TextView)findViewById(R.id.mon_12);
		day[11][1] = (TextView)findViewById(R.id.tue_12);
		day[11][2] = (TextView)findViewById(R.id.wed_12);
		day[11][3] = (TextView)findViewById(R.id.thu_12);
		day[11][4] = (TextView)findViewById(R.id.fri_12);
		day[11][5] = (TextView)findViewById(R.id.sat_12);
		day[11][6] = (TextView)findViewById(R.id.sun_12);
		
		day[12][0] = (TextView)findViewById(R.id.mon_13);
		day[12][1] = (TextView)findViewById(R.id.tue_13);
		day[12][2] = (TextView)findViewById(R.id.wed_13);
		day[12][3] = (TextView)findViewById(R.id.thu_13);
		day[12][4] = (TextView)findViewById(R.id.fri_13);
		day[12][5] = (TextView)findViewById(R.id.sat_13);
		day[12][6] = (TextView)findViewById(R.id.sun_13);
		
		day[13][0] = (TextView)findViewById(R.id.mon_14);
		day[13][1] = (TextView)findViewById(R.id.tue_14);
		day[13][2] = (TextView)findViewById(R.id.wed_14);
		day[13][3] = (TextView)findViewById(R.id.thu_14);
		day[13][4] = (TextView)findViewById(R.id.fri_14);
		day[13][5] = (TextView)findViewById(R.id.sat_14);
		day[13][6] = (TextView)findViewById(R.id.sun_14);
		
		day[14][0] = (TextView)findViewById(R.id.mon_15);
		day[14][1] = (TextView)findViewById(R.id.tue_15);
		day[14][2] = (TextView)findViewById(R.id.wed_15);
		day[14][3] = (TextView)findViewById(R.id.thu_15);
		day[14][4] = (TextView)findViewById(R.id.fri_15);
		day[14][5] = (TextView)findViewById(R.id.sat_15);
		day[14][6] = (TextView)findViewById(R.id.sun_15);
		
		day[15][0] = (TextView)findViewById(R.id.mon_16);
		day[15][1] = (TextView)findViewById(R.id.tue_16);
		day[15][2] = (TextView)findViewById(R.id.wed_16);
		day[15][3] = (TextView)findViewById(R.id.thu_16);
		day[15][4] = (TextView)findViewById(R.id.fri_16);
		day[15][5] = (TextView)findViewById(R.id.sat_16);
		day[15][6] = (TextView)findViewById(R.id.sun_16);
		
		day[16][0] = (TextView)findViewById(R.id.mon_17);
		day[16][1] = (TextView)findViewById(R.id.tue_17);
		day[16][2] = (TextView)findViewById(R.id.wed_17);
		day[16][3] = (TextView)findViewById(R.id.thu_17);
		day[16][4] = (TextView)findViewById(R.id.fri_17);
		day[16][5] = (TextView)findViewById(R.id.sat_17);
		day[16][6] = (TextView)findViewById(R.id.sun_17);
		
		day[17][0] = (TextView)findViewById(R.id.mon_18);
		day[17][1] = (TextView)findViewById(R.id.tue_18);
		day[17][2] = (TextView)findViewById(R.id.wed_18);
		day[17][3] = (TextView)findViewById(R.id.thu_18);
		day[17][4] = (TextView)findViewById(R.id.fri_18);
		day[17][5] = (TextView)findViewById(R.id.sat_18);
		day[17][6] = (TextView)findViewById(R.id.sun_18);
		
		day[18][0] = (TextView)findViewById(R.id.mon_19);
		day[18][1] = (TextView)findViewById(R.id.tue_19);
		day[18][2] = (TextView)findViewById(R.id.wed_19);
		day[18][3] = (TextView)findViewById(R.id.thu_19);
		day[18][4] = (TextView)findViewById(R.id.fri_19);
		day[18][5] = (TextView)findViewById(R.id.sat_19);
		day[18][6] = (TextView)findViewById(R.id.sun_19);
		
		day[19][0] = (TextView)findViewById(R.id.mon_20);
		day[19][1] = (TextView)findViewById(R.id.tue_20);
		day[19][2] = (TextView)findViewById(R.id.wed_20);
		day[19][3] = (TextView)findViewById(R.id.thu_20);
		day[19][4] = (TextView)findViewById(R.id.fri_20);
		day[19][5] = (TextView)findViewById(R.id.sat_20);
		day[19][6] = (TextView)findViewById(R.id.sun_20);

		// 투명도 선언 //
		what_alpha = what.getBackground();
		time_alpha[0] = time_1.getBackground();
		time_alpha[1] = time_2.getBackground();
		time_alpha[2] = time_3.getBackground();
		time_alpha[3] = time_4.getBackground();
		time_alpha[4] = time_5.getBackground();
		time_alpha[5] = time_6.getBackground();
		time_alpha[6] = time_7.getBackground();
		time_alpha[7] = time_8.getBackground();
		time_alpha[8] = time_9.getBackground();
		time_alpha[9] = time_10.getBackground();
		time_alpha[10] = time_11.getBackground();
		time_alpha[11] = time_12.getBackground();
		time_alpha[12] = time_13.getBackground();
		time_alpha[13] = time_14.getBackground();
		time_alpha[14] = time_15.getBackground();
		time_alpha[15] = time_16.getBackground();
		time_alpha[16] = time_17.getBackground();
		time_alpha[17] = time_18.getBackground();
		time_alpha[18] = time_19.getBackground();
		time_alpha[19] = time_20.getBackground();
		day_alpha[0] = mon.getBackground();
		day_alpha[1] = tue.getBackground();
		day_alpha[2] = wed.getBackground();
		day_alpha[3] = thu.getBackground();
		day_alpha[4] = fri.getBackground();
		day_alpha[5] = sat.getBackground();
		day_alpha[6] = sun.getBackground();
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 7; j++)
				contents_alpha[i][j] = day[i][j].getBackground();
	}

	//	텍스트 폰트 지정
//	private void setFont() {
//		// TODO Auto-generated method stub
//		alert_text.setTypeface(font);
//
//		mon.setTypeface(font);
//		tue.setTypeface(font);
//		wed.setTypeface(font);
//		thu.setTypeface(font);
//		fri.setTypeface(font);
//		sat.setTypeface(font);
//		sun.setTypeface(font);
//
//		time_1.setTypeface(font);
//		time_2.setTypeface(font);
//		time_3.setTypeface(font);
//		time_4.setTypeface(font);
//		time_5.setTypeface(font);
//		time_6.setTypeface(font);
//		time_7.setTypeface(font);
//		time_8.setTypeface(font);
//		time_9.setTypeface(font);
//		time_10.setTypeface(font);
//		time_11.setTypeface(font);
//		time_12.setTypeface(font);
//		time_13.setTypeface(font);
//		time_14.setTypeface(font);
//		time_15.setTypeface(font);
//		time_16.setTypeface(font);
//		time_17.setTypeface(font);
//		time_18.setTypeface(font);
//		time_19.setTypeface(font);
//		time_20.setTypeface(font);
//
//		for(int i = 0; i < 20; i++)
//			for(int j = 0; j < 7; j++)
//				day[i][j].setTypeface(font);
//	}

	//	화면에 보여질 줄 수 (교시)
	private void show(int a) {
		// TODO Auto-generated method stub
		while(a > 0){
			if(a == 1){
				row_1.setVisibility(0);
				time_1.setText(one);
			}
			else if(a == 2){
				row_2.setVisibility(0);
				time_2.setText(two);
			}
			else if(a == 3){
				row_3.setVisibility(0);
				time_3.setText(three);
			}
			else if(a == 4){
				row_4.setVisibility(0);
				time_4.setText(four);
			}
			else if(a == 5){
				row_5.setVisibility(0);
				time_5.setText(five);
			}
			else if(a == 6){
				row_6.setVisibility(0);
				time_6.setText(six);
			}
			else if(a == 7){
				row_7.setVisibility(0);
				time_7.setText(seven);
			}
			else if(a == 8){
				row_8.setVisibility(0);
				time_8.setText(eight);
			}
			else if(a == 9){
				row_9.setVisibility(0);
				time_9.setText(nine);
			}
			else if(a == 10){
				row_10.setVisibility(0);
				time_10.setText(ten);
			}
			else if(a == 11){
				row_11.setVisibility(0);
				time_11.setText(tenone);
			}
			else if(a == 12){
				row_12.setVisibility(0);
				time_12.setText(tentwo);
			}
			else if(a == 13){
				row_13.setVisibility(0);
				time_13.setText(tenthree);
			}
			else if(a == 14){
				row_14.setVisibility(0);
				time_14.setText(tenfour);
			}
			else if(a == 15){
				row_15.setVisibility(0);
				time_15.setText(tenfive);
			}
			else if(a == 16){
				row_16.setVisibility(0);
				time_16.setText(tensix);
			}
			else if(a == 17){
				row_17.setVisibility(0);
				time_17.setText(tenseven);
			}
			else if(a == 18){
				row_18.setVisibility(0);
				time_18.setText(teneight);
			}
			else if(a == 19){
				row_19.setVisibility(0);
				time_19.setText(tennine);
			}
			else if(a == 20){
				row_20.setVisibility(0);
				time_20.setText(twenty);
			}
			a--;
		}
	}

	//	각 요일마다 해당되는 contents의 배경화면 지정
	private void setColor() {
		// TODO Auto-generated method stub
		sql_db = db.getReadableDatabase();
		cursor = sql_db.rawQuery("select * from TimeTable", null);
		while(cursor.moveToNext()){
			int time = new Filter().Filter_time(cursor.getString(0));
			int day = new Filter().Filter_day(cursor.getString(2));
			if(day == 0)											// 월요일
				this.day[time][day].setBackgroundColor(Color.argb(100, 0xff, 0x46, 0x46));
			else if(day == 1)										// 화요일
				this.day[time][day].setBackgroundColor(Color.argb(100, 0x4b, 0xaf, 0x4b));
			else if(day == 2)										// 수요일
				this.day[time][day].setBackgroundColor(Color.argb(100, 0xff, 0xe1, 0x3c));
			else if(day == 3)										// 목요일
				this.day[time][day].setBackgroundColor(Color.argb(100, 0x9f, 0x81, 0x4f));
			else if(day == 4)										// 금요일
				this.day[time][day].setBackgroundColor(Color.argb(100, 0xdd, 0x78, 0xf6));
			else if(day == 5)										// 토요일
				this.day[time][day].setBackgroundColor(Color.argb(100, 0x46, 0xbe, 0xff));
			else if(day == 6)										// 일요일
				this.day[time][day].setBackgroundColor(Color.argb(100, 0x8e, 0x83, 0xa9));
		}
		cursor.close();
		db.close();
	}

//	//	각 TextView 투명도 지정
//	private void makealpha(){
//		// TODO Auto-generated method stub
//		what_alpha.setAlpha(100);
//		for(int i = 0; i < 20; i++)
//			time_alpha[i].setAlpha(100);
//		for(int i = 0; i < 7; i++)
//			day_alpha[i].setAlpha(100);
//		for(int i = 0; i < 20; i++)
//			for(int j = 0; j < 7; j++)
//				contents_alpha[i][j].setAlpha(100);
//	}

	//	이벤트 영역
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 7; j++){
				if(v == day[i][j]){
					intent = new Intent(TimeTable.this, TableSetting.class);

					if(i == 0){
						intent.putExtra("time_", "1교시");
						intent.putExtra("time", time_1.getText().toString());
					}
					else if(i == 1){
						intent.putExtra("time_", "2교시");
						intent.putExtra("time", time_2.getText().toString());
					}
					else if(i == 2){
						intent.putExtra("time_", "3교시");
						intent.putExtra("time", time_3.getText().toString());
					}
					else if(i == 3){
						intent.putExtra("time_", "4교시");
						intent.putExtra("time", time_4.getText().toString());
					}
					else if(i == 4){
						intent.putExtra("time_", "5교시");
						intent.putExtra("time", time_5.getText().toString());
					}
					else if(i == 5){
						intent.putExtra("time_", "6교시");
						intent.putExtra("time", time_6.getText().toString());
					}
					else if(i == 6){
						intent.putExtra("time_", "7교시");
						intent.putExtra("time", time_7.getText().toString());
					}
					else if(i == 7){
						intent.putExtra("time_", "8교시");
						intent.putExtra("time", time_8.getText().toString());
					}
					else if(i == 8){
						intent.putExtra("time_", "9교시");
						intent.putExtra("time", time_9.getText().toString());
					}
					else if(i == 9){
						intent.putExtra("time_", "10교시");
						intent.putExtra("time", time_10.getText().toString());
					}
					else if(i == 10){
						intent.putExtra("time_", "11교시");
						intent.putExtra("time", time_11.getText().toString());
					}
					else if(i == 11){
						intent.putExtra("time_", "12교시");
						intent.putExtra("time", time_12.getText().toString());
					}
					else if(i == 12){
						intent.putExtra("time_", "13교시");
						intent.putExtra("time", time_13.getText().toString());
					}
					else if(i == 13){
						intent.putExtra("time_", "14교시");
						intent.putExtra("time", time_14.getText().toString());
					}
					else if(i == 14){
						intent.putExtra("time_", "15교시");
						intent.putExtra("time", time_15.getText().toString());
					}
					else if(i == 15){
						intent.putExtra("time_", "16교시");
						intent.putExtra("time", time_16.getText().toString());
					}
					else if(i == 16){
						intent.putExtra("time_", "17교시");
						intent.putExtra("time", time_17.getText().toString());
					}
					else if(i == 17){
						intent.putExtra("time_", "18교시");
						intent.putExtra("time", time_18.getText().toString());
					}
					else if(i == 18){
						intent.putExtra("time_", "19교시");
						intent.putExtra("time", time_19.getText().toString());
					}
					else if(i == 19){
						intent.putExtra("time_", "20교시");
						intent.putExtra("time", time_20.getText().toString());
					}

					if(j == 0)
						intent.putExtra("day", "월요일");
					else if(j == 1)
						intent.putExtra("day", "화요일");
					else if(j == 2)
						intent.putExtra("day", "수요일");
					else if(j == 3)
						intent.putExtra("day", "목요일");
					else if(j == 4)
						intent.putExtra("day", "금요일");
					else if(j == 5)
						intent.putExtra("day", "토요일");
					else if(j == 6)
						intent.putExtra("day", "일요일");

					if(day[i][j].getText() != null)
						intent.putExtra("contents", day[i][j].getText().toString());
					startActivity(intent); 

				}
			}
	}
}
