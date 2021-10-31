package model;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//I've slightly modified the commented section below to take care of a couple of naming discrepancies/duplication ie startDate/beginTime: I see it as referring to the same field
/**
 * An examSetup is related to a specific course. it is identified by the course it is related to, and the beginTime.
 *
 * The number of classCodes to be generated can be set.
 * unique classCodes are needed for each needed classroom during the exam. the classCode consists of the name of the exam, followed by a '-', followed by a random string of 6 characters.
 * it is assumed the randomness creates unique codes.
 * two extra classCodes are always generated for extra safety (this means extra classCodes are always added).
 * the name of the extra classCodes are the name of the exam, followed by '-extra-', followed by a random string of 4 characters.
 * a list of all classCodes can be generated.
 *
 * an examName can be given during creation of an exam. when no name is provided, the examName is taken from the course the exam is for.
 * it can always be changed afterwards. an empty examName is not allowed, when this is entered, the name of the course is used instead.
 * it is not allowed to change the name, after the exam has started.
 *  *
 * the beginTime of the exam (when it is being set), cannot be after the endTime of the exam. otherwise, an IllegalDateException is thrown.
 * the reverse is true for the endTime.
 * instead of an endTime, a beginTime + duration in seconds can be given, the endTime is then calculated.
 *
 * the examID of the examSetup can be returned.
 *
 * Exam materials can be added to the exam setup after creation. after the exam has started, no materials can be added.
 *
 *
 */
public class ExamSetup {
    // TODO: the appropriate methods
    private Course course;// If examID has no examName, the name comes from the course
    private ExamID examID;// Contains information on beginTime and examName
    private String classCode;
    private String setExamName;
    private List<String> extraClassCodes = new ArrayList<>();
    private int classCodeAmount = 2;
    private long beginTime;
    private long duration;
    private long endTime;
    private List<File> extraMaterials = new ArrayList<>();

    /**@should classCode is examName followed by dash and six random characters
     * @should always create two extra classCodes
     * @should extra classCode should end in '-extra-'
     * @should examName cannot be changed after beginTime
     * @should beginTime cant be higher than endTime throws IllegalDateException
     * @should endTime cant be lower than beginTime throws IllegalDateException
     * @should endTime equals beginTime plus duration
     * @should extraMaterials cannot be added after beginTime
     * @should ExamSetup is uniquely defined by courseName and beginTime
     */

    //CONSTRUCTOR

    public ExamSetup(Course course,ExamID examId,long duration){
        this.course = course;
        this.examID = examId;
        this.duration = duration;
    }

    //GETTERS AND SETTERS

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public List<String> getExtraClassCodes() {
        return extraClassCodes;
    }

    public void setExtraClassCodes(List<String> extraClassCodes) {
        this.extraClassCodes = extraClassCodes;
    }

    public String getExamName() {
        return setExamName;
    }

    public void setExamName(String examName) {
        this.setExamName = examName;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getClassCodeAmount() {
        return classCodeAmount;
    }

    public void setClassCodeAmount(int classCodeAmount) {
        this.classCodeAmount = classCodeAmount;
    }

    public List<File> getExtraMaterials() {
        return extraMaterials;
    }

    public void setExtraMaterials(List<File> extraMaterials) {
        this.extraMaterials = extraMaterials;
    }

    //METHODS
    private void CreateClassCodes(){
        if(!(examID.getExamName()==null||examID.getExamName().length()==0)){
            //If examID has a name, use that one

            //Create random string
            byte[] array = new byte[4];
            new Random().nextBytes(array);
            String randString = new String(array, Charset.forName("UTF-8"));
        }
    }
}
