package com.example.skhuapp.community.free;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skhuapp.R;
import com.example.skhuapp.manager.NetworkManager;
import com.example.skhuapp.manager.NetworkManager.OnResultListener;
import com.example.skhuapp.manager.SendLetterResult;
import com.example.skhuapp.manager.ServerFreeBoardData;

public class FreeBoardWriteActivity extends Activity {

	EditText name, contents;
	Button btn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_freeboardwrite);
		name = (EditText) findViewById(R.id.activity_freeboardwrite_name);
		contents = (EditText) findViewById(R.id.activity_freeboardwrite_contents);
		btn = (Button) findViewById(R.id.activity_freeboardwrite_write_btn);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String strName = name.getText().toString();
				String strContent = contents.getText().toString();
				String type = "1";
				if (!strName.equals("") && !strContent.equals("")) {

					NetworkManager.getInstnace().postStory(
							getApplicationContext(), strName, strContent, type,
							new OnResultListener<SendLetterResult>() {

								@Override
								public void onSuccess(SendLetterResult result) {
									Toast.makeText(getApplicationContext(),
											"글 올리기 성공", Toast.LENGTH_SHORT)
											.show();
								}

								@Override
								public void onFail(int code) {
									Toast.makeText(getApplicationContext(),
											"글 올리기 실패", Toast.LENGTH_SHORT)
											.show();
								}
							});
					finish();
				}else if(strName.equals("")){
					Toast.makeText(getApplicationContext(), "이름을 입력 하세요", Toast.LENGTH_SHORT).show();
				}else if(strContent.equals("")){
					Toast.makeText(getApplicationContext(), "내용을 입력 하세요", Toast.LENGTH_SHORT).show();
				}
			}

		});
		btn = (Button) findViewById(R.id.activity_freeboardwrite_write_cancel_btn);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

}
