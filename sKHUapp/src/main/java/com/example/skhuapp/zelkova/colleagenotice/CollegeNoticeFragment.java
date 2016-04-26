package com.example.skhuapp.zelkova.colleagenotice;

import com.example.skhuapp.R;
import com.example.skhuapp.zelkova.PagerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CollegeNoticeFragment extends PagerFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v= inflater.inflate(R.layout.colleage_notice_layout, container, false);
		return v;
	}

}
