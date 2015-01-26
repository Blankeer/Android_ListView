package com.example.new_list_check;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private List<String> lists = new ArrayList<String>();// list item条目
	private List<Boolean> checkFlags = new ArrayList<Boolean>();// checkbox 选中状态
	private ListView lv;
	private ListCheckAdapter lca;
	private TextView textStatusInfo;
	private Button buttonAllSelect, buttonUnSelect, buttonCancel;
	private int selectNum = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.main_listview);
		textStatusInfo = (TextView) findViewById(R.id.main_textview_status_info);
		buttonAllSelect = (Button) findViewById(R.id.main_button_allselect);
		buttonUnSelect = (Button) findViewById(R.id.main_button_unallselect);
		buttonCancel = (Button) findViewById(R.id.main_button_cancel);
		ButtonOnClick bo = new ButtonOnClick();
		buttonAllSelect.setOnClickListener(bo);
		buttonUnSelect.setOnClickListener(bo);
		buttonCancel.setOnClickListener(bo);
		listInit();
	}

	private void listInit() {
		for (int i = 0; i < 50; i++) {
			lists.add("test" + i);
			checkFlags.add(false);
		}
		lv.setOnItemClickListener(new ListOnItemClickListener());
		lca = new ListCheckAdapter(lists, checkFlags, this);
		lv.setAdapter(lca);
	}

	class ListOnItemClickListener implements OnItemClickListener {
		// list点击事件
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// Toast.makeText(MainActivity.this, position + "", 0).show();
			ViewHolder holder = (ViewHolder) view.getTag();
			holder.cb.toggle();// 状态变成和当前相反的状态
			boolean b = holder.cb.isChecked();
			checkFlags.set(position, b);
			ListCheckAdapter.setCheckFlags(checkFlags);
			lca.notifyDataSetChanged();
			if (b) {
				selectNum++;
			} else {
				selectNum--;
			}
			textChange();
		}
	}

	private void textChange() {
		this.textStatusInfo.setText("已选中" + selectNum + "项");
	}

	class ButtonOnClick implements OnClickListener {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.main_button_allselect:
				allSelect();
				break;
			case R.id.main_button_unallselect:
				unallselect();
				break;
			case R.id.main_button_cancel:
				cancel();
				break;
			default:
				break;
			}
			lca.notifyDataSetChanged();
		}

		private void allSelect() {// 全选
			for (int i = 0; i < checkFlags.size(); i++) {
				checkFlags.set(i, true);
			}
			selectNum = checkFlags.size();
			textChange();
		}

		private void unallselect() {// 反选
			int isTrueNum = 0;
			for (int i = 0; i < checkFlags.size(); i++) {
				boolean b = checkFlags.get(i);
				if (b == false) {
					isTrueNum++;
				}
				checkFlags.set(i, !b);
			}
			selectNum = isTrueNum;
			textChange();
		}

		private void cancel() {// 取消选中
			for (int i = 0; i < checkFlags.size(); i++) {
				checkFlags.set(i, false);
			}
			selectNum = 0;
			textChange();
		}
	}

}
