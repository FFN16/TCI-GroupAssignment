package services.exposed;

import execution.ExamExecution;
import model.ExamID;
import model.ExamSetup;
import model.StudentExam;
import org.junit.jupiter.api.Test;
import services.exposed.teacher.*;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SimpleTeacherServiceTest {

    /**
     * @verifies create a valid exam and return the ExamID
     * @see SimpleTeacherService#createExamSetup(String, Date)
     */
    @Test
    public void createExamSetup_shouldCreateValidExam() throws Exception {

    }

    /**
     * @verifies throws DuplicateExamException
     * @see SimpleTeacherService#createExamSetup(String, Date)
     */
    @Test
    public void createExamSetup_throwsDuplicateExam() throws Exception {

    }

    /**
     * @verifies throws IllegalArgumentException for date before current
     * @see SimpleTeacherService#createExamSetup(String, Date)
     */
    @Test
    public void createExamSetup_throwsIllegalArgumentException() throws Exception {

    }

    /**
     * @verifies returns a set of ExamIDs
     * @see SimpleTeacherService#getOpenExams()
     */
    @Test
    public void getOpenExams_shouldReturnExams() throws Exception {

    }

    /**
     * @verifies should get Exams between two valid dates
     * @see SimpleTeacherService#getOpenExams(Date, Date)
     */
    @Test
    public void getOpenExams_shouldReturnBetweenDates() throws Exception {

    }

    /**
     * @verifies throws illegal argument for first date after second
     * @see SimpleTeacherService#getOpenExams(Date, Date)
     */
    @Test
    public void getOpenExams_throwsIllegalArgumentException() throws Exception {

    }

    /**
     * @verifies should add materieal to examID
     * @see SimpleTeacherService#addExamMaterial(ExamID, Object)
     */
    @Test
    public void addExamMaterial_shouldAddMaterials() throws Exception {

    }

    /**
     * @verifies throws ExamNotFound for bad ExamID
     * @see SimpleTeacherService#addExamMaterial(ExamID, Object)
     */
    @Test
    public void addExamMaterial_throwsExamNotFound() throws Exception {

    }

    /**
     * @verifies throws ExamStartedException if materials added to exam that has past its start time
     * @see SimpleTeacherService#addExamMaterial(ExamID, Object)
     */
    @Test
    public void addExamMaterial_throwsExamStartedException() throws Exception {

    }

    /**
     * @verifies returns exam materials for valid ExamID
     * @see SimpleTeacherService#getExamMaterials(ExamID)
     */
    @Test
    public void getExamMaterials_shouldReturnValidExamMaterials() throws Exception {

    }

    /**
     * @verifies throws ExamNotFoundException when invalid ExamID supplied
     * @see SimpleTeacherService#getExamMaterials(ExamID)
     */
    @Test
    public void getExamMaterials_throwsExamNotFoundException() throws Exception {

    }

    /**
     * @verifies returns true when material removed from exam
     * @see SimpleTeacherService#removeExamMaterial(ExamID, Object)
     */
    @Test
    public void removeExamMaterial_returnSuccessAfterRemoval() throws Exception {

    }

    /**
     * @verifies throws ExamNotFoundException when invalid ExamID supplied
     * @see SimpleTeacherService#removeExamMaterial(ExamID, Object)
     */
    @Test
    public void removeExamMaterial_throwsExamNotFoundException() throws Exception {

    }

    /**
     * @verifies throws ExamStartedException if date past start date.
     * @see SimpleTeacherService#removeExamMaterial(ExamID, Object)
     */
    @Test
    public void removeExamMaterial_throwsExamStartedException() throws Exception {

    }

    /**
     * @verifies should return exam results as StudentExams
     * @see SimpleTeacherService#getExamResults(ExamID)
     */
    @Test
    public void getExamResults_shouldReturnExamResults() throws Exception {

    }

    /**
     * @verifies throws ExamNotFoundException when wrong ExamID provided
     * @see SimpleTeacherService#getExamResults(ExamID)
     */
    @Test
    public void getExamResults_throwsExamNotFoundException() throws Exception {

    }

    /**
     * @verifies throw ExamNotEndedException when exam has not yet finished
     * @see SimpleTeacherService#getExamResults(ExamID)
     */
    @Test
    public void getExamResults_throwsExamNotEndedException() throws Exception {

    }

    /**
     * @verifies
     * @see SimpleTeacherService#finalizeExam(ExamID)
     */
    @Test
    public void finalizeExam_shouldFinalizeExamAndReturnSuccess() throws Exception {

    }

    /**
     * @verifies throws ExamNotFoundException when wrong ExamID provided
     * @see SimpleTeacherService#finalizeExam(ExamID)
     */
    @Test
    public void finalizeExam_throwsExamNotFoundException() throws Exception {

    }

}
