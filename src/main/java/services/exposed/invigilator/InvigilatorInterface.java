package services.exposed.invigilator;

import model.Student;

import java.util.Set;

public interface InvigilatorInterface {

    Set<Student> getListOfEnteredStudents();

    void addExtraTime(Student student,long amountOfSeconds);

    void addExtraTime(String classCode,long amountOfSeconds);

    void changeExamStartTime(long newStartTimeInEpochFormat);

    void changeExamEndTime(long newEndTimeInEpochFormat);

    Object showScreenShot(Student student, int screenshotsAgo);

    void resetWarningOnStudent(Student student, Warning warning);


}
