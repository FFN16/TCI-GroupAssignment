package services.exposed.invigilator;

import model.Student;
import server.SimpleEFITserver;
import services.exposed.client.SimpleEFITClientService;

import java.util.Set;

public class SimpleInvigilatorService implements InvigilatorInterface {

    private SimpleEFITserver server;

    public SimpleEFITserver getServer() {
        return server;
    }

    public void setServer(SimpleEFITserver server) {
        this.server = server;
    }

    @Override
    public Set<Student> getListOfEnteredStudents() {
        return null;
    }

    @Override
    public void addExtraTime(Student student, long amountOfSeconds) {

    }

    @Override
    public void addExtraTime(String classCode, long amountOfSeconds) {

    }

    @Override
    public void changeExamStartTime(long newStartTimeInEpochFormat) {

    }

    @Override
    public void changeExamEndTime(long newEndTimeInEpochFormat) {

    }

    @Override
    public Object showScreenShot(Student student, int screenshotsAgo) {
        return null;
    }

    @Override
    public void resetWarningOnStudent(Student student, Warning warning) {

    }
    // TODO
}
