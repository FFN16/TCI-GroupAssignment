package services.exposed.client;

import model.ExamID;
import model.ExamToken;
import model.Student;
import server.SimpleEFITserver;
import services.exposed.ExamNotFoundException;
import services.exposed.client.EFITClientInterface;
import services.exposed.invigilator.Alert;
import services.external.UserToken;
import services.internal.InvalidTokenException;

import java.util.List;
import java.util.Set;

public class SimpleEFITClientService implements EFITClientInterface {

    private SimpleEFITserver server;

    public SimpleEFITserver getServer() {
        return server;
    }

    public void setServer(SimpleEFITserver server) {
        this.server = server;
    }

    @Override
    public void signUpForExam(Student student, String examName, Long examDateAsEpochTime) throws IllegalArgumentException, SignupNotAllowedException, ExamNotFoundException, ExamAmountException {

    }

    @Override
    public Set<ExamID> getRegisteredExams(Student student) {
        return null;
    }

    @Override
    public ExamToken loginForExam(UserToken token, ExamID exam, String examClasscode) throws InvalidTokenException, IllegalArgumentException, ExamNotFoundException {
        return null;
    }

    @Override
    public Object getExamMaterial(ExamToken examToken) {
        return null;
    }

    @Override
    public long sendKeepAlive(ExamToken examToken) throws InvalidTokenException {
        return 0;
    }

    @Override
    public void sendScreenShot(ExamToken examToken, Object screenshot) throws InvalidTokenException {

    }

    @Override
    public void sendListOfRunningProcesses(ExamToken examToken, List<String> runningProcesses) throws InvalidTokenException {

    }

    @Override
    public Alert sendProcessInfo(ExamToken examToken, String propertyKey, String propertyValue) throws InvalidTokenException {
        return null;
    }

    @Override
    public Alert uploadExamWork(ExamToken examToken, Object examWork) throws InvalidTokenException, IllegalArgumentException {
        return null;
    }

    @Override
    public Alert logoutForExam(ExamToken examToken) {
        return null;
    }
    // TODO
}
