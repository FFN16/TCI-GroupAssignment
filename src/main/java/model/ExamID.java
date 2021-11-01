package model;

import java.util.Objects;

/**
 * a planned exam is an immutable object, meant for showing when an exam is planned.
 */
public class ExamID {

    private String examName;
    private long timeOfExamInEpochFormat;
    /**
     * @should logically identical objects must be identical
     * @should logically different objects must be different
     */

    public ExamID(String examName, long timeOfExamInEpochFormat){
        this.examName=examName;
        this.timeOfExamInEpochFormat=timeOfExamInEpochFormat;
    }

    public String getExamName() {
        return examName;
    }

    public long getTimeOfExamInEpochFormat() {
        return timeOfExamInEpochFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamID examID = (ExamID) o;
        return examName.equals(examID.examName);
    }

    @Override
    public int hashCode() {
        return examName.hashCode() + Objects.hash(timeOfExamInEpochFormat) * 31 ;
    }


}
