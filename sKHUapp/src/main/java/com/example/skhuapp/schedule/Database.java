package com.example.skhuapp.schedule;
import java.io.*;
import android.content.*;
import android.database.sqlite.*;
import android.util.*;


public class Database extends SQLiteOpenHelper{  
	
	public Database(Context context) {
		super(context, "Timetable.db", null, 1);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
//		
//		db.execSQL("CREATE TABLE TimeTable (time_ varchar(50), time varchar(50), day varchar(10), contents varchar(100));");
//		db.execSQL("CREATE TABLE SaveTable (num int(10), name varchar(50), time_ varchar(50), time varchar(50), day varchar(10), contents varchar(100));");
//	
//	
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}