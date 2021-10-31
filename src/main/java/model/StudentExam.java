package model;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * contains the student, the exam he/she took, classcode used and all screenshots, logging, uploaded examwork of a student which took an exam.
 *
 * methods need to be provided which enable adding screenshots, running processes on the student's computer, and several properties which
 * are sent by the EFIT client.
 *
 */
public class StudentExam {

    private Student student;
    private ExamID examID;
    private String classCode;
    private ArrayList<Image> screenshots;
    private ArrayList<String> logging;
    private ArrayList<File> handin;


    // Constructors

    public StudentExam(Student student, ExamID examID, String classCode) {
        this.student = student;
        this.examID = examID;
        this.classCode = classCode;
        this.screenshots = new ArrayList<>();
        this.logging = new ArrayList<>();
        this.handin = new ArrayList<>();
    }

    // Methods

    public void addScreenshot(Image image) {
        this.screenshots.add(image);
    }

    // Hash & isEqual

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentExam that = (StudentExam) o;
        return student.equals(that.student) && examID.equals(that.examID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, examID);
    }


    // Getters and Setters

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ExamID getExamID() {
        return examID;
    }

    public void setExamID(ExamID examID) {
        this.examID = examID;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public ArrayList<Image> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(ArrayList<Image> screenshots) {
        this.screenshots = screenshots;
    }

    public ArrayList<String> getLogging() {
        return logging;
    }

    public void setLogging(ArrayList<String> logging) {
        this.logging = logging;
    }

    public ArrayList<File> getHandin() {
        return handin;
    }

    public void setHandin(ArrayList<File> handin) {
        this.handin = handin;
    }

}
