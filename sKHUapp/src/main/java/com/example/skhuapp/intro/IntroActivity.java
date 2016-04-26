package com.example.skhuapp.intro;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.example.skhuapp.R;

public class IntroActivity extends Activity {

	ExpandableListView listView;
	MyAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		listView = (ExpandableListView) findViewById(R.id.intro_expandablelistview);
		mAdapter = new MyAdapter(this);
		listView.setAdapter(mAdapter);
		initData();
		for (int i = 0; i < mAdapter.getGroupCount(); i++) {
			listView.collapseGroup(i);
		}
		// listView.setOnGroupExpandListener(new OnGroupExpandListener() {
		//
		// @Override
		// public void onGroupExpand(int groupPosition) {
		// }
		// });
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_default);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	private String[] introIndex = { "교육 목표", "대학 상징", "인사말" };
	private int[] img = { R.drawable.poongkyung, R.drawable.logo3,
			R.drawable.chongjang };
	private String[] title = { "성공회대의 교육이념은 '열림, 나눔, 섬김' 입니다.",
			"LUX MUNDI\n세상의 빛이라는 요한복음 8:12의 라틴어이다.",
			"누구나 오고싶고, 배우고싶고, 일하고 싶고, 머무르고 싶은 대학교" };
	private String[] content = {
			"성공회대의 교육이념은 '열림,나눔,섬김'입니다.\n\n교육 이념\n\n열림이란 나와 나 자신이 속한 단체가 가족주의의 이기심을 버리고\n이웃과 사회와 소통함으로써 평화를 이루는 것입니다.\n나눔이란 내가 소유하고 있는 물질만이 아니라 재능까지 포함하여\n모든 것을 이웃과 공유하는 봉사의 실천을 통해 공의로운사회를 형성하는 것입니다.\n섬김이란 하느님을 섬기고 내 이웃을 사랑으로 섬김으로써 서로 존중하는 사회를 이룩하는 것입니다.\n\n교육목표\n\n성공회대학교의 교육이념인 열림·나눔·섬김의 구현은 자발적이며\n창의적인 교육방법을 통해 가능합니다. 이 정신은 성공회대학교가\n지향하는 진보적이며 비판적인 학풍의 초석입니다.\n- 열림·나눔·섬김을 실천하는‘기독교 정신의 전인적인 인간’육성\n- 열린 마음을 지닌‘보편적 인간’육성\n- 나눔을 실천하는‘공공의 인간’육성\n- 섬김을 앞세우는‘봉사하는 인간’육성\n\n",
			"LUX MUNDI글자 주위의 모양은 불꽃을 상징하는 것으로\n그리스도의 빛이 온누리에 퍼져나감을 상징한다. \n\nLUX MUNDI밑의 수평으로 그어진 그림은\n온누리, 세상을 의미한다.\n\n저울 형태의 그림은 성공회 대학교의 호수 성인인 성미카엘의 \n첫글자 'M'과 '천평저울'을 추 형태로 한것이다.\n\n ",
			"인권과 평화의 대학 성공회대학교 입니다.\n\n성공회대학교는 1914년 대한성공회 한인성직자를 양성하기 위해 \n영국인선교사가 설립한 ‘성 미카엘 신학원’을 모태로 성장한 대학교입니다. \n그 당시 교육은 교수와 학생이 함께 기숙하는 영국의 도제교육제도였습니다.\n이러한 교육은 1982년 3월 학부 학사과정이 설립될 때 까지 무려 70여년 지속되었습니다.\n오늘 날까지 그 어느 대학보다도 교수와 학생사이에 돈독한 개별지도 \n방식이 지속되고 있는 것은 성공회 대학교만의 교육전통 때문입니다.\n\n성공회대학교의 설립목적과 교육목표의 토대는 \n성공회의 포용성을 반영한 것으로써\n'열림, 나눔. 섬김'의 정신입니다. \n인권과 평화를 실천하는 성공회대학교의 인문사회 과학의 \n진보적 학풍과 명성은 지속 발전해 갈 것입니다.\n 동시에 성공회대학교는 사회적 책임을 다하는 전인적인 지성인을 \n육성하기위해 ‘사회적 영성’을 더하는 교육을 지향하고 있습니다.\n\n과거와 다르게 모든 국내대학들의 상황은 점점 어려워지고 있습니다. \n그러나 성공회대학교는 학생과 교수, 직원 모두가 ‘처음처럼’ \n창학의 정신에 충직함으로써 사회와 국가의 발전에 기여할 \n인재육성에 온 힘을 다할 것입니다. \n성공회 대학교는 ‘누구나 오고 싶고, 배우고 싶고, 일하고 싶고, 머무르고 싶은 대학교’로 \n성장할 것입니다.\n\n여러분 성공회대학교로 오십시오.\n주님의 도우심은 변함이 없습니다.\n고맙습니다." };

	private void initData() {
		for (int i = 0; i < introIndex.length; i++) {
			for (int j = 0; j < 1; j++) {
				SuperItemData superData = new SuperItemData();
				SubItemData subData = new SubItemData();
				superData.title = introIndex[i];
				subData.imageId = img[i];
				subData.title = title[i];
				subData.content = content[i];
				mAdapter.put(superData, subData);
			}
		}
	}
}
