package services.exposed;

import execution.ExamExecution;
import model.ExamID;
import model.ExamSetup;
import model.StudentExam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.SimpleEFITserver;
import services.exposed.teacher.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleTeacherServiceTest {

    private static final long INVALID_DATE = System.currentTimeMillis() - 1_000_000L;
    private static final long VALID_FIRST_DATE = System.currentTimeMillis() + 1_000_000L;
    private static final long VALID_SECOND_DATE = System.currentTimeMillis() + 4_000_000L;

    /**
     * @verifies create a valid exam and return the ExamID
     * @see SimpleTeacherService#createExamSetup(String, Date)
     */
    @Test
    public void createExamSetup_shouldCreateValidExam() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date date = mock(Date.class);

        when(date.getTime()).thenReturn(VALID_FIRST_DATE);

        ExamID examID = teachI.createExamSetup(name,date);
        Assertions.assertEquals(examID.getExamName(), name);
        Assertions.assertEquals(examID.getTimeOfExamInEpochFormat(), VALID_FIRST_DATE);
    }

    /**
     * @verifies throws DuplicateExamException
     * @see SimpleTeacherService#createExamSetup(String, Date)
     */
    @Test
    public void createExamSetup_throwsDuplicateExam() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date date = mock(Date.class);

        when(date.getTime()).thenReturn(VALID_FIRST_DATE);

        ExamID examID = teachI.createExamSetup(name,date);
        Assertions.assertThrows(DuplicateExamException.class, () -> teachI.createExamSetup(name, date));
    }

    /**
     * @verifies throws IllegalArgumentException for date before current
     * @see SimpleTeacherService#createExamSetup(String, Date)
     */
    @Test
    public void createExamSetup_throwsIllegalArgumentException() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date date = mock(Date.class);

        // Using a date before current so that its incorrect
        when(date.getTime()).thenReturn(INVALID_DATE);

        Assertions.assertThrows(IllegalArgumentException.class, () -> teachI.createExamSetup(name, date));
    }

    /**
     * @verifies returns a set of ExamIDs
     * @see SimpleTeacherService#getOpenExams()
     */
    @Test
    public void getOpenExams_shouldReturnExams() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date date = mock(Date.class);

        when(date.getTime()).thenReturn(VALID_FIRST_DATE);

        ExamID examID = teachI.createExamSetup(name,date);
        Set<ExamID> examIDS = teachI.getOpenExams();
        Assertions.assertEquals(examIDS.iterator().next(), examID);
    }

    /**
     * @verifies should get Exams between two valid dates
     * @see SimpleTeacherService#getOpenExams(Date, Date)
     */
    @Test
    public void getOpenExams_shouldReturnBetweenDates() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date dateFirst = mock(Date.class);
        Date dateSecond = mock(Date.class);

        when(dateFirst.getTime()).thenReturn(VALID_FIRST_DATE);
        when(dateSecond.getTime()).thenReturn(VALID_SECOND_DATE);

        ExamID examID = teachI.createExamSetup(name,dateFirst);
        Set<ExamID> examIDS = teachI.getOpenExams(dateFirst, dateSecond);
        Assertions.assertEquals(examIDS.iterator().next(), examID);
    }

    /**
     * @verifies throws illegal argument for first date after second
     * @see SimpleTeacherService#getOpenExams(Date, Date)
     */
    @Test
    public void getOpenExams_throwsIllegalArgumentException() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        Date dateFirst = mock(Date.class);
        Date dateSecond = mock(Date.class);

        when(dateFirst.getTime()).thenReturn(VALID_FIRST_DATE);
        when(dateSecond.getTime()).thenReturn(VALID_SECOND_DATE);

        Assertions.assertThrows(IllegalArgumentException.class, () -> teachI.getOpenExams(dateSecond, dateFirst));
    }

    /**
     * @verifies should add materieal to examID
     * @see SimpleTeacherService#addExamMaterial(ExamID, Object)
     */
    @Test
    public void addExamMaterial_shouldAddMaterials() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date date = mock(Date.class);

        when(date.getTime()).thenReturn(VALID_FIRST_DATE);

        ExamID examID = teachI.createExamSetup(name,date);
        teachI.addExamMaterial(examID, "TestObject");
        Assertions.assertEquals(teachI.getExamMaterials(examID).get(0), "TestObject");
    }

    /**
     * @verifies throws ExamNotFound for bad ExamID
     * @see SimpleTeacherService#addExamMaterial(ExamID, Object)
     */
    @Test
    public void addExamMaterial_throwsExamNotFound() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        ExamID examID = mock(ExamID.class);

        Assertions.assertThrows(ExamNotFoundException.class, () -> teachI.addExamMaterial(examID, "TestObject"));
    }

    /**
     * @verifies throws ExamStartedException if materials added to exam that has past its start time
     * @see SimpleTeacherService#addExamMaterial(ExamID, Object)
     */
    @Test
    public void addExamMaterial_throwsExamStartedException() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date date = mock(Date.class);

        when(date.getTime()).thenReturn(VALID_FIRST_DATE);

        ExamID examID = teachI.createExamSetup(name,date);
        server.startExam(examID);
        Assertions.assertThrows(ExamStartedException.class, () -> teachI.addExamMaterial(examID, "TestObject"));
    }

    /**
     * @verifies returns true when material removed from exam
     * @see SimpleTeacherService#removeExamMaterial(ExamID, Object)
     */
    @Test
    public void removeExamMaterial_returnSuccessAfterRemoval() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date date = mock(Date.class);

        when(date.getTime()).thenReturn(VALID_FIRST_DATE);

        ExamID examID = teachI.createExamSetup(name,date);
        teachI.addExamMaterial(examID, "TestObject");
        Assertions.assertEquals(teachI.removeExamMaterial(examID, "TestObject"), true);
    }

    /**
     * @verifies throws ExamNotFoundException when invalid ExamID supplied
     * @see SimpleTeacherService#removeExamMaterial(ExamID, Object)
     */
    @Test
    public void removeExamMaterial_throwsExamNotFoundException() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        ExamID examID = mock(ExamID.class);

        Assertions.assertThrows(ExamNotFoundException.class, () -> teachI.removeExamMaterial(examID, "TestObject"));
    }

    /**
     * @verifies throws ExamStartedException if date past start date.
     * @see SimpleTeacherService#removeExamMaterial(ExamID, Object)
     */
    @Test
    public void removeExamMaterial_throwsExamStartedException() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        String name = "FinalExam";
        Date date = mock(Date.class);

        when(date.getTime()).thenReturn(VALID_FIRST_DATE);

        ExamID examID = teachI.createExamSetup(name,date);
        server.startExam(examID);

        Assertions.assertThrows(ExamStartedException.class, () -> teachI.removeExamMaterial(examID, "TestObject"));
    }

    /**
     * @verifies should return exam results as StudentExams
     * @see SimpleTeacherService#getExamResults(ExamID)
     */
    @Test
    public void getExamResults_shouldReturnExamResults() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

        
    }

    /**
     * @verifies throws ExamNotFoundException when wrong ExamID provided
     * @see SimpleTeacherService#getExamResults(ExamID)
     */
    @Test
    public void getExamResults_throwsExamNotFoundException() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

    }

    /**
     * @verifies throw ExamNotEndedException when exam has not yet finished
     * @see SimpleTeacherService#getExamResults(ExamID)
     */
    @Test
    public void getExamResults_throwsExamNotEndedException() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

    }

    /**
     * @verifies
     * @see SimpleTeacherService#finalizeExam(ExamID)
     */
    @Test
    public void finalizeExam_shouldFinalizeExamAndReturnSuccess() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

    }

    /**
     * @verifies throws ExamNotFoundException when wrong ExamID provided
     * @see SimpleTeacherService#finalizeExam(ExamID)
     */
    @Test
    public void finalizeExam_throwsExamNotFoundException() throws Exception {
        SimpleEFITserver server = SimpleEFITserver.getInstanceTesting();
        TeacherInterface teachI = server.getTeacherInterface();

    }

}
