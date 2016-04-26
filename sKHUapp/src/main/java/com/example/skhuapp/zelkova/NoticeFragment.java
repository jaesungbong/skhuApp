package com.example.skhuapp.zelkova;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.skhuapp.R;
import com.example.skhuapp.zelkova.colleagenotice.CollegeNoticeFragment;
import com.example.skhuapp.zelkova.eventnotice.EventNoticeFragment;
import com.example.skhuapp.zelkova.generalnotice.GeneralNoticeFragment;
import com.example.skhuapp.zelkova.lessonnotice.LessonNoticeFragment;
import com.example.skhuapp.zelkova.scholarshipnotice.ScholarshipNoticeFragment;
import com.viewpagerindicator.UnderlinePageIndicator;

public class NoticeFragment extends Fragment {
	
	ViewPager pager;
	TabHost tabHost;
	TabsAdapter mAdapter;
	public static final String PARAM_CURRENT_TAB = "currentTab";
	
@Override
public View onCreateView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	View v = inflater.inflate(R.layout.notice_fragment, container, false);
	tabHost = (TabHost)v.findViewById(R.id.tabhost);
	pager = (ViewPager)v.findViewById(R.id.pager);
	tabHost.setup();
	UnderlinePageIndicator indicator = (UnderlinePageIndicator)v.findViewById(R.id.indicator);
	mAdapter = new TabsAdapter(getActivity(), getChildFragmentManager(), tabHost, pager,indicator);
	
	mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator("일반 공지"),GeneralNoticeFragment.class , null);
	mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator("학사 공지"),CollegeNoticeFragment.class , null);
	mAdapter.addTab(tabHost.newTabSpec("tab3").setIndicator("수업 공지"),LessonNoticeFragment.class , null);
	mAdapter.addTab(tabHost.newTabSpec("tab4").setIndicator("장학 공지"),ScholarshipNoticeFragment.class , null);
	mAdapter.addTab(tabHost.newTabSpec("tab5").setIndicator("행사 공지"),EventNoticeFragment.class , null);
	
	if (savedInstanceState != null) {
		mAdapter.onRestoreInstanceState(savedInstanceState);
		tabHost.setCurrentTabByTag(savedInstanceState.getString(PARAM_CURRENT_TAB));
	}
	return v;
}
}
