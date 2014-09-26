/*
 * Copyright (C) 2014 Anthony Wu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ajwutodo_app;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	Context ctx;
	LayoutInflater lInflater;
	ArrayList<ToDoItem> objects;

	ListAdapter(Context context, Collection<ToDoItem> tasks) {
		ctx = context;
		objects = (ArrayList<ToDoItem>) tasks;
		lInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return objects.size();
	}

	@Override
	public Object getItem(int position) {
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	//Unlike UnselectableAdapter, the cb (check box) is enabled in the archived and main activity. 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.checkbox_list_, parent, false);
		}
		ToDoItem item = getTask(position);
		((TextView) view.findViewById(R.id.taskName)).setText(item.name);
		CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox1);
		cb.setOnCheckedChangeListener(checkBoxListener);
		cb.setTag(position);
		cb.setChecked(item.checkbox);
		return view;
	}

	ToDoItem getTask(int position) {
		return ((ToDoItem) getItem(position));
	}

	// Listens and updates the check box attribute
	OnCheckedChangeListener checkBoxListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			getTask((Integer) buttonView.getTag()).checkbox = isChecked;
		}
	};

	// Counts number of checked items, unchecked items and total items in a given list (main or archived list)
	String getCheckNumbers(ArrayList<ToDoItem> list, String taskType) {
		int checked_numbers = 0;
		int unchecked_numbers = 0;
		for (int i = 0; i < list.size(); i++) {
			ToDoItem task = list.get(i);
			if (task.checkbox()) {
				checked_numbers += 1;
			}
		}
		unchecked_numbers = list.size() - checked_numbers;
		return taskType + "\n" + "Checked: " + checked_numbers + "\n"
				+ "Unchecked: " + unchecked_numbers + "\n" + "Total tasks: "
				+ list.size() + "\n\n";

	}

	// Gathers all ToDo items along with their status (Done or Not Done)
	// Returns the results in a string that is later used for email
	String getAllTasks(ArrayList<ToDoItem> list) {
		String tasks = "";
		String name = "";
		for (int i = 0; i < list.size(); i++) {
			ToDoItem task = list.get(i);
			name = task.getToDoName().toString();
			if (task.checkbox()) {
				tasks = tasks + name + ": " + "Done\n";
			} else {
				tasks = tasks + name + ": " + "Not Done\n";
			}
		}
		return tasks;
	}
}