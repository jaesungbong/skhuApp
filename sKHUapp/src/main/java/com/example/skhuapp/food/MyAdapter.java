package com.example.skhuapp.food;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MyAdapter extends BaseExpandableListAdapter {
	
	Context mContext;
	ArrayList<SuperFoodData> superItems = new ArrayList<SuperFoodData>();
	public MyAdapter(Context context){
		mContext = context;
	}
	
	public void put(SuperFoodData superdata, SubFoodData subdata) {
		SuperFoodData item = null;
		for(SuperFoodData id : superItems){
			if(id.type.equals(superdata.type)){
				item = id;
				break;
			}
		}
		if(item==null){
			item = new SuperFoodData();
			item.type = superdata.type;
			superItems.add(item);
		}
		item.items.add(subdata);
		notifyDataSetChanged();
	}

	@Override
	public int getGroupCount() {
		return superItems.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		SuperFoodData item = superItems.get(groupPosition);
		return item.items.size();
	}

	@Override
	public String getGroup(int groupPosition) {
		SuperFoodData item = superItems.get(groupPosition);
		return item.type;
	}

	@Override
	public SubFoodData getChild(int groupPosition, int childPosition) {
		SuperFoodData item = superItems.get(groupPosition);
		return item.items.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return (((long) groupPosition) << 32 | ((long) childPosition));
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		SuperFoodView v;
		if(convertView ==null){
			v=new SuperFoodView(mContext);
		}else{
			v=(SuperFoodView)convertView;
		}
		v.setData(superItems.get(groupPosition));
		return v;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		SubFoodView v;
		if(convertView==null){
			v=new SubFoodView(mContext);
		}else{
			v=(SubFoodView)convertView;
		}
		v.setData(superItems.get(groupPosition).items.get(childPosition));
		return v;
	}
	@Override
	public int getGroupTypeCount() {
		return super.getGroupTypeCount();
	}

	@Override
	public int getGroupType(int groupPosition) {
		// TODO Auto-generated method stub
		return super.getGroupType(groupPosition);
	}

	@Override
	public int getChildTypeCount() {
		// TODO Auto-generated method stub
		return super.getChildTypeCount();
	}

	@Override
	public int getChildType(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return super.getChildType(groupPosition, childPosition);
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
