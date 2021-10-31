package model;

/**
 * a planned exam is an immutable object, meant for showing when an exam is planned.
 */
public class ExamID {
    private String examName;
    private long timeOfExamInEpochFormat;

    public ExamID(String examName,long timeOfExamInEpochFormat){
        this.examName=examName;
        this.timeOfExamInEpochFormat=timeOfExamInEpochFormat;
    }

    public String getExamName() {
        return examName;
    }

    public long getTimeOfExamInEpochFormat() {
        return timeOfExamInEpochFormat;
    }


}
