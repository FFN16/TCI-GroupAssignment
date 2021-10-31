package services.exposed.gdpr;

import model.ExamID;
import server.SimpleEFITserver;
import services.exposed.ExamNotFoundException;

public class SimpleGDPRService implements GDPRInterface {

    /**
     *@should throw ExamNotFoundException
     *@should throw examNotFinalizedException
     */
    //Leaving it here so that TestCherry recognizes @should
    public SimpleGDPRService(){

    }
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


}
