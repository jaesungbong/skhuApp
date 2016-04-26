package com.example.skhuapp.community.free;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.skhuapp.R;
import com.example.skhuapp.manager.DetailServerFreeBoardData;
import com.example.skhuapp.manager.NetworkManager;
import com.example.skhuapp.manager.NetworkManager.OnResultListener;
import com.example.skhuapp.manager.ServerFreeBoardData;

public class FreeBoardActivity extends Activity {

	ListView list;
	FreeBoardAdapter mAdapter;
	Button btn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_freeboard);

		list = (ListView) findViewById(R.id.activity_freeboard_list);
		mAdapter = new FreeBoardAdapter(this);
		mAdapter.setOnAdapterCommentClickListener(new FreeBoardAdapter.OnAdapterCommentClickListener() {

			@Override
			public void onCommentClick(View v, DetailServerFreeBoardData data) {
				String story_id = data.story_id;
				Intent i = new Intent(FreeBoardActivity.this,
						FreeBoardCommentActivity.class);
				i.putExtra("story_id", story_id);
				startActivity(i);
			}
		});
		list.setAdapter(mAdapter);
		initData();
		btn = (Button) findViewById(R.id.activity_freeboard_write);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						FreeBoardWriteActivity.class);
				startActivity(i);
			}
		});

		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	private void initData() {
		NetworkManager.getInstnace().getFreeboardList(getApplicationContext(),
				new OnResultListener<ServerFreeBoardData>() {

					@Override
					public void onSuccess(ServerFreeBoardData result) {
						mAdapter.clear();
						mAdapter.addAll(result.result);
					}

					// @Override
					// public void onSuccess(ServerFreeBoardData result) {
					// mAdapter.clear();
					// for (int i = 0; i < result.result.size(); i++) {
					// DetailServerFreeBoardData mData = new
					// DetailServerFreeBoardData();
					// mData.name = result.result.get(i).name;
					// mData.story_id = result.result.get(i).story_id;
					// mData.content = result.result.get(i).content;
					// mData.enroll_time = result.result.get(i).enroll_time;
					// NetworkManager.getInstnace().getCommentList(
					// getApplicationContext(),
					// result.result.get(i).story_id,
					// new OnResultListener<GetCommentResult>() {
					//
					// @Override
					// public void onSuccess(
					// GetCommentResult result) {
					// com = "" + result.result.size();
					// }
					//
					// @Override
					// public void onFail(int code) {
					// }
					// });
					// mData.commentSize = com;
					// mAdapter.add(mData);
					// }
					// }

					@Override
					public void onFail(int code) {
						Toast.makeText(getApplicationContext(), "게시판 불러오기 실패",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	@Override
	protected void onResume() {
		super.onResume();
		NetworkManager.getInstnace().getFreeboardList(getApplicationContext(),
				new OnResultListener<ServerFreeBoardData>() {
					@Override
					public void onSuccess(ServerFreeBoardData result) {
						mAdapter.clear();
						mAdapter.addAll(result.result);
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(getApplicationContext(), "콘텐츠 불러오기 실패",
								Toast.LENGTH_SHORT).show();
					}
				});
	}
}
