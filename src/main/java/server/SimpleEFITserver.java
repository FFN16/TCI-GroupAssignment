package server;

import model.ExamID;
import model.Student;
import model.StudentExam;
import services.exposed.ExamNotFoundException;
import services.exposed.client.EFITClientInterface;
import services.exposed.client.SimpleEFITClientService;
import services.exposed.gdpr.ExamNotFinalizedException;
import services.exposed.gdpr.GDPRInterface;
import services.exposed.gdpr.SimpleGDPRService;
import services.exposed.invigilator.InvigilatorInterface;
import services.exposed.invigilator.SimpleInvigilatorService;
import services.exposed.teacher.DuplicateExamException;
import services.exposed.teacher.ExamNotEndedException;
import services.exposed.teacher.SimpleTeacherService;
import services.exposed.teacher.TeacherInterface;
import services.external.UserToken;
import services.internal.InvalidTokenException;
import services.internal.UserRole;

import java.util.Date;
import java.util.Set;

public class SimpleEFITserver extends EFITserver {

    // Instancing for between class testing
    public static final SimpleEFITserver instance = new SimpleEFITserver(new SimpleEFITClientService(), new SimpleTeacherService(), new SimpleInvigilatorService(), new SimpleGDPRService());
    public static SimpleEFITserver getInstance(){
        return instance;
    }
    public static SimpleEFITserver getInstanceTesting(){
        return new SimpleEFITserver(new SimpleEFITClientService(), new SimpleTeacherService(), new SimpleInvigilatorService(), new SimpleGDPRService());
    }

    /**
     * Constructor
     */
    public SimpleEFITserver(EFITClientInterface efitClientInterface, TeacherInterface teacherInterface, InvigilatorInterface invigilatorInterface, GDPRInterface gdprInterface) {
        super(efitClientInterface, teacherInterface, invigilatorInterface, gdprInterface);
        ((SimpleEFITClientService) efitClientInterface).setServer(this);
        ((SimpleTeacherService) teacherInterface).setServer(this);
        ((SimpleInvigilatorService) invigilatorInterface).setServer(this);
        ((SimpleGDPRService) gdprInterface).setServer(this);

    }

    /**
     * this method checks if the logged in user has a specific UserRole.
     * The UserRole is checked against the FHICTRoles contained in the UserToken.
     * <p>
     * The following mapping is used for comparing FHICT roles to UserRole's in this application
     * UUID.toString        UserRole
     * 0002-0001            TEACHER
     * 0002-0002            STUDENT
     * 0002-0003            INVIGILATOR
     * 0002-0004            DATAMANAGER
     *
     * @param token       token of loggedin user
     * @param queriedRole role which you want to know if the user has it.
     * @return true if user has the role, false if not.
     * @throws InvalidTokenException when used token is not a valid token.
     */
    @Override
    public boolean userHasRole(UserToken token, UserRole queriedRole) throws InvalidTokenException {
        return false;
    }

    @Override
    public Set<ExamID> getSetupExamIDs() {
        return null;
    }

    @Override
    public Set<ExamID> getInProgressOrUnfinalizedExamIDs() {
        return null;
    }

    @Override
    public Set<ExamID> getFinalizedExamIDs() {
        return null;
    }

    /**
     * process step 1. create Exam for the first time
     *
     * @param examname
     * @param begindate
     * @throws DuplicateExamException
     */
    @Override
    public ExamID createExamSetup(String examname, Date begindate) throws DuplicateExamException {
        return null;
    }

    /**
     * process step 2. signing up for an exam
     *
     * @param student
     * @param examID
     * @throws ExamNotFoundException
     */
    @Override
    public void signUpForExam(Student student, ExamID examID) throws ExamNotFoundException {

    }

    /**
     * process  step 3.
     * start executing given exam. this method is only executed when the begintime of the exam has passed and the exam has not been started yet.
     * when it does start for the first time, it creates an examexecution object for the exam. it also adds it to the currently running exams.
     *
     * @param examID
     */
    @Override
    public void startExam(ExamID examID) throws ExamNotFoundException {

    }

    /**
     * process  step 3.
     * stop given exam. this method is only executed when the end of the exam has passed.
     * it removes the exam from the examsetup, so only examexecution object is left. the exam is moved to the
     * finished exams.
     *
     * @param examID
     */
    @Override
    public void stopExam(ExamID examID) throws ExamNotFoundException {

    }

    /**
     * process  step 4.
     * gets examresults from a finished exam. when exam is not found or not finished, an appropriate
     * exception is thrown.
     *
     * @param exam
     * @return
     * @throws ExamNotFoundException
     * @throws ExamNotEndedException
     */
    @Override
    public Set<StudentExam> getExamResults(ExamID exam) throws ExamNotFoundException, ExamNotEndedException {
        return null;
    }

    /**
     * process  step 5.
     * finalize given exam.
     *
     * @param examID
     * @return true when finalized, otherwise false
     */
    @Override
    public boolean finalizeExam(ExamID examID) throws ExamNotFoundException {
        return false;
    }

    /**
     * process step 6.
     * removes all detailed data from an exam. only leaves the metadata of the exam
     *
     * @param examID
     * @throws ExamNotFoundException
     * @throws ExamNotFinalizedException
     */
    @Override
    public void removeStudentExamData(ExamID examID) throws ExamNotFoundException, ExamNotFinalizedException {

    }
}