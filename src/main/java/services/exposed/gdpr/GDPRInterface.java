package services.exposed.gdpr;

import execution.ExamExecution;
import model.ExamID;
import services.exposed.ExamNotFoundException;

public interface GDPRInterface {


    void removeStudentExamData(ExamID examID) throws ExamNotFoundException, ExamNotFinalizedException;

}
