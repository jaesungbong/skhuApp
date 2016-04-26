package com.example.skhuapp.zelkova.generalnotice;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.skhuapp.R;
import com.example.skhuapp.zelkova.DetailContent;
import com.example.skhuapp.zelkova.PagerFragment;

public class GeneralNoticeFragment extends PagerFragment {

	private ArrayList<HashMap<String, String>> data;

	ListView list;
	private SimpleAdapter sa;
	private Parse hp;
	String page = "1";
	String bsid = "10007";

	private final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			listUpdate();
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.general_notice_layout, container,
				false);
		data = new ArrayList<HashMap<String, String>>();
		hp = new Parse(getActivity(), handler, data, page, bsid);

		sa = new SimpleAdapter(getActivity(), data,
				R.layout.general_notice_item_layout, new String[] { "title",
						"writer", "date" }, new int[] {
						R.id.general_notice_item_layout_title,
						R.id.general_notice_item_layout_author,
						R.id.general_notice_item_layout_date });

		list = (ListView) v.findViewById(R.id.general_notice_list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String topTitle = "일반공지";
				String writer = data.get(position).get("writer");
				String title = data.get(position).get("title");
				String date = data.get(position).get("date");
				String content = data.get(position).get("content");
				Intent i = new Intent(getActivity(), DetailContent.class);
				i.putExtra("topTilte", topTitle);
				i.putExtra("title", title);
				i.putExtra("writer", writer);
				i.putExtra("date", date);
				i.putExtra("content", content);
				startActivity(i);
			}
		});
		list.setAdapter(sa);
		hp.open();
		return v;
	}

	private void listUpdate() {
		sa.notifyDataSetChanged();
	}
}
