package com.example.skhuapp.zelkova.scholarshipnotice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skhuapp.R;
import com.example.skhuapp.zelkova.PagerFragment;

public class ScholarshipNoticeFragment extends PagerFragment{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v= inflater.inflate(R.layout.scholarship_notice_layout, container, false);
		return v;
	}
	

}
