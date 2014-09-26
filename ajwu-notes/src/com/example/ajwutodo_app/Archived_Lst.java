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

public class Archived_Lst {

	protected ArrayList<ToDoItem> archivedList;
	protected ArrayList<Listener> listeners;

	public Archived_Lst() {
		archivedList = new ArrayList<ToDoItem>();
		listeners = new ArrayList<Listener>();
	}

	public Collection<ToDoItem> getTodoItems() {
		return archivedList;
	}

	public void addItem(ToDoItem item) {
		archivedList.add(item);
		notifyListeners();
	}

	private void notifyListeners() {
		for (Listener listener : listeners) {
			listener.update();
		}
	}

	public void removeItem(ToDoItem item) {
		archivedList.remove(item);
		notifyListeners();
	}

	public void addListener(Listener l) {
		listeners.add(l);
	}

	public void removeListener(Listener l) {
		listeners.remove(l);
	}
}