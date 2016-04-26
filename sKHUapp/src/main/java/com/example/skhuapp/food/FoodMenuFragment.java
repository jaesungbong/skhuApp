package com.example.skhuapp.food;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import com.example.skhuapp.R;

public class FoodMenuFragment extends Fragment {
	
	ExpandableListView listView;
	MyAdapter mAdapter;
	SuperFoodData chicken,pizza,chinafood,meat,meal;
	SubFoodData subdata;
	String SubChickenFood[]={"썬더치킨","호식이 두마리 치킨"};
	int SubChickenFoodCode[] = {3,4};
	String SubPizzaFood[]={"59피자"};
	int SubPizzaFoodCode[] = {5};
	String SubChinaFood[]={"중국집1","중국집2","중국집3"};
	int SubChinaFoodCode[] = {6,7,8};
	String SubMeatFood[]={"돈돈","삼지창"};
	int SubMeatFoodCode[] = {1,2};
	String SubMealFood[]={"서울식당"};
	int SubMealFoodCode[] = {9};
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.food_fragment_menu, container, false);
		
		listView = (ExpandableListView)view.findViewById(R.id.food_fragment_menu_expandablelist);
		
		mAdapter = new MyAdapter(getActivity());
		
		listView.setAdapter(mAdapter);
		
		listView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				SubFoodData data=mAdapter.getChild(groupPosition, childPosition);
				int code = data.foodCode;
				FoodManager.getInstance().setFoodCode(code);
				((FoodActivity)getActivity()).switchFragment();
				return false;
			}
		});
		
		initData();

		return view;


//		for (int i = 0; i < mAdapter.getGroupCount(); i++) {
//			listView.expandGroup(i);
//		}
//		listView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
//			
//			@Override
//			public void onGroupCollapse(int groupPosition) {
//				listView.expandGroup(groupPosition);
//			}
//		});
	}
	
	private void initData(){
		chicken=new SuperFoodData();
		chicken.type="치킨";
		chicken.typeimgId=R.drawable.ic_launcher;
		pizza = new SuperFoodData();
		pizza.type="피자";
		pizza.typeimgId=R.drawable.ic_launcher;
		chinafood= new SuperFoodData();
		chinafood.type="중국집";
		chinafood.typeimgId=R.drawable.ic_launcher;
		meat = new SuperFoodData();
		meat.type="고기집";
		meat.typeimgId=R.drawable.ic_launcher;
		meal = new SuperFoodData();
		meal.type="식사";
		meal.typeimgId=R.drawable.ic_launcher;
		
		for(int i = 0 ;i<SubChickenFood.length;i++){
			subdata=new SubFoodData();
			subdata.title=SubChickenFood[i];
			subdata.foodCode=SubChickenFoodCode[i];
			mAdapter.put(chicken, subdata);
		}
		
		for(int i = 0 ;i<SubPizzaFood.length;i++){
			subdata=new SubFoodData();
			subdata.title=SubPizzaFood[i];
			subdata.foodCode=SubPizzaFoodCode[i];
			mAdapter.put(chicken, subdata);
		}
		
		for(int i = 0 ;i<SubChinaFood.length;i++){
			subdata=new SubFoodData();
			subdata.title=SubChinaFood[i];
			subdata.foodCode=SubChinaFoodCode[i];
			mAdapter.put(pizza, subdata);
		}
		
		for(int i = 0 ;i<SubMeatFood.length;i++){
			subdata=new SubFoodData();
			subdata.title=SubMeatFood[i];
			subdata.foodCode=SubMeatFoodCode[i];
			mAdapter.put(meat, subdata);
		}
		
		for(int i = 0 ;i<SubMealFood.length;i++){
			subdata=new SubFoodData();
			subdata.title=SubMealFood[i];
			subdata.foodCode=SubMealFoodCode[i];
			mAdapter.put(meal, subdata);
		}
	}

}
