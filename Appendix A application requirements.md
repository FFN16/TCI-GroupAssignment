#EFIT
**EFIT** (Exams For IT) is a tool which is used by FHICT for exams. It enables students to execute ICT exams on their own hardware, while not having communication with others during the exam.
The tool consists of 2 parts:

1 **Client-side software (the EFIT client)** it connects to an EFIT server during exams.

2 **Server-side software (the EFIT server)** students connect to it during exams, while using the EFIT client. It also provides teachers to possibility to create exams before the exam execution time and downloading exam work for grading. The server logs several items from the students’ computer during exams. Invigilators can see the status of students’ connection and log items during the exam. Removal of exam work and log items is done when exam results are finalized.

This assignment focuses on a part of the server-side code.


##Description

This codebase consists of the core interfaces and classes of the EFIT server. All classes for communication with the outside world are not present, and are not considered.

### exposed services
the EFIT server is used by different types of users:
1 Students
students sign up for exams before the exam starts
2 EFIT clients
during exams students connect to the server using local software (the EFIT client), which logs in into the server, routes network traffic, and monitors the client. 
At the end of an exam, and sometimes during the exam, the exam work of a student is sent to the server. There is a specific service for handling this communication.
3 Invigilators
invigilators are present in class during exams. They recieve an overview of all students in their class, and their connection status. They can add extra time for all students,
or for individual students. They also can start and stop exams manually. During exams, invigilators can look at screenshots and computer info of individual students
whenever fraud is suspected. There is a specific service for handling this communication.
4 Teachers
Teachers create exam setups befor the exam date. It consistst of exam info, the amount of classrooms needed, location of exam material, and other information. For each classroom
needed for the exam, a unique classcode is created. when a student enters a specific exam with a classcode, the invigilant knows the student is in his class, 
and he will showup on the invigilants screen. The classcode is also shown on the EFIT client. 
After an exam is finished, a teacher gets access to all information of the exam execution, and the exam work of students can be downloaded.
There is a specific service for handling this communication.
5 Data manager
The data manager indicates which exams are final, so the execution details of an exam are no longer needed. This is also done for GDPR purposes. 
Only metadata of exams is stored. There is a specific service for handling this communication.

### needed services
The EFIT server itself is dependent on external services (f.i. authentication, logging). For each external service, an API interface is present in the code.

### internal services
Internal services are used as a gateway to from the exposed services to the internal model. The model objects should NEVER directly be manipulated by the exposed services.
the internal service also hides the needed services from the exposed services.
EFIT internally uses services which contain utility functions to move through the whole (business) process steps: 
1. creating exams (by teachers), 
2. signing up for exams (by students), 
3. executing exams (by efit clients and by invigilators), 
4. accessing exam data (by teachers), 
5. finalizing exams (by teachers)
6. cleaning up of data (by data managers). 




##Distribution of work
Creating tests and implementation for the behaviour of specific classes at least needs to be distributed between the students in a group. Make sure the following rules appy:
* pick at least one class which is NOT dependent on other classes
* pick at least two classes which are dependent on other classes
* make sure the division of classes is discussed with the teacher when discussing GA1 and written down.
* make sure each student uses his/hers own git branch for their own classes
* distribute ALL classes


##Specific (technical) requirements
1. DO NOT change the given interfaces and abstract classes!
2. Create tests specifications using TestCherry, test code and implementation code which implement the behaviour.
3. use Git commits to show the order in which you executed all steps.
4. when asked to create methods, minimize the amount of methods to use. 
5. The behaviour of those methods is described, and the paramters of these methods can be deduced from other interfaces and classes.
6. The application at least should be able to handle synchronous communication.
7. Do NOT create or add test & code for:
•	the Course class.
•	Exception classes, except constructors when you need them.
•	Generated code by IntelliJ without user input.


##Advanced requirements (for higher mark, see grading sheet)
You can define behaviour yourself (to create tests and code for) for instance:
•	Use asynchronous communication for the different types of users.
•	Use multiple threads for simulating multiple (types of) users.
•	Use time based tests, for instance for ending of exams
•	Make the application locale (time zones,regional settings, language)
Agree with the teacher BEFORE GA2 what you want to want to add.

##Recommendations (strongly!)
•	Create a list of requirements based on the description above, and the given interfaces. 
•	Draw a class diagram first of the whole application.
•	Translate the requirements into needed tests first. use TestCherry for this.
•	Use the class diagram to find out if you are complete. 
•	Per test: determine (and justify) first, what kind of test type is needed (do you need direct or indirect in/output, mocks needed, etc.).
•	Use TDD is much as possible. 
Group Assignments GAx
GA1:
Create and deliver an architecture of the application on different levels. Use the C4 model for this. (see https://c4model.com/ and given pdf). Draw the concept, container, and component levels.
Also, draw a class diagram of the complete application. Demonstrate the relationship between C4 model and the class diagram.
Show live, demonstrate & discuss with teacher in week 5 or 6
GA2:
Convert all requirements (given both in code and in the application description) into test specifications using TestCherry. The requirements might change based on this, so keep track of your assumptions. 
Determine per test, where you need direct I/O, indirect I/O, mocking, stubbing, spying, asserting, or verifying.
Show live, demonstrate & discuss with teacher in week 6 or 7

GA3:
Implement all tests, and submit the whole final assignment, including documentation when necessary as 1 zipfile in Canvas. Also hand in the link to your Git repository

Hand in online before the deadline on Canvas. This assignment is for the final TCI grade!

