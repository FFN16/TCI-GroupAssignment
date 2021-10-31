package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExamSetupTest {

    private static long VALID_DURATION = 5400;
    private static String VALID_COURSE_NAME = "Testing and Continuous Integration";
    ExamID examId = mock(ExamID.class);
    Course course = mock(Course.class);
    /**
     * @verifies classCode is examName followed by six random characters
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldClassCodeIsExamNameFollowedByDashAndSixRandomCharacters() throws Exception {

        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        //Case examID has a name
        when(examId.getExamName()).thenReturn(VALID_COURSE_NAME);
        String examName = sut.getExamName();
        char[] ch = examName.toCharArray();
        assertThat(examName.length()== VALID_COURSE_NAME.length()+7);
        assertThat(ch[examName.length()-6]=='-');

        //Case examID has no name
        when(examId.getExamName()).thenReturn(null);
        when(course.getName()).thenReturn(VALID_COURSE_NAME);
        sut = new ExamSetup(course,examId,VALID_DURATION);
        examName = sut.getExamName();
        ch = examName.toCharArray();

        assertThat(examName.length()== VALID_COURSE_NAME.length()+7);
        assertThat(ch[examName.length()-6]=='-');

    }

    /**
     * @verifies always create two extra classCodes
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldAlwaysCreateTwoExtraClassCodes() throws Exception {
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        //Case examID has a name
        when(examId.getExamName()).thenReturn(VALID_COURSE_NAME);
        //Method call
        assertThat(sut.getExtraClassCodes().size() == 2);

        //Case examID has no name
        sut = new ExamSetup(course,examId,VALID_DURATION);

        when(examId.getExamName()).thenReturn(null);
        when(course.getName()).thenReturn(VALID_COURSE_NAME);
        //Method call
        assertThat(sut.getExtraClassCodes().size() == 2);

    }

    /**
     * @verifies extra classCode should end in '-extra-'
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldExtraClassCodeShouldEndInExtra() throws Exception {
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        //Case examID has a name

        when(examId.getExamName()).thenReturn(VALID_COURSE_NAME);
        //Method call
        for(int i = 0; i < sut.getExtraClassCodes().size() ; i++){
            String  examName = sut.getExtraClassCodes().get(i);
            char[] ch = examName.toCharArray();
            String temp = "";
            for(int j = 0; j < 7; j++){
                temp += ch[VALID_COURSE_NAME.length()+j];
            }

            assertThat(temp == "-extra-");
        }

        //Case examID has no name
        sut = new ExamSetup(course,examId,VALID_DURATION);

        when(examId.getExamName()).thenReturn(null);
        when(course.getName()).thenReturn(VALID_COURSE_NAME);

        //method call
        for(int i = 0; i < sut.getExtraClassCodes().size() ; i++){
            String  examName = sut.getExtraClassCodes().get(i);
            char[] ch = examName.toCharArray();
            String temp = "";
            for(int j = 0; j < 7; j++){
                temp += ch[VALID_COURSE_NAME.length()+j];
            }

            assertThat(temp == "-extra-");
        }
    }

    /**
     * @verifies examName cannot be changed after beginTime
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldExamNameCannotBeChangedAfterBeginTime() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis());
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        String changedName = "changed";

        //Case examID has a name
        when(examId.getExamName()).thenReturn(VALID_COURSE_NAME);

        sut.setExamName("ChangedName");
        assertThat(sut.getExamName()!="ChangedName");
        //Case examID has no name
        sut = new ExamSetup(course,examId,VALID_DURATION);

        when(examId.getExamName()).thenReturn(null);
        when(course.getName()).thenReturn(VALID_COURSE_NAME);

        sut.setExamName("ChangedName");
        assertThat(sut.getExamName()!="ChangedName");

    }

    /**
     * @verifies beginTime cant be higher than endTime throws IllegalDateException
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldBeginTimeCantBeHigherThanEndTimeThrowsIllegalDateException() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis());
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        sut.setEndTime(System.currentTimeMillis());

        Assertions.assertThrows(IllegalDateException.class, () -> {
            sut.setBeginTime(System.currentTimeMillis()+10000);
        });
    }

    /**
     * @verifies endTime cant be lower than beginTime throws IllegalDateException
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldEndTimeCantBeLowerThanBeginTimeThrowsIllegalDateException() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis());
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        sut.setEndTime(System.currentTimeMillis());

        Assertions.assertThrows(IllegalDateException.class, () -> {
            sut.setEndTime(System.currentTimeMillis()-10000);
        });
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies endTime equals beginTime plus duration
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldEndTimeEqualsBeginTimePlusDuration() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis());
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        //Method call calculateEndTime()

        assertThat(sut.getEndTime()==sut.getBeginTime()+sut.getDuration());
    }

    /**
     * @verifies extraMaterials cannot be added after beginTime
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldExtraMaterialsCannotBeAddedAfterBeginTime() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis());
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        sut.getExtraMaterials().add(new File("dummy.txt"));

        assertThat(sut.getExtraMaterials().size()==0);

    }

    /**
     * @verifies ExamSetup is uniquely defined by courseName and beginTime
     * @see ExamSetup#ExamSetup(Course, ExamID, long)
     */
    @Test
    public void ExamSetup_shouldShowThatLogicallySimilarCoursesAreEqual() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis());

        ExamSetup A = new ExamSetup(course,examId,VALID_DURATION);
        ExamSetup B = new ExamSetup(course,examId,VALID_DURATION);

        assertThat(A).isEqualTo(B);
        assertThat(A).hasSameHashCodeAs(B);

    }

    /**
     * @verifies ExamSetup is uniquely defined by courseName and beginTime
     * @see ExamSetup#ExamSetup(Course, ExamID, long)
     */
    @Test
    public void ExamSetup_shouldShowThatLogicallyNotSimilarCoursesAreNotEqual() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis());

        ExamSetup A = new ExamSetup(course,examId,VALID_DURATION);
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()+100000);
        ExamSetup B = new ExamSetup(course,examId,VALID_DURATION);

        assertThat(A).isNotEqualTo(B);

    }
}
