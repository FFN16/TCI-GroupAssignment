package services.exposed.teacher;

import model.Course;
import model.ExamID;
import model.ExamSetup;
import model.StudentExam;
import services.exposed.ExamNotFoundException;
import services.exposed.client.SimpleEFITClientService;
import server.SimpleEFITserver;

import java.util.*;
import java.util.stream.Collectors;
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
        return getServer().getSetupExamIDs();
    }

    @Override
    public Set<ExamID> getOpenExams(Date dateOnOrAfter, Date dateOnOrBefore) throws IllegalArgumentException {
        if(dateOnOrAfter.getTime() > dateOnOrBefore.getTime()){
            throw new IllegalArgumentException();
        }

        return getServer().getSetupExamIDs().stream().filter(x -> {
            return dateOnOrAfter.getTime() <= x.getTimeOfExamInEpochFormat() && dateOnOrBefore.getTime() >= x.getTimeOfExamInEpochFormat();
        }).collect(Collectors.toSet());
    }

    @Override
    public void addExamMaterial(ExamID exam, Object examMaterial) throws ExamNotFoundException, ExamStartedException {
        ExamSetup examSetup = getServer().getExamSetupByExamId(exam);
        if(examSetup == null){
            throw new ExamNotFoundException();
        }
        if(getServer().isExamRunning(examSetup)){
            throw new ExamStartedException();
        }
        List<Object> materials = examSetup.getExtraMaterials();
        materials.add(examMaterial);
        examSetup.setExtraMaterials(materials);
    }

    @Override
    public List<Object> getExamMaterials(ExamID exam) {
        ExamSetup examSetup = getServer().getExamSetupByExamId(exam);
        List<Object> materials = examSetup.getExtraMaterials();
        return materials;
    }

    @Override
    public boolean removeExamMaterial(ExamID exam, Object examMaterial) throws ExamNotFoundException, ExamStartedException {
        ExamSetup examSetup = getServer().getExamSetupByExamId(exam);
        if(examSetup == null){
            throw new ExamNotFoundException();
        }
        if(getServer().isExamRunning(examSetup)){
            throw new ExamStartedException();
        }
        List<Object> materials = examSetup.getExtraMaterials();
        if(materials.contains(examMaterial)){
            materials.remove(examMaterial);
            examSetup.setExtraMaterials(materials);
            return true;
        }
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
