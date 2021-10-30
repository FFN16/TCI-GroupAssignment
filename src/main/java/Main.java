import services.exposed.client.EFITClientInterface;
import services.exposed.client.SimpleEFITClientService;
import services.exposed.gdpr.GDPRInterface;
import services.exposed.gdpr.SimpleGDPRService;
import services.exposed.invigilator.InvigilatorInterface;
import services.exposed.invigilator.SimpleInvigilatorService;
import services.exposed.teacher.SimpleTeacherService;
import services.exposed.teacher.TeacherInterface;

/**
 * main class for starting EFIT server
 */
public class Main {
    private static EFITserver server;

    public static void main(String[] args) {
        // create external interface implementations
        EFITClientInterface efitClientInterface = new SimpleEFITClientService();
        TeacherInterface teacherInterface = new SimpleTeacherService();
        InvigilatorInterface invigilatorInterface = new SimpleInvigilatorService();
        GDPRInterface gdprInterface = new SimpleGDPRService();
        //
        // create EFIT server object
        // TODO server = ...
        //
    }

    public static EFITserver getServer() {
        return server;
    }
}
