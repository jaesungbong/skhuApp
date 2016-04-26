package com.example.skhuapp.community.free;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skhuapp.R;
import com.example.skhuapp.manager.GetCommentResult;
import com.example.skhuapp.manager.NetworkManager;
import com.example.skhuapp.manager.NetworkManager.OnResultListener;
import com.example.skhuapp.manager.SendCommentResult;

public class FreeBoardCommentActivity extends Activity {

	ImageView back, smile;
	ListView commentList;
	TextView totalComment;
	EditText write;
	Button btn;
	FreeBoardCommentAdapter mAdapter;
	String story_id;
	int total;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_freeboardcomment);

		Intent i = getIntent();
		story_id = i.getStringExtra("story_id");

		back = (ImageView) findViewById(R.id.activity_freeboardcomment_backarrow);
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		totalComment = (TextView) findViewById(R.id.activity_freeboardcomment_total_comment);
		
		commentList = (ListView) findViewById(R.id.activity_freeboardcomment_list);
		smile = (ImageView) findViewById(R.id.smile);
		smile.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "hi",
						Toast.LENGTH_SHORT).show();
			}
		});

		write = (EditText) findViewById(R.id.activity_freeboardcomment_write);
		btn = (Button) findViewById(R.id.activity_freeboardcomment_write_btn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String strWrite = write.getText().toString();
				if(strWrite.equals("")||strWrite.equals(null)){
					Toast.makeText(getApplicationContext(), "덧글을 입력해 주세요", Toast.LENGTH_SHORT).show();
				}else{
					NetworkManager.getInstnace().postComment(getApplicationContext(), "", strWrite, story_id, new OnResultListener<SendCommentResult>() {

						@Override
						public void onSuccess(SendCommentResult result) {
							Toast.makeText(getApplicationContext(),"덧글 올리기 성공", Toast.LENGTH_SHORT).show();
							write.setText("");
							
							NetworkManager.getInstnace().getCommentList(getApplicationContext(),
									story_id, new OnResultListener<GetCommentResult>() {

										@Override
										public void onSuccess(GetCommentResult result) {
											mAdapter.clear();
											mAdapter.addAll(result.result);
											total = mAdapter.getCount();
											totalComment.setText("덧글 "+total);
										}

										@Override
										public void onFail(int code) {
											Toast.makeText(getApplicationContext(), "덧글 불러오기 실패",
													Toast.LENGTH_SHORT).show();
										}
									});
						}

						@Override
						public void onFail(int code) {
							Toast.makeText(getApplicationContext(),"덧글 올리기 실패", Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});

		mAdapter = new FreeBoardCommentAdapter(getApplicationContext());
		commentList.setAdapter(mAdapter);
		initData();

		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	private void initData() {
		NetworkManager.getInstnace().getCommentList(getApplicationContext(),
				story_id, new OnResultListener<GetCommentResult>() {

					@Override
					public void onSuccess(GetCommentResult result) {
						mAdapter.clear();
						mAdapter.addAll(result.result);
						total = mAdapter.getCount();
						totalComment.setText("덧글 "+total);
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(getApplicationContext(), "덧글 불러오기 실패",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	@Override
	protected void onResume() {
		super.onResume();
		NetworkManager.getInstnace().getCommentList(getApplicationContext(),
				story_id, new OnResultListener<GetCommentResult>() {

					@Override
					public void onSuccess(GetCommentResult result) {
						mAdapter.clear();
						mAdapter.addAll(result.result);
						total = mAdapter.getCount();
						totalComment.setText("덧글 "+total);
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(getApplicationContext(), "덧글 불러오기 실패",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

}
