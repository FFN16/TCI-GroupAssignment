package execution;

import model.ExamID;
import model.ExamSetup;
import model.StudentExam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static execution.ExamExecutionState.*;
import exceptions.*;

/**
 * an examexecution consists of the examsetup with all examstudents who logged in for the exam with all of their data.
 *
 * it also contains methods for getting A COPY OF the studentexams objects.
 *
 * When all results of an exam are final (exam marks are registered in another system), the examexecution will be set
 * to status 'finalized'. This means its data can be removed by the data manager, and  only the metadata remains:
 * this is
 * the name of the exam, the date of the exam
 * the number of generated classcodes
 * the number of students which used each classcode
 * the exam duration per used classcode
 *
 * cleanup of the data can only be performed by the datamanager. when no finalization has taken place yet, the data cannot
 * be cleaned, but a DataNotFinalizedException is thrown.
 *
 * the examID of the examExecution can be returned.
 *
 * after cleaning of data: any method which tries to get detailed information, will throw an DataCleanedException
 *
 *
 */
public class ExamExecution {
    // Data that can be wiped once finalized
    private ExamSetup examSetup;
    private List<StudentExam> studentExams;
    private ExamExecutionState state;

    // Metadata that must be held
    private String examName;
    private Long examDate;
    private int classCodesCount;
    // Map the user count to the class code
    private Map<String, Integer> classCodeUsers;
    // Map the duration to the class code
    private Map<String, Long> classCodeTime;

    public ExamExecution(ExamSetup examSetup, List<StudentExam> studentExams) {
        this.examSetup = examSetup;
        this.studentExams = studentExams;
        this.state = NOTFINALIZED;
        this.examName = examSetup.getExamName();
        this.examDate = examSetup.getBeginTime();
        this.classCodesCount = examSetup.getClassCodeAmount();
    }

    public ArrayList<StudentExam> getCopyStudentExams() throws DataCleanedException{
        if(state == CLEARED){
            throw new DataCleanedException();
        }
        return new ArrayList<>(studentExams);
    }

    // To be used by datamanager
    public ExamID clean() throws DataNotFinalizedException{
        if(!state.equals(FINALIZED)){
            throw new DataNotFinalizedException();
        }

        state = CLEARED;
        ExamID examID = examSetup.getExamID();
        examSetup = null;
        studentExams = null;
        return examID;
    }

    public ExamSetup getExamSetup() throws DataCleanedException {
        if(state == CLEARED){
            throw new DataCleanedException();
        }
        return examSetup;
    }

    public void setExamSetup(ExamSetup examSetup) {
        this.examSetup = examSetup;
    }

    public List<StudentExam> getStudentExams() throws DataCleanedException {
        if(state == CLEARED){
            throw new DataCleanedException();
        }
        return studentExams;
    }

    public void setStudentExams(List<StudentExam> studentExams) {
        this.studentExams = studentExams;
    }

    public ExamExecutionState getState() {
        return state;
    }

    public void setState(ExamExecutionState state) {
        this.state = state;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getClassCodesCount() {
        return classCodesCount;
    }

    public void setClassCodesCount(int classCodesCount) {
        this.classCodesCount = classCodesCount;
    }

    public Map<String, Integer> getClassCodeUsers() {
        return classCodeUsers;
    }

    public void setClassCodeUsers(Map<String, Integer> classCodeUsers) {
        this.classCodeUsers = classCodeUsers;
    }

    public Map<String, Long> getClassCodeTime() {
        return classCodeTime;
    }

    public void setClassCodeTime(Map<String, Long> classCodeTime) {
        this.classCodeTime = classCodeTime;
    }
}
