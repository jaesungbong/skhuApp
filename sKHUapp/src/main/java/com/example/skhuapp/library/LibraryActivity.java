package com.example.skhuapp.library;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.skhuapp.R;

public class LibraryActivity extends Activity {

	ImageView searchIcon;
	LinearLayout before;
	LinearLayout after;
	private static final String address1 = "http://library.skhu.ac.kr/DLiWeb20/components/searchir/result.aspx?qy_idx=TITL&qy_opt=AND&qy_kwd=";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library);

		final EditText keyword = (EditText) findViewById(R.id.keyword);
		searchIcon = (ImageView) findViewById(R.id.search_btn);
		before = (LinearLayout) findViewById(R.id.before_search);
		after = (LinearLayout) findViewById(R.id.after_search);
		final WebView webView = (WebView) findViewById(R.id.web);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setSupportZoom(true);

		searchIcon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String key = keyword.getText().toString();
				if (key.equals(null) || key.equals("")) {
					Toast.makeText(getApplicationContext(), "검색어를 입력해 주세요",
							Toast.LENGTH_SHORT).show();
				} else {
					webView.loadUrl(address1 + key);
					after.setVisibility(View.VISIBLE);
					before.setVisibility(View.GONE);
				}

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
