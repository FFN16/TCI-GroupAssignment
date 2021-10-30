package services.exposed.client;

import model.ExamToken;
import model.ExamID;
import services.exposed.*;
import services.exposed.invigilator.Alert;
import services.external.UserToken;
import services.internal.InvalidTokenException;
import model.Student;

import java.util.List;
import java.util.Set;

/**
 * the EFITClient interface contains behaviour which is needed by the student and/or the efitclient.
 */
public interface EFITClientInterface {

    /**
     * sign up for a specific exam on a specific date.
     * The date is given in epoch format and the date cannot be before today.
     * students are only allowed a maximum of 4 exams per day, otherwise a ExamAmountException is thrown
     * when is student is not allowed to do an exam (for instance, in a wrong semester) a SignupNotAllowedException is thrown
     *
     */
    void signUpForExam(Student student, String examName, Long examDateAsEpochTime) throws IllegalArgumentException, SignupNotAllowedException, ExamNotFoundException, ExamAmountException;


    /**
     * returns a set of plannedexams for which the student is registered.
     * @return
     */
    Set<ExamID> getRegisteredExams(Student student);

    //
    // Behaviour involving execution of exams
    //


    /**
     * method is called when user logs into the server with the efit client.
     * the name of the exam, and the specific classcode is provided by the efit client.
     * returns an appropiate exception when a specific situation occurs
     * returns a non null ExamToken when no exception
     */
    ExamToken loginForExam(UserToken token, ExamID exam, String examClasscode) throws InvalidTokenException, IllegalArgumentException, ExamNotFoundException;

    /**
     * returns the exam material to the caller. returns null when no exam material is present. Can also return alert.yellow, examnotstarted, examended or alert.red
     * @param examToken
     * @return
     */
    Object getExamMaterial(ExamToken examToken);

    //
    //
    //

    /**
     * used as a keepalive message to show the client still is alive.
     * returns the number of seconds still left in the exam. returns negative numbers when exam has not started.
     * returns max_long when exam has ended.
     * NOTE: other methods which use an examtoken, also are seen by the efit server as a keepalive message.
     *
     * @param examToken
     * @return
     * @throws InvalidTokenException
     */
    long sendKeepAlive(ExamToken examToken) throws InvalidTokenException;

    void sendScreenShot(ExamToken examToken,Object screenshot) throws InvalidTokenException;

    void sendListOfRunningProcesses(ExamToken examToken,List<String> runningProcesses) throws InvalidTokenException;

    Alert sendProcessInfo(ExamToken examToken, String propertyKey, String propertyValue) throws InvalidTokenException;

    /**
     * returns ok when everything went ok, yellow when something not fatal happened (please retry), or RED when the examwork cannot
     * be processed at all. an illegalargument exception is thrown when the examwork is null.
     */
    Alert uploadExamWork(ExamToken examToken, Object examWork) throws InvalidTokenException, IllegalArgumentException;

    /**
     * logs out of an exam.
     * returns OK when logged out AFTER handing in examwork, YELLOW when trying to log out without handing in examwork, in this case, logging out is NOT executed.
     * returns examnotstarted or examended alert when exam was not started or when exam was already ended.
     * @param examToken
     * @return
     */
    Alert logoutForExam(ExamToken examToken);

    //
    //
    //



}
