package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentExamTest {

    private static final String VALID_CLASS_CODE = "FinalExam-agHRtP";

    /**
     * @verifies create StudentExam with valid parameters
     * @see StudentExam#StudentExam(Student, ExamID, String)
     */
    @Test
    public void StudentExam_shouldCreateStudentExamWithValidParameters() throws Exception {
        // Arrange
        var student = mock(Student.class);
        var examID = mock(ExamID.class);
        var screenshots = new ArrayList<Image>();
        var logging = new ArrayList<String>();
        var handin = new ArrayList<File>();

        // Act
        StudentExam studentExam = new StudentExam(student, examID, VALID_CLASS_CODE);

        // Assert
        assertThat(studentExam.getStudent()).isEqualTo(student);
        assertThat(studentExam.getExamID()).isEqualTo(examID);
        assertThat(studentExam.getClassCode()).isEqualTo(VALID_CLASS_CODE);
        assertThat(studentExam.getScreenshots()).isEqualTo(screenshots);
        assertThat(studentExam.getLogging()).isEqualTo(logging);
        assertThat(studentExam.getHandin()).isEqualTo(handin);

    }

    /**
     * @verifies show that logically similar courses are equal
     * @see StudentExam#StudentExam(Student, ExamID, String)
     */
    @Test
    public void StudentExam_shouldShowThatLogicallySimilarStudentExamsAreEqual() throws Exception {
        // Arrange
        var student = mock(Student.class);
        var examID = mock(ExamID.class);

        // Act
        StudentExam studentExamA = new StudentExam(student, examID, VALID_CLASS_CODE);
        StudentExam studentExamB = new StudentExam(student, examID, VALID_CLASS_CODE);

        // Assert
        assertThat(studentExamA).isEqualTo(studentExamB);
        assertThat(studentExamA).hasSameHashCodeAs(studentExamB);
    }



    /**
     * @verifies show that logically not-similar courses are not equal
     * @see StudentExam#StudentExam(Student, ExamID, String)
     */
    @Test
    public void StudentExam_shouldShowThatLogicallyNotsimilarStudentExamsAreNotEqual() throws Exception {
        // Arrange
        var student = mock(Student.class);
        var examID = mock(ExamID.class);
        var examID2 = mock(ExamID.class);

        // Act
        StudentExam studentExamA = new StudentExam(student, examID, VALID_CLASS_CODE);
        StudentExam studentExamB = new StudentExam(student, examID2, VALID_CLASS_CODE);

        // Assert
        assertThat(studentExamA).isNotEqualTo(studentExamB);

    }

    /**
     * @verifies show that logically not-similar courses are not equal
     * @see StudentExam#addScreenshot(Image)
     */
    @Test
    public void StudentExam_shouldAddScreenshotToStudentExam() throws Exception {
        var student = mock(Student.class);
        var examID = mock(ExamID.class);
        var image = mock(Image.class);

        StudentExam studentExam = new StudentExam(student, examID, VALID_CLASS_CODE);

        studentExam.addScreenshot(image);

        Assertions.assertEquals(1, studentExam.getScreenshots().size());
    }

}
