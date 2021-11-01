package server;

import exceptions.DataCleanedException;
import execution.ExamExecution;
import model.ExamID;
import services.exposed.client.EFITClientInterface;
import services.exposed.gdpr.GDPRInterface;
import services.exposed.invigilator.InvigilatorInterface;
import services.exposed.teacher.TeacherInterface;
import model.ExamSetup;
import model.Student;
import services.internal.ExamProcessInterface;

import java.util.*;
import java.util.stream.Collectors;

/**
 * the server.EFITserver class holds all information on exams, registered students for exams, exam results and logging
 *
 */
public abstract class EFITserver implements ExamProcessInterface {
    // needed data before exams start
    private Set<ExamSetup> setupExams;
    private Map<Student,Set<ExamSetup>> signedUpExamsPerStudent;
    // needed data during exams
    private Set<ExamExecution> currentlyRunningExams;
    // needed data after exams
    private Set<ExamExecution> finishedExams;
    //
    // needed external interfaces
    private EFITClientInterface efitClientInterface;
    private TeacherInterface teacherInterface;
    private InvigilatorInterface invigilatorInterface;
    private GDPRInterface gdprInterface;
    //

    /**
     * Constructor.
     * @param efitClientInterface
     * @param teacherInterface
     * @param invigilatorInterface
     * @param gdprInterface
     */
    public EFITserver(EFITClientInterface efitClientInterface, TeacherInterface teacherInterface, InvigilatorInterface invigilatorInterface, GDPRInterface gdprInterface) {
        this.efitClientInterface = efitClientInterface;
        this.teacherInterface = teacherInterface;
        this.invigilatorInterface = invigilatorInterface;
        this.gdprInterface = gdprInterface;
        this.setupExams = new HashSet<>();
        this.signedUpExamsPerStudent = new HashMap<>();
        this.currentlyRunningExams = new HashSet<>();
        this.finishedExams = new HashSet<>();
    }

    public EFITClientInterface getEfitClientInterface() {
        return efitClientInterface;
    }

    public TeacherInterface getTeacherInterface() {
        return teacherInterface;
    }

    public InvigilatorInterface getInvigilatorInterface() {
        return invigilatorInterface;
    }

    public GDPRInterface getGdprInterface() {
        return gdprInterface;
    }

    public Set<ExamSetup> getSetupExams() {
        return setupExams;
    }

    public Map<Student, Set<ExamSetup>> getSignedUpExamsPerStudent() {
        return signedUpExamsPerStudent;
    }

    public Set<ExamExecution> getCurrentlyRunningExams() {
        return currentlyRunningExams;
    }

    public Set<ExamExecution> getFinishedExams() {
        return finishedExams;
    }

    // Methods to keep implements clean

    public ExamSetup getExamSetupByExamId(ExamID examID) {
        return getSetupExams().stream().filter(x -> x.getExamID().equals(examID)).findFirst().orElse(null);
    }

    public List<Student> getStudentsForExam(ExamSetup examSetup) {
        return getSignedUpExamsPerStudent().entrySet().stream().filter(x -> x.getValue().contains(examSetup)).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public boolean isExamRunning(ExamSetup examSetup) {
        return getCurrentlyRunningExams().stream().anyMatch(x -> {
            try {
                return x.getExamSetup().equals(examSetup);
            } catch (DataCleanedException e) {
                return false;
            }
        });
    }


}
