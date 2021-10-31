package services.exposed.gdpr;

import model.ExamID;
import server.SimpleEFITserver;
import services.exposed.ExamNotFoundException;
import services.exposed.client.SimpleEFITClientService;
import services.exposed.gdpr.GDPRInterface;

public class SimpleGDPRService implements GDPRInterface {

    private SimpleEFITserver server;

    public SimpleEFITserver getServer() {
        return server;
    }

    public void setServer(SimpleEFITserver server) {
        this.server = server;
    }

    @Override
    public void removeStudentExamData(ExamID examID) throws ExamNotFoundException, ExamNotFinalizedException {

    }
    // TODO
}
