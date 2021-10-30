package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private static String VALID_NAME = "John Doe";
    private static long VALID_STUDENT_NUMBER= 123456;
    private static Image VALID_IMAGE;

    @Test
    public void Student_shouldShowThatLogicallySimilarStudentsAreEqual() throws Exception {
        //Act
        Student a = new Student(VALID_NAME,VALID_STUDENT_NUMBER,VALID_IMAGE);
        Student b = new Student(VALID_NAME,VALID_STUDENT_NUMBER,VALID_IMAGE);

        //Assert
        assertThat(a).isEqualTo(b);
        assertThat(a).hasSameHashCodeAs(b);

    }

    /**
     * @verifies show that logically not-similar students are not equal
     * @see Student#Student(String, long, java.awt.Image)
     */
    @Test
    public void Student_shouldShowThatLogicallyNotsimilarStudentsAreNotEqual() throws Exception {
        //Act
        Student a = new Student(VALID_NAME,VALID_STUDENT_NUMBER,VALID_IMAGE);
        Student b = new Student(VALID_NAME,654321,VALID_IMAGE);
        Student c = new Student("Jane Doe",VALID_STUDENT_NUMBER,VALID_IMAGE);

        //Assert
        assertThat(a).isNotEqualTo(b);
        assertThat(a).isNotEqualTo(c);
        assertThat(b).isNotEqualTo(c);
    }

    /**
     * @verifies only sign up before exam starts
     * @see Student#Student(String, long, java.awt.Image)
     */
    @Test
    public void Student_shouldOnlySignUpBeforeExamStarts() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}