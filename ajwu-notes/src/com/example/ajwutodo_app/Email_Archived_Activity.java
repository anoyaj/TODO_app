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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Email_Archived_Activity extends Activity {

	// Gathering the data for the archived list (Archived_list)
	Collection<ToDoItem> archived_tasks = TaskController.getArchived_Lst().getTodoItems();
	ArrayList<ToDoItem> archived_list = new ArrayList<ToDoItem>(archived_tasks);

	// This list will be used for the View
	private ArrayList<ToDoItem> list = new ArrayList<ToDoItem>();

	// Initiating checkboxAdapter
	UnselectableAdapter unselectableAdapter;

	String email = "";
	String name = "";
	String checked = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		list.addAll(archived_list);

		setContentView(R.layout.activity_email);
		unselectableAdapter = new UnselectableAdapter(this, list);
		ListView listView = (ListView) findViewById(R.id.todoList);
		listView.setAdapter(unselectableAdapter);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView,
					View view, int position, long id) {

				String task;
				name = list.get(position).toString();
				Toast.makeText(Email_Archived_Activity.this, "Added " + "\""+ name +"\"",Toast.LENGTH_SHORT).show();
				if (list.get(position).checkbox) {
					checked = "Done";
				} else {
					checked = "Not Done";
				}
				list.remove(position);
				unselectableAdapter.notifyDataSetChanged();
				task = name + " : " + checked + "\n";
				email = email + task;
				return true;
			}
		});
	}

	// Email intent referenced from: 
	// http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
	public void sendAEmail(View v) {
		Toast.makeText(Email_Archived_Activity.this,"Adding to email", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, new String[] { "recipient@example.com" });
		i.putExtra(Intent.EXTRA_SUBJECT, "To Do List");
		i.putExtra(Intent.EXTRA_TEXT, email);
		try {
			startActivity(Intent.createChooser(i, "Sending Email ..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(Email_Archived_Activity.this,
					"Error2: There are no Email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}

	// Settings
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selected, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.see_archived:
			Intent archived = new Intent(Email_Archived_Activity.this,
					Archived_Activity.class);
			startActivity(archived);
			finish();
			return true;

		case R.id.see_unarchived:
			Intent unarchived = new Intent(Email_Archived_Activity.this,
					Main_Activity.class);
			startActivity(unarchived);
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}