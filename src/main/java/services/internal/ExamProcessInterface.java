package services.internal;

import model.ExamID;
import model.ExamSetup;
import model.Student;
import model.StudentExam;
import services.exposed.ExamNotFoundException;
import services.exposed.gdpr.ExamNotFinalizedException;
import services.exposed.teacher.DuplicateExamException;
import services.exposed.teacher.ExamNotEndedException;
import services.external.FHICTUserRole;
import services.external.UserToken;

import java.util.Date;
import java.util.Set;

public interface ExamProcessInterface {


    /**
     * this method checks if the logged in user has a specific UserRole.
     * The UserRole is checked against the FHICTRoles contained in the UserToken.
     *
     * The following mapping is used for comparing FHICT roles to UserRole's in this application
     * UUID.toString        UserRole
     * 0002-0001            TEACHER
     * 0002-0002            STUDENT
     * 0002-0003            INVIGILATOR
     * 0002-0004            DATAMANAGER
     *
     * @param token token of loggedin user
     * @param queriedRole role which you want to know if the user has it.
     * @return true if user has the role, false if not.
     * @throws InvalidTokenException when used token is not a valid token.
     */
    boolean userHasRole(UserToken token, UserRole queriedRole) throws InvalidTokenException;



    Set<ExamID> getSetupExamIDs();
    Set<ExamID> getInProgressOrUnfinalizedExamIDs();
    Set<ExamID> getFinalizedExamIDs();


    /**
     * process step 1. create Exam for the first time
     * @param examname
     * @param begindate
     * @throws DuplicateExamException
     */
    ExamID createExamSetup(String examname, Date begindate) throws DuplicateExamException;

    /**
     * process step 2. signing up for an exam
     * @param student
     * @param examID
     * @throws ExamNotFoundException
     */
    void signUpForExam(Student student, ExamID examID) throws ExamNotFoundException;



    /**
     * process  step 3.
     * start executing given exam. this method is only executed when the begintime of the exam has passed and the exam has not been started yet.
     * when it does start for the first time, it creates an examexecution object for the exam. it also adds it to the currently running exams.
     * @param examID
     */
    void startExam(ExamID examID) throws ExamNotFoundException;


    /**
     * process  step 3.
     * stop given exam. this method is only executed when the end of the exam has passed.
     * it removes the exam from the examsetup, so only examexecution object is left. the exam is moved to the
     * finished exams.
     * @param examID
     */
    void stopExam(ExamID examID) throws ExamNotFoundException;


    /**
     * process  step 4.
     * gets examresults from a finished exam. when exam is not found or not finished, an appropriate
     * exception is thrown.
     * @param exam
     * @return
     * @throws ExamNotFoundException
     * @throws ExamNotEndedException
     */
    Set<StudentExam> getExamResults(ExamID exam) throws ExamNotFoundException, ExamNotEndedException;


    /**
     * process  step 5.
     * finalize given exam.
     * @param examID
     * @return true when finalized, otherwise false
     */
    boolean finalizeExam(ExamID examID) throws ExamNotFoundException;

    /**
     * process step 6.
     * removes all detailed data from an exam. only leaves the metadata of the exam
     * @param examID
     * @throws ExamNotFoundException
     * @throws ExamNotFinalizedException
     */
    void removeStudentExamData(ExamID examID) throws ExamNotFoundException, ExamNotFinalizedException;


}
