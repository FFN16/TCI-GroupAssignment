package services.exposed.teacher;

import model.Course;
import model.ExamID;
import model.ExamSetup;
import model.StudentExam;
import services.exposed.ExamNotFoundException;
import services.exposed.client.SimpleEFITClientService;
import server.SimpleEFITserver;

import java.util.*;
import java.util.stream.Stream;

public class SimpleTeacherService implements TeacherInterface {

    private SimpleEFITserver server;

    public SimpleEFITserver getServer() {
        return server;
    }

    public void setServer(SimpleEFITserver server) {
        this.server = server;
    }

    @Override
    public ExamID createExamSetup(String examname, Date begindate) throws DuplicateExamException, IllegalArgumentException {
        long time = System.currentTimeMillis();
        if(time > begindate.getTime()){
            throw new IllegalArgumentException();
        }
        ExamID examID = new ExamID(examname, begindate.getTime());

        System.out.println(getServer().getSetupExams());
        if(getServer().getSetupExams().stream().anyMatch(x -> x.getExamID().equals(examID))){
            throw new DuplicateExamException();
        }


        Course course = new Course("Testing and Continuous Integration", "TCI", 3);
        ExamSetup examSetup = new ExamSetup(course, examID, 60 * 60 * 1000);
        getServer().getSetupExams().add(examSetup);
        return examID;
    }

    @Override
    public Set<ExamID> getOpenExams() {
        return null;
    }

    @Override
    public Set<ExamID> getOpenExams(Date dateOnOrAfter, Date dateOnOrBefore) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void addExamMaterial(ExamID exam, Object examMaterial) throws ExamNotFoundException, ExamStartedException {

    }

    @Override
    public List<Object> getExamMaterials(ExamID exam) {
        return null;
    }

    @Override
    public boolean removeExamMaterial(ExamID exam, Object examMaterial) throws ExamNotFoundException, ExamStartedException {
        return false;
    }

    @Override
    public Set<StudentExam> getExamResults(ExamID exam) throws ExamNotFoundException, ExamNotEndedException {
        return null;
    }

    @Override
    public boolean finalizeExam(ExamID examID) throws ExamNotFoundException {
        return false;
    }
    // TODO
}
