package com.example.skhuapp.collegeschedule;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class ParseCalender {
	private String url;
	private Context context;
	private Handler handler;
	private ProgressDialog progressDialog;
	private Source source;
	private Calendar calender;
	private ArrayList<String> parsedDay;
	private ArrayList<String> period;
	private ArrayList<String> content;

	public ParseCalender(Context context, Handler handler, Calendar calender,
			ArrayList<String> day,ArrayList<String> period, ArrayList<String> content) {
		this.context = context;
		this.handler = handler;
		this.calender = calender;
		this.parsedDay = day;
		this.period = period;
		this.content = content;
	}

	public void parsing() {
		parsedDay.clear();
		period.clear();
		content.clear();
		int year = calender.get(Calendar.YEAR);
//		System.out.println(year);
		int month = calender.get(Calendar.MONTH)+1;
//		System.out.println(month);

		url = "http://www.skhu.ac.kr/calendar/calendar_list_1.aspx?strYear="
				+ year + "&strMonth=" + month;
		System.out.println(url);

		try {
			process();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process() throws IOException {
		final Handler mHandler = new Handler();
		new Thread() {
			public void run() {
				URL nURL;
				try {
					nURL = new URL(url);
					mHandler.post(new Runnable() {

						@Override
						public void run() {
							progressDialog = progressDialog.show(context, "",
									"Data loding...");
						}
					});
					
					InputStream html = nURL.openStream();
					
					source = new Source(new InputStreamReader(html, "EUC-KR"));
					
					Element table  = (Element)source.getAllElements(HTMLElementName.TABLE).get(0);
					
					int td_count  = table.getAllElements(HTMLElementName.TD).size();
					
					Element td = null;
					for(int i=0;i<td_count;i++){
						td = (Element)table.getAllElements(HTMLElementName.TD).get(i);
						String class_id = (String)td.getAttributeValue("class");
						if(class_id!=null){
							if(class_id.equals("on")){
								TextExtractor textExtractor = td
										.getTextExtractor(); 
								parsedDay.add(textExtractor.toString());
//								System.out.println(textExtractor.toString());
							}
						}
					} //특별한 날짜 파싱
					
					Element tbody = (Element)source.getAllElements(HTMLElementName.TBODY).get(0);
					
					int tr_count = tbody.getAllElements(HTMLElementName.TR).size();
					
					Element tr = null;
					
					for(int i=1;i<tr_count;i++){
						tr = (Element)tbody.getAllElements(HTMLElementName.TR).get(i);
						TextExtractor periodText = tr.getAllElements(HTMLElementName.TD).get(0).getTextExtractor();
						System.out.println(periodText.toString());
						period.add(periodText.toString());
						TextExtractor contentText = tr.getAllElements(HTMLElementName.TD).get(1).getTextExtractor();
						System.out.println(contentText.toString());
						content.add(contentText.toString());
					} //특정한 기간과 내용 파싱
					
					mHandler.post(new Runnable() {
						public void run() {
							progressDialog.cancel();
							handler.sendEmptyMessage(0);
						}
					});
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

}
