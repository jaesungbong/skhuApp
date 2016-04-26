package com.example.skhuapp.zelkova;

import com.example.skhuapp.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {
	
	Button btn;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.zelkova_fragment_menu, container,
				false);
		btn = (Button)v.findViewById(R.id.zelkova_fragment_menu_btn1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((NoticeActivity)getActivity()).switchOneFragment();
			}
		});
		return v;
	}

}
