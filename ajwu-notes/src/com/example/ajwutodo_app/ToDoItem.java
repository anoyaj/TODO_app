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

public class ToDoItem {
	public String name;
	public boolean checkbox;
	public boolean archive;

	ToDoItem(String new_name, boolean checkboxed, boolean archived) {
		name = new_name;
		checkbox = checkboxed;
		archive = archived;
	}
	
	public ToDoItem(String name, boolean selected) {
		this.name = name;
		this.checkbox = selected;
	}

	public String getToDoName() {
		return this.name;
	}

	public String toString() {
		return getToDoName();
	}

	public boolean checkbox() {
		return checkbox;
	}

}
