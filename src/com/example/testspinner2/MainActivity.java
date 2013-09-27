package com.example.testspinner2;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ArrayList<String> list;
	private View imgView;
	private TextView textView;
	private LinearLayout layout;
	private ListView listView;
	private MyspinnerAdapter adapter;
	private PopupWindow popupWindow;
	private LinearLayout spinnerlayout;
	int width;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.text);
		imgView = findViewById(R.id.arrowbut);
		// 实例化一个List,添加数据
		list = new ArrayList<String>();
		list.add("第一展厅");
		list.add("第二展厅");
		list.add("第三展厅");
		// 实例化一个适配器，list的数据作为Adapter的数据
		adapter = new MyspinnerAdapter(this, list);
		// 默认设置下拉框的标题为数据的第一个
		textView.setText((CharSequence) adapter.getItem(0));
		spinnerlayout = (LinearLayout) findViewById(R.id.spinnerid);
		// 点击右侧按钮，弹出下拉框
		imgView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showWindow(v);

			}
		});
	}

	@SuppressWarnings("deprecation")
	public void showWindow(View v) {
		// 找到布局文件
		layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.mypinner_dropdown, null);
		// 实例化listView
		listView = (ListView) layout.findViewById(R.id.listView);
		// 设置listView的适配器
		listView.setAdapter(adapter);
		// 实例化一个PopuWindow对象
		popupWindow = new PopupWindow(v);
		// 设置弹框的宽度为布局文件的宽
		popupWindow.setWidth(spinnerlayout.getWidth());
		// 高度随着内容变化
		popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置一个透明的背景，不然无法实现点击弹框外，弹框消失
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击弹框外部，弹框消失
		popupWindow.setOutsideTouchable(true);
		// 设置焦点
		popupWindow.setFocusable(true);
		// 设置所在布局
		popupWindow.setContentView(layout);
		// 设置弹框出现的位置，在v的正下方横轴偏移textview的宽度，为了对齐~纵轴不偏移
		popupWindow.showAsDropDown(v, -textView.getWidth(), 0);
		// listView的item点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				textView.setText(list.get(arg2));// 设置所选的item作为下拉框的标题
				// 弹框消失
				popupWindow.dismiss();
				popupWindow = null;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
