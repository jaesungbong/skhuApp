package com.example.skhuapp.menu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

public class parseMenu {
	private String url;
	private Context context;
	private Handler handler;
	private ProgressDialog progressDialog;
	private Source source;
	WeekMenu menu;
	
	public parseMenu(Context context, Handler handler,	WeekMenu menu){
		this.context =context;
		this.handler = handler;
		this.menu = menu;
	}
	
	public void parsing(){
		url = "http://www.skhu.ac.kr/uni_zelkova/uni_zelkova_4_3_view.aspx?idx=193";
		
		try{
			process();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void process() throws IOException{
		final Handler mHandler = new Handler();
		new Thread(){
			public void run() {
				URL nURL;
				try{
					nURL = new URL(url);
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							progressDialog = ProgressDialog.show(context, "", "Data loding...");
						}
					});
					
					InputStream html = nURL.openStream();
					//가져오는 HTML의 인코딩형식
					source = new Source(new InputStreamReader(html, "EUC-KR"));
					
					Element table = (Element) source.getAllElements(HTMLElementName.TABLE).get(1);
					
					int tr_count = table.getAllElements(HTMLElementName.TR).size();
					
					Element tr[] = new Element[10]; //0은 요일, 1은 날짜, 2는 중식, 3은 중식 열량, 4는 특선, 5는 특선열량, 8은 석식, 9는 석식열량
					
					for(int i = 0; i<tr_count;i++){
						tr[i] = (Element) table.getAllElements(HTMLElementName.TR).get(i);
					}
					ArrayList<String> dayOfweek = new ArrayList<String>();
					
					for(int i = 1;i<=5;i++){
						menu.dayOfweek.add(tr[0].getAllElements(HTMLElementName.TH).get(i).getContent().toString());
					} //요일
					
					for(int i = 2;i<=6;i++){
						menu.weekDate.add(tr[1].getAllElements(HTMLElementName.TH).get(i).getContent().toString());
					} //날짜
					
					for(int i = 0;i<5;i++){
						String lunch = tr[2].getAllElements(HTMLElementName.TD).get(i).getContent().toString();
						lunch = lunch.replace("<br />","\n");
						menu.weekLunch.add(lunch);
					} //중식
					
					for(int i=0;i<5;i++){
						menu.lunchKcal.add(tr[3].getAllElements(HTMLElementName.TD).get(i).getContent().toString());
					} //중식 열량
					
					for(int i = 0;i<5;i++){
						String special = tr[4].getAllElements(HTMLElementName.TD).get(i).getContent().toString();
						special = special.replace("<br />", "\n");
						menu.weekSpecial.add(special);
					}//특선
					
					for(int i = 0;i<5;i++){
						menu.specialKcal.add(tr[5].getAllElements(HTMLElementName.TD).get(i).getContent().toString());
					}//특선열량
					
					for(int i = 0;i<5;i++){
						String dinner = tr[8].getAllElements(HTMLElementName.TD).get(i).getContent().toString();
						dinner = dinner.replace("<br />", "\n");
						menu.weekDinner.add(dinner);
					}//석식
					
					for(int i = 0;i<5;i++){
						menu.dinnerKcal.add(tr[9].getAllElements(HTMLElementName.TD).get(i).getContent().toString());
					}//석식열량
					
					mHandler.post(new Runnable()
					{
						public void run()
						{
							progressDialog.cancel();
							//업데이트 완료를 핸들러로 보내줌
							handler.sendEmptyMessage(0);
						}
					});
				}catch(MalformedURLException e){
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
