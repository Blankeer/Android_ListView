package com.example.new_list_check;

import java.util.List;

import android.content.Context;
import android.provider.Browser.BookmarkColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListCheckAdapter extends BaseAdapter {
	private List<String> lists;// list item条目
	private static List<Boolean> checkFlags;// checkbox 选中状态
	private Context context;

	public ListCheckAdapter(List<String> lists, List<Boolean> checkFlags,
			Context context) {
		this.lists = lists;
		this.checkFlags = checkFlags;
		this.context = context;
	}

	public static void setCheckFlags(List<Boolean> checkFlag) {
		checkFlags = checkFlag;
	}

	public int getCount() {
		return lists.size();
	}

	public Object getItem(int position) {
		return lists.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vb = null;
		if (convertView == null) {
			vb = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item, null);
			vb.tv = (TextView) convertView.findViewById(R.id.list_item_text);
			vb.cb = (CheckBox) convertView
					.findViewById(R.id.list_item_checkbox);
			convertView.setTag(vb);
		} else {
			vb = (ViewHolder) convertView.getTag();
		}
		vb.tv.setText(lists.get(position));
		vb.cb.setChecked(checkFlags.get(position));
		vb.cb.setClickable(checkFlags.get(position));
		return convertView;
	}

}
