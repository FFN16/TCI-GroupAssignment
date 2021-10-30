package services.exposed.gdpr;

import model.ExamID;
import services.exposed.ExamNotFoundException;
import services.exposed.gdpr.GDPRInterface;

public class SimpleGDPRService implements GDPRInterface {
    @Override
    public void removeStudentExamData(ExamID examID) throws ExamNotFoundException, ExamNotFinalizedException {

    }
    // TODO
}
