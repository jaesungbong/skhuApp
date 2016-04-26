package com.example.skhuapp.intro;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter {

	Context mContext;
	ArrayList<SuperItemData> supItems = new ArrayList<SuperItemData>();
	public MyAdapter(Context context) {
		mContext = context;
	}

	public void put(SuperItemData supdata, SubItemData subdata) {
		SuperItemData item = null;
		for (SuperItemData id : supItems) {
			if (id.title.equals(supdata.title)) {
				item = id;
				break;
			}
		}
		if (item == null) {
			item = new SuperItemData();
			item.title = supdata.title;
			supItems.add(item);
		}
		item.items.add(subdata);
		notifyDataSetChanged();
	}

	@Override
	public int getGroupCount() {
		return supItems.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		SuperItemData item = supItems.get(groupPosition);
		return item.items.size();
	}

	@Override
	public String getGroup(int groupPosition) {
		SuperItemData item = supItems.get(groupPosition);
		return item.title;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		SuperItemData item = supItems.get(groupPosition);
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
		SuperItemView v;
		if (convertView == null) {
			v = new SuperItemView(mContext);
		} else {
			v = (SuperItemView) convertView;
		}
		v.setData(supItems.get(groupPosition));

		return v;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		SubItemView v;
		if (convertView == null) {
			v = new SubItemView(mContext);
		} else {
			v = (SubItemView) convertView;
		}
		v.setData(supItems.get(groupPosition).items.get(childPosition));
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
		return true;
	}

}
