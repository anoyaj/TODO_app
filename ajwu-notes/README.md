[RELEASE] Version 1.0, DATE: 9/26/2014

[DESCRIPTION] 

TODO is a portable android app ultility that helps record, archive and email "To do" items on the go. This android application supports the following features:

1. Ability to Record/Add your own Editable tasks 
2. Ability to Delete previously added/archived tasks
3. Ability to Archive/UnArchive tasks for later
4. Ability to Email items, separately, by selection or by bulk with a long press of a finger. 
5. Simple and easy-to-understand GUI that focuses on a more user friendly "minimalist" approach.  
6. Generates a Summary Statistic reporting how many items are Archived/UnArchived and/or are checked/un-checked at any given time.  
6. [*Working Feature*] Dynamic Persistant Memory Serializing Data storage model * Working feature might be laggy/glitchy but in beta testing at the moment.  

Interactive, lightweight and compact, the TODO app is an ideal choice for those who want a simple android taskManager on their phones.   

[SCREENSHOTS]
![Alt text](https://github.com/anoyaj/TODO_app/tree/master/ajwu-notes/doc/Main1.png)

[GUIDE]

- Navigate and cycle through screens (Main Screen and Archived Items Screen) using the Menu. 
- Tasks are recorded when the item is "added" via pressing the <Add> button in the Main Screen. 
- To Archive/Delete or Email an individual task, touch the desired task on the screen. This prompts a popup dialog box asking what you want to do with the item: <Email>, <Archive> or <Delete>. Similar options are accesible in the Archived Screen but instead of <Archive> has <UnArchive>.  
- To Mass/Bulk email a selection or all the tasks, select the <Bulk Email Tasks>. This prompts the user to either choose from a <Selection> or <All Tasks>.  

[DESIGN]

Refer to the UML in the ajwu-notes/doc/ajwu_ToDo_UML.pdf directory 

[REFERENCES]

2.      Android Documentation     http://developer.android.com/reference/org/w3c/dom/Document.html
3.      Abram Hindle's Array Adapter Tutorial     http://www.youtube.com/watch?v=7zKCuqScaRE&list=UUTLkh9KmeYXQBR59wJxq1eg
3.      TeamTreeHouse Programming Tutorial    http://www.youtube.com/watch?v=7zKCuqScaRE&list=UUTLkh9KmeYXQBR59wJxq1eg

In my code, I've used the above sources as references and for learning purposes and applied what I learned while developing this TODO app. Unless explicitly stated in my code (see commented sections), I've adapted parts of what I learned (above) and applied a different implementation. 

There are also some StackOverFlow references I've used which is properly cited in the src code within the corresponding classes I used them in.  

The background pngs I used as a layout for my android app (found in the directory: 
ajwu-notes/res/drawable-hdpi) can be found here: http://aldeasinfantilessos.org.do/images/

The UML software I used to generate the ajwu_ToDo_UML.pdf was: https://www.draw.io/ 

[LICENSE]

Copyright (C) 2014 Anthony Wu.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.