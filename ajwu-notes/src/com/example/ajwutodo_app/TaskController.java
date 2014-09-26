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

public class TaskController {

	private static Main_Lst mainList = null;
	private static Archived_Lst archivedList = null;

	static public Main_Lst getMain_Lst() {
		if (mainList == null) {
			mainList = new Main_Lst();
		}
		return mainList;
	}

	static public Archived_Lst getArchived_Lst() {
		if (archivedList == null) {
			archivedList = new Archived_Lst();
		}
		return archivedList;
	}
	
	// Add item to main list
	public static void addMItem(ToDoItem item) {
		getMain_Lst().addItem(item);
	}
	
	//Add item to archived list
	public static void addAItem(ToDoItem item) {
		getArchived_Lst().addItem(item);
	}

}
