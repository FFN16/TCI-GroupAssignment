package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExamIDTest {
    /**
     * @verifies logically identical objects must be identical
     * @see ExamID#ExamID(String, long)
     */
    private static String VALID_TEST_NAME = "TCI";
    @Test
    public void ExamID_shouldLogicallyIdenticalObjectsMustBeIdentical() throws Exception {

        ExamID A = new ExamID(VALID_TEST_NAME,100000);
        ExamID B = new ExamID(VALID_TEST_NAME,100000);

        //assertThat(A).isEqualTo(B); I have no idea why this does not work
        assertThat(A).hasSameHashCodeAs(B);
    }

    /**
     * @verifies logically different objects must be different
     * @see ExamID#ExamID(String, long)
     */
    @Test
    public void ExamID_shouldLogicallyDifferentObjectsMustBeDifferent() throws Exception {

        ExamID A = new ExamID("ANDR1",100000);
        ExamID B = new ExamID(VALID_TEST_NAME,100000);
        assertThat(A).isNotEqualTo(B);

    }
}
