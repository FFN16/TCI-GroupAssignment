package execution;

import exceptions.*;
import model.ExamID;
import model.ExamSetup;
import model.Student;
import model.StudentExam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ExamExecutionTest {

    /**
     * @verifies create ExamExecution with valid parameters
     * @see ExamExecution#ExamExecution(model.ExamSetup, java.util.List)
     */
    @Test
    public void ExamExecution_shouldCreateExamExecutionWithValidParameters() throws Exception {
        var examSetup = mock(ExamSetup.class);
        var studentExams = Arrays.asList(mock(StudentExam.class), mock(StudentExam.class));

        ExamExecution examExecution = new ExamExecution(examSetup, studentExams);

        assertThat(examExecution.getExamSetup()).isEqualTo(examSetup);
        assertThat(examExecution.getStudentExams()).isEqualTo(studentExams);
    }

    /**
     * @verifies that ExamExecution cannot be cleared till finalized
     * @see ExamExecution#clean()
     */
    @Test
    public void clean_shouldNotClearTillFinalized() throws Exception {
        var examSetup = mock(ExamSetup.class);
        var studentExams = Arrays.asList(mock(StudentExam.class), mock(StudentExam.class));

        ExamExecution examExecution = new ExamExecution(examSetup, studentExams);

        Assertions.assertThrows(DataNotFinalizedException.class, examExecution::clean);
    }

    /**
     * @verifies that when ExamExecution is cleaned info fields return DataCleanedException
     * @see ExamExecution#clean()
     */
    @Test
    public void clean_shouldClearOnceFinalized() throws Exception {
        var examSetup = mock(ExamSetup.class);
        var studentExams = Arrays.asList(mock(StudentExam.class), mock(StudentExam.class));

        ExamExecution examExecution = new ExamExecution(examSetup, studentExams);

        examExecution.setState(ExamExecutionState.FINALIZED);
        examExecution.clean();

        Assertions.assertThrows(DataCleanedException.class, examExecution::getExamSetup);
        Assertions.assertThrows(DataCleanedException.class, examExecution::getStudentExams);
    }

    /**
     * @verifies that when clean is run state is set to CLEARED
     * @see ExamExecution#clean()
     */
    @Test
    public void clean_shouldChangeState() throws Exception {
        var examSetup = mock(ExamSetup.class);
        var studentExams = Arrays.asList(mock(StudentExam.class), mock(StudentExam.class));

        ExamExecution examExecution = new ExamExecution(examSetup, studentExams);

        examExecution.setState(ExamExecutionState.FINALIZED);
        examExecution.clean();

        assertThat(examExecution.getState()).isEqualTo(ExamExecutionState.CLEARED);
    }

    /**
     * @verifies that getCopyStudentExams returns a copy of StudentExams
     * @see ExamExecution#getCopyStudentExams()
     */
    @Test
    public void getCopyStudentExams_shouldReturnMatchingCopy() throws Exception {
        var examSetup = mock(ExamSetup.class);
        var studentExams = Arrays.asList(mock(StudentExam.class), mock(StudentExam.class));

        ExamExecution examExecution = new ExamExecution(examSetup, studentExams);

        assertThat(examExecution.getCopyStudentExams()).isEqualTo(studentExams);
    }
}
