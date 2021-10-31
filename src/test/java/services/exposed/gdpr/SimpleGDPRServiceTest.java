package services.exposed.gdpr;

import model.ExamID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.SimpleEFITserver;
import services.exposed.ExamNotFoundException;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleGDPRServiceTest {

    //Dummy exam ids from server
    private static Set<ExamID> dummyIdsNotFound = new HashSet<>();
    private static Set<ExamID> dummyIdsNotFinalized = new HashSet<>();
    private static ExamID T1 = new ExamID("TEST1",1);
    private static ExamID T2 = new ExamID("TEST2",2);
    /**
     * @verifies throw ExamNotFoundException
     * @see SimpleGDPRService#SimpleGDPRService()
     */
    @BeforeAll
    public static void Setup(){
        dummyIdsNotFound.add(T1);
        dummyIdsNotFinalized.add(T2);
    }
    @Test
    public void SimpleGDPRService_shouldThrowExamNotFoundException() throws Exception {
        SimpleEFITserver efit = mock(SimpleEFITserver.class);
        SimpleGDPRService sut = new SimpleGDPRService();
        sut.setServer(efit);
        when(efit.getFinalizedExamIDs()).thenReturn(dummyIdsNotFound);

        Assertions.assertThrows(ExamNotFoundException.class, () -> {
            sut.removeStudentExamData(T2);
        });
    }

    /**
     * @verifies throw examNotFinalizedException
     * @see SimpleGDPRService#SimpleGDPRService()
     */
    @Test
    public void SimpleGDPRService_shouldThrowExamNotFinalizedException() throws Exception {
        SimpleEFITserver efit = mock(SimpleEFITserver.class);
        SimpleGDPRService sut = new SimpleGDPRService();
        sut.setServer(efit);
        when(efit.getFinalizedExamIDs()).thenReturn(dummyIdsNotFound);
        when(efit.getInProgressOrUnfinalizedExamIDs()).thenReturn(dummyIdsNotFinalized);

        Assertions.assertThrows(ExamNotFinalizedException.class, () -> {
            sut.removeStudentExamData(T2);
        });
    }
}
