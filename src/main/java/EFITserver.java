import execution.ExamExecution;
import services.exposed.client.EFITClientInterface;
import services.exposed.gdpr.GDPRInterface;
import services.exposed.invigilator.InvigilatorInterface;
import services.exposed.teacher.TeacherInterface;
import model.ExamSetup;
import model.Student;
import services.internal.ExamProcessInterface;

import java.util.Map;
import java.util.Set;

/**
 * the EFITserver class holds all information on exams, registered students for exams, exam results and logging
 *
 */
public abstract class EFITserver implements ExamProcessInterface {
    // needed data before exams start
    private Set<ExamSetup> setupExams;
    private Map<Student,Set<ExamSetup>> signedUpExamsPerStudent;
    // needed data during exams
    private Set<ExamExecution> currentlyRunningExams;
    // needed data after exams
    private Set<ExamExecution> finishedExams;
    //
    // needed external interfaces
    private EFITClientInterface efitClientInterface;
    private TeacherInterface teacherInterface;
    private InvigilatorInterface invigilatorInterface;
    private GDPRInterface gdprInterface;
    //

    /**
     * Constructor.
     * @param efitClientInterface
     * @param teacherInterface
     * @param invigilatorInterface
     * @param gdprInterface
     */
    public EFITserver(EFITClientInterface efitClientInterface, TeacherInterface teacherInterface, InvigilatorInterface invigilatorInterface, GDPRInterface gdprInterface) {
        this.efitClientInterface = efitClientInterface;
        this.teacherInterface = teacherInterface;
        this.invigilatorInterface = invigilatorInterface;
        this.gdprInterface = gdprInterface;
    }

    public EFITClientInterface getEfitClientInterface() {
        return efitClientInterface;
    }

    public TeacherInterface getTeacherInterface() {
        return teacherInterface;
    }

    public InvigilatorInterface getInvigilatorInterface() {
        return invigilatorInterface;
    }

    public GDPRInterface getGdprInterface() {
        return gdprInterface;
    }
}
