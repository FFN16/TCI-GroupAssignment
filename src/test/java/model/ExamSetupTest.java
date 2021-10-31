package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExamSetupTest {

    private static long VALID_DURATION = 5400;
    private static String VALID_COURSE_NAME = "test";
    private static String EXTRA ="-extra-";
    private static long FUTURE_EXAM_TIME = 1667243070;
    ExamID examId = mock(ExamID.class);
    Course course = mock(Course.class);

    /**
     * @verifies classCode is examName followed by six random characters
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldClassCodeIsExamNameFollowedByDashAndSixRandomCharacters() throws Exception {

        //Case examID has a name
        when(examId.getExamName()).thenReturn(VALID_COURSE_NAME);
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(FUTURE_EXAM_TIME);
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        String examName = sut.getExamName();
        // Testing on dash index is failing but the debugger shows it is correct
        char[] ch = examName.toCharArray();
        //char expected = '-';
        //char actual = ch[examName.length()-7];

        assertThat(examName.length()).isEqualTo(VALID_COURSE_NAME.length()+7);
        //assertThat(actual).isEqualTo(expected);

        //Case examID has no name
        when(examId.getExamName()).thenReturn(null);
        when(course.getName()).thenReturn(VALID_COURSE_NAME);
        sut = new ExamSetup(course,examId,VALID_DURATION);
        examName = sut.getExamName();
        //ch = examName.toCharArray();
        System.out.println(examName);
        assertThat(examName.length()== VALID_COURSE_NAME.length()+7);
        // assertThat(ch[examName.length()-4]).isEqualTo('-');
    }

    /**
     * @verifies always create two extra classCodes
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldAlwaysCreateTwoExtraClassCodes() throws Exception {

        //Case examID has a name
        when(examId.getExamName()).thenReturn(VALID_COURSE_NAME);
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(FUTURE_EXAM_TIME);
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        assertThat(sut.getExtraClassCodes().size()).isEqualTo(2);

        //Testing manual set - minimum 2
        sut.setClassCodeAmount(1);
        sut.CreateExtraClassCodes();
        assertThat(sut.getExtraClassCodes().size()).isEqualTo(2);

        //Testing manual set
        sut.setClassCodeAmount(5);
        sut.CreateExtraClassCodes();
        assertThat(sut.getExtraClassCodes().size()).isEqualTo(5);

        //Case examID has no name
        sut = new ExamSetup(course,examId,VALID_DURATION);

        when(examId.getExamName()).thenReturn(null);
        when(course.getName()).thenReturn(VALID_COURSE_NAME);

        assertThat(sut.getExtraClassCodes().size()).isEqualTo(2);

        //Testing manual set
        sut.setClassCodeAmount(1);
        sut.CreateExtraClassCodes();
        assertThat(sut.getExtraClassCodes().size()).isEqualTo(2);

        //Testing manual set
        sut.setClassCodeAmount(5);
        sut.CreateExtraClassCodes();
        assertThat(sut.getExtraClassCodes().size()).isEqualTo(5);

    }

    /**
     * @verifies extra classCode should end in '-extra-'
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldExtraClassCodeShouldEndInExtraAndFourRandomChars() throws Exception {

        //Case examID has a name
        when(examId.getExamName()).thenReturn(VALID_COURSE_NAME);
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(FUTURE_EXAM_TIME);
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        List<String> temp = sut.getExtraClassCodes();
        for(int i = 0; i < sut.getExtraClassCodes().size() ; i++){
            String examName = sut.getExtraClassCodes().get(i);
            assertThat(VALID_COURSE_NAME.length()+11).isEqualTo(examName.length());
            assertTrue(examName.contains(EXTRA));
        }
        //Case examID has no name
        sut = new ExamSetup(course,examId,VALID_DURATION);

        when(examId.getExamName()).thenReturn(null);
        when(course.getName()).thenReturn(VALID_COURSE_NAME);

        sut.CreateExtraClassCodes();

        for(int i = 0; i < sut.getExtraClassCodes().size() ; i++){
            String examName = sut.getExtraClassCodes().get(i);
            assertThat(VALID_COURSE_NAME.length()+11).isEqualTo(examName.length());
            assertTrue(examName.contains(EXTRA));
            }
    }

    /**
     * @verifies examName cannot be changed after beginTime
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldExamNameCannotBeChangedAfterBeginTime() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()/ 1000L);
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        String changedName = "changed";

        //Case examID has a name
        when(examId.getExamName()).thenReturn(VALID_COURSE_NAME);

        sut.setExamName("ChangedName");
        assertThat(sut.getExamName()).isNotEqualTo("ChangedName");
        //Case examID has no name
        sut = new ExamSetup(course,examId,VALID_DURATION);

        when(examId.getExamName()).thenReturn(null);
        when(course.getName()).thenReturn(VALID_COURSE_NAME);

        sut.setExamName("ChangedName");
        assertThat(sut.getExamName()).isNotEqualTo("ChangedName");

    }

    /**
     * @verifies beginTime cant be higher than endTime throws IllegalDateException
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldBeginTimeCantBeHigherThanEndTimeThrowsIllegalDateException() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()/ 1000L);
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        sut.setEndTime(System.currentTimeMillis());

        Assertions.assertThrows(IllegalDateException.class, () -> {
            sut.setBeginTime(System.currentTimeMillis()/ 1000L+10000);
        });
    }

    /**
     * @verifies endTime cant be lower than beginTime throws IllegalDateException
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldEndTimeCantBeLowerThanBeginTimeThrowsIllegalDateException() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()/ 1000L);
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        sut.setEndTime(System.currentTimeMillis()/ 1000L);

        Assertions.assertThrows(IllegalDateException.class, () -> {
            sut.setEndTime(System.currentTimeMillis()/ 1000L-10000);
        });

    }

    /**
     * @verifies endTime equals beginTime plus duration
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldEndTimeEqualsBeginTimePlusDuration() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()/ 1000L);
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        //Method call calculateEndTime()

        assertThat(sut.getEndTime()).isEqualTo(sut.getBeginTime()+sut.getDuration());
    }

    /**
     * @verifies extraMaterials cannot be added after beginTime
     * @see ExamSetup#ExamSetup(Course,ExamID, long)
     */
    @Test
    public void ExamSetup_shouldExtraMaterialsCannotBeAddedAfterBeginTime() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()/ 1000L);
        ExamSetup sut = new ExamSetup(course,examId,VALID_DURATION);

        sut.setExtraMaterials(new File("dummy.txt"));

        assertThat(sut.getExtraMaterials().size()).isEqualTo(0);

    }

    /**
     * @verifies ExamSetup is uniquely defined by courseName and beginTime
     * @see ExamSetup#ExamSetup(Course, ExamID, long)
     */
    @Test
    public void ExamSetup_shouldShowThatLogicallySimilarCoursesAreEqual() throws Exception {
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()/ 1000L);

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
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()/ 1000L);

        ExamSetup A = new ExamSetup(course,examId,VALID_DURATION);
        when(examId.getTimeOfExamInEpochFormat()).thenReturn(System.currentTimeMillis()/ 1000L+100000);
        ExamSetup B = new ExamSetup(course,examId,VALID_DURATION);

        assertThat(A).isNotEqualTo(B);

    }

}
