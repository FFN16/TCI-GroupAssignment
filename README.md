# TCI groupassignment EFIT

## Outline
**EFIT** (Exams For IT) is a tool which is used by FHICT for exams. It enables students to execute ICT exams on their own hardware, while not having communication with others during the exam.
The tool consists of 2 parts:

1 **Client-side software (the EFIT client)** it connects to an EFIT server during exams, routes network traffic, and monitors the client. At the end of an exam, and sometimes during the exam, the exam work of a student is sent to the server.

2 **Server-side software (the EFIT server)** students connect to it during exams, while using the EFIT client. It also provides teachers to possibility to create exams before the exam execution time and downloading exam work for grading. The server logs several items from the students’ computer during exams. Invigilators can see the status of students’ connection and log items during the exam. Removal of exam work and log items is done when exam results are finalized.

This assignment focusses on a part of the server-side code.

## Details
We are using github for CI: 
[Git Repo](https://github.com/FFN16/TCI-GroupAssignment) - please send your git username to us on teams and we will add you

### Running tests
1. Load the project in Intelij
2. Open ``src/test``
3. Richt click on java
4. Choose ``Run 'All Tests'``

### Dependencies Used
1. Intelij Plugin: TestCherry

### Test coverage
Class | Coverage | By
--- | --- | ---
Course | 100% | Pre-built
ExamID | 100% | Filippo
ExamSetup | 100% | Filippo
Student | 100% | Filippo
StudentExam | 100% | Floris
ExamExecution | 100% | Floris
SimpleTeacherService | 100% | Floris
SimpleGDPRService | 100% | Filippo

### Other Implementation
Class | By
--- | ---
EFITserver | Floris
SimpleEFITserver | Floris