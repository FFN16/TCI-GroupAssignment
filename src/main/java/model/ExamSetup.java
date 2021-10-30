package model;

/**
 * An examsetup is related to a specific course. it is identified by the course it is related to, and the startdate.
 *
 * The number of classcodes to be generated can be set.
 * unique classcodes are needed for each needed classroom during the exam. the classcode consists of the name of the exam, followed by a '-', followed by a random string of 6 characters.
 * it is assumed the randomness creates unique codes.
 * two extra classcodes are always generated for extra safety (this means extra classcodes are always added).
 * the name of the extra classcodes are the name of the exam, followed by '-extra-', followed by a random string of 4 characters.
 * a list of all examcodes can be generated.
 *
 * an examname can be given during creation of an exam. when no name is provided, the examname is taken from the course the exam is for.
 * it can always be changed afterwards. an empty examname is not allowed, when this is entered, the name of the course is used instead.
 * it is not allowed to change the name, after the exam has started.
 *  *
 * the begintime of the exam (when it is being set), cannot be after the endtime of the exam. otherwise an IllegalDateException is thrown.
 * the reverse is true for the endtime.
 * instead of an endtime, a begintime + duration in seconds can be given, the endtime is then calculated.
 *
 * the examID of the examSetup can be returned.
 *
 * Exam materials can be added to the exam setup after creation. after the exam has started, no materials can be added.
 *
 *
 */
public class ExamSetup {
    // TODO: the appropiate methods



}
