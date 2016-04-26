package com.example.skhuapp.schedule;

public class Filter {

	public int Filter_time(String time){
		if(time.equals("1교시"))
			return 0;
		else if(time.equals("2교시"))
			return 1;
		else if(time.equals("3교시"))
			return 2;
		else if(time.equals("4교시"))
			return 3;
		else if(time.equals("5교시"))
			return 4;
		else if(time.equals("6교시"))
			return 5;
		else if(time.equals("7교시"))
			return 6;
		else if(time.equals("8교시"))
			return 7;
		else if(time.equals("9교시"))
			return 8;
		else if(time.equals("10교시"))
			return 9;
		else if(time.equals("11교시"))
			return 10;
		else if(time.equals("12교시"))
			return 11;
		else if(time.equals("13교시"))
			return 12;
		else if(time.equals("14교시"))
			return 13;
		
		else
			return -1;
	}
	//	받은 요일을 int값으로 넘겨주는 부분	(실패시 -1)
	public int Filter_day(String day){
		if(day.equals("월요일"))
			return 0;
		else if(day.equals("화요일"))
			return 1;
		else if(day.equals("수요일"))
			return 2;
		else if(day.equals("목요일"))
			return 3;
		else if(day.equals("금요일"))
			return 4;
		
		else
			return -1;
	}
}
