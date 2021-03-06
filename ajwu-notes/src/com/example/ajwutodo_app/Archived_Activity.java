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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Archived_Activity extends Activity {

	// Gathering both the data for the unArchived (Main_list) and archived list (Archived_list)
	Collection<ToDoItem> main_tasks = TaskController.getMain_Lst().getTodoItems();
	ArrayList<ToDoItem> main_list = new ArrayList<ToDoItem>(main_tasks);
	Collection<ToDoItem> archived_tasks = TaskController.getArchived_Lst().getTodoItems();
	ArrayList<ToDoItem> archived_list = new ArrayList<ToDoItem>(archived_tasks);
	String todoStats;

	// Initiating checkboxAdapter
	ListAdapter checkboxAdapter;

	// String variable for email body description and statistics
	String taskstoSend = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_archived_);
		checkboxAdapter = new ListAdapter(this, archived_tasks);
		ListView listView = (ListView) findViewById(R.id.todoList);
		listView.setAdapter(checkboxAdapter);

		// Updating both the archived list and the main list if anything changed
		TaskController.getArchived_Lst().addListener(new Listener() {
			@Override
			public void update() {
				archived_list.clear();
				Collection<ToDoItem> items = TaskController.getArchived_Lst()
						.getTodoItems();
				archived_list.addAll(items);
				checkboxAdapter.notifyDataSetChanged();
			}
		});

		TaskController.getMain_Lst().addListener(new Listener() {
			@Override
			public void update() {
				main_list.clear();
				Collection<ToDoItem> items = TaskController.getMain_Lst()
						.getTodoItems();
				main_list.addAll(items);
				checkboxAdapter.notifyDataSetChanged();
			}
		});

		/**
		 * ALERT DIALOG BOXES:
		 * 
		 * 1) Archive: Adds ToDoItem to Archived_list and removes it from the Main_lst
		 * 2) Email: fetch the ToDoItem.name and send it via email
		 * 3) Delete: Remove item from the Main_lst/Archived_lst
		 *
		 **/

		// prompts user to select from TODO options 
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int position, long list_position) {

				Log.d("NOTE ALERT", "Waiting for user_input for note action");
				AlertDialog.Builder alert = new AlertDialog.Builder(
						Archived_Activity.this);
				alert.setTitle("Task Action")
						.setMessage("What do you want to do?")
						.setCancelable(true)
						// 1. archive ToDoItem
						.setNegativeButton("UnArchive",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,int which) {
										ToDoItem item = archived_list.get(position);
										String taskInfo = item.getToDoName().toString();
										boolean checked = item.checkbox;
										TaskController.addMItem(new ToDoItem(taskInfo, checked));
										TaskController.getArchived_Lst().removeItem(item);
										Toast.makeText(Archived_Activity.this,"UnArchived!",Toast.LENGTH_SHORT).show();
										dialog.cancel();
									}
								})
						// 2. email ToDoItem
						.setNeutralButton("Email",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,int which) {
										ToDoItem item = archived_list.get(position);
										String taskInfo = item.getToDoName()
												.toString();
										boolean checked = item.checkbox;
										if (checked) {
											taskstoSend = "Done";
										} else {
											taskstoSend = "Not Done";
										}
										String taskDesc = taskInfo + " :"
												+ taskstoSend;
										sendEmail(taskDesc);
										dialog.cancel();
									}
								})
						// 3. delete ToDoItem
						.setPositiveButton("Delete",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Toast.makeText(Archived_Activity.this,"Deleted a task!",Toast.LENGTH_SHORT).show();
										ToDoItem item = archived_list.get(position);
										TaskController.getArchived_Lst().removeItem(item);
										dialog.cancel();
									}
								});
				alert.show();
			}
		});

	}
	
	// Email 
	public void email(View v) {
		AlertDialog.Builder alert = new AlertDialog.Builder(Archived_Activity.this);
		alert.setTitle("Task Action")
				.setMessage("I want to email:")
				.setCancelable(true)
				// 1. email all ToDoItems
				.setPositiveButton("All Tasks",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String archivedStats;
								todoStats = checkboxAdapter.getAllTasks(main_list);
								archivedStats = checkboxAdapter.getAllTasks(archived_list);
								sendEmail(todoStats + archivedStats);
								dialog.cancel();
							}
						})
				// 2. email select ToDoItems
				.setNeutralButton("Selected Tasks",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which) {
								Intent select = new Intent(Archived_Activity.this,Email_Archived_Activity.class);
								startActivity(select);
								finish();
							}
						});
		alert.show();
	}
	
	// Email intent referenced from
	// http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
	private void sendEmail(String taskstoSend) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, new String[] { "recipient@example.com" });
		i.putExtra(Intent.EXTRA_SUBJECT, "To Do List");
		i.putExtra(Intent.EXTRA_TEXT, taskstoSend);
		try {
			startActivity(Intent.createChooser(i, "Sending mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(Archived_Activity.this,
					"Error2: There are no Email clients installed.", Toast.LENGTH_SHORT).show();
		}
		finish();
	}

	// Settings
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archived_, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String todoStats;
		String archivedStats;
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.see_unarchived:
			Intent unarchived = new Intent(Archived_Activity.this,
					Main_Activity.class);
			startActivity(unarchived);
			finish();
			return true;

		case R.id.show_stats:
			todoStats = checkboxAdapter.getCheckNumbers(main_list,
					"ToDo Items");
			archivedStats = checkboxAdapter.getCheckNumbers(archived_list,
					"Archived ToDo Items");
			new AlertDialog.Builder(this)
					.setTitle("Statistics")
					.setMessage(todoStats + archivedStats)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {
								}
							}).show();

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
