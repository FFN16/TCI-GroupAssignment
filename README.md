# TCI groupassignment EFIT

**EFIT** (Exams For IT) is a tool which is used by FHICT for exams. It enables students to execute ICT exams on their own hardware, while not having communication with others during the exam.
The tool consists of 2 parts:

1 **Client-side software (the EFIT client)** it connects to an EFIT server during exams, routes network traffic, and monitors the client. At the end of an exam, and sometimes during the exam, the exam work of a student is sent to the server.

2 **Server-side software (the EFIT server)** students connect to it during exams, while using the EFIT client. It also provides teachers to possibility to create exams before the exam execution time and downloading exam work for grading. The server logs several items from the students’ computer during exams. Invigilators can see the status of students’ connection and log items during the exam. Removal of exam work and log items is done when exam results are finalized.

This assignment focusses on a part of the server-side code.


