package services.exposed.teacher;

import model.ExamID;
import model.StudentExam;
import services.exposed.ExamNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TeacherInterface {

    ExamID createExamSetup(String examname, Date begindate) throws DuplicateExamException;

    Set<ExamID> getOpenExams();

    Set<ExamID> getOpenExams(Date dateOnOrAfter, Date dateOnOrBefore);

    void addExamMaterial(ExamID exam, Object examMaterial) throws ExamNotFoundException, ExamStartedException;

    List<Object> getExamMaterials(ExamID exam);

    boolean removeExamMaterial(ExamID exam, Object examMaterial) throws ExamNotFoundException, ExamStartedException;

    Set<StudentExam> getExamResults(ExamID exam) throws ExamNotFoundException, ExamNotEndedException;

    /**
     * finalize given exam.
     * @param examID
     * @return true when finalized, otherwise false
     */
    boolean finalizeExam(ExamID examID) throws ExamNotFoundException;

}
