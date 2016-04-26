package com.example.skhuapp.zelkova.generalnotice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

public class Parse {
	private String url;
	private Context context;
	private Handler handler;
	private ProgressDialog progressDialog;
	private Source source;
	private ArrayList<HashMap<String, String>> data;
	private String page;
	private String bsid;

	public Parse(Context context, Handler handler,
			ArrayList<HashMap<String, String>> data, String page, String bsid) {
		this.context = context;
		this.handler = handler;
		this.data = data;
		this.page = page;
		this.bsid = bsid;
	}

	public void open() {
		// 일반게시판의 첫번째 페이지
		url = "http://www.skhu.ac.kr/board/boardlist.aspx?curpage=" + page
				+ "&bsid=" + bsid;
		// 처리하기
		try {
			process();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process() throws IOException {
		// 상태 Progress 띄우기 위해서 사용함!
		final Handler mHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				URL nURL;
				try {
					nURL = new URL(url);
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							progressDialog = ProgressDialog.show(context, "",
									"Data loding...");
						}
					});
					// 모든 데이터 초기화
					InputStream html = nURL.openStream();
					// 가져오는 HTML의 인코딩형식
					source = new Source(new InputStreamReader(html, "EUC-KR"));
					// 테이블가져오기
					Element table = (Element) source.getAllElements(
							HTMLElementName.TABLE).get(0);
					// System.out.println("테이블 개수!!!!"
					// + source.getAllElements(HTMLElementName.TABLE)
					// .size());
					// 테이블 안의 TR 개수
					int tr_count = table.getAllElements(HTMLElementName.TR)
							.size();
					Element tr = null;
					HashMap<String, String> hm = null;
					for (int i = 1; i < tr_count; i++) {
						tr = (Element) table.getAllElements(HTMLElementName.TR)
								.get(i);
						hm = new HashMap<String, String>();
						Element titleTr = (Element) tr.getAllElements(HTMLElementName.TD).get(1);
						String link = "http://www.skhu.ac.kr/board/"+ titleTr.getAllElements(HTMLElementName.A).get(0).getAttributeValue("href").toString();
						hm.put("content", getURLtocontent(link));
						TextExtractor tilteTextExtractor = titleTr.getTextExtractor();
						hm.put("title", tilteTextExtractor.toString());
						hm.put("file",((Element) tr.getAllElements(HTMLElementName.TD).get(2)).getContent().toString());
						hm.put("writer",((Element) tr.getAllElements(HTMLElementName.TD).get(3)).getContent().toString());
						hm.put("date",((Element) tr.getAllElements(HTMLElementName.TD).get(4)).getContent().toString());
						hm.put("views",((Element) tr.getAllElements(HTMLElementName.TD).get(4)).getContent().toString());
						data.add(hm);
					}
					mHandler.post(new Runnable() {
						public void run() {
							progressDialog.cancel();
							// 업데이트 완료를 핸들러로 보내줌
							handler.sendEmptyMessage(0);
						}
					});
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}.start();
	}

	public static String getURLtocontent(String strURL) {
		Source source = null;
		String text = new String();
		try {
			URL url = new URL(strURL);
			source = new Source(url);

			Element list_td = source.getAllElements(HTMLElementName.TABLE).get(0);
			ArrayList<Element> list_td_sub = new ArrayList<Element>();
			list_td_sub = (ArrayList<Element>) list_td
					.getAllElements(HTMLElementName.TD);
			Element element[] = new Element[list_td_sub.size()];
			String class_id = new String();

			for (int i = 0; i < list_td_sub.size(); i++) {
				element[i] = list_td_sub.get(i);
				class_id = element[i].getAttributeValue("class");
				if (class_id != null) {
					if (class_id.equals("board_view_con")) {
						TextExtractor textExtractor = element[i]
								.getTextExtractor(); // here
						text = textExtractor.toString();
					}
				}
			}
			return text;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
