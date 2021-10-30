package services.external;

import model.Course;
import model.Student;

import java.util.Set;

public interface FHICTservice {



    UserToken login() throws LoginFailedException;

    boolean isAllowedToSignup(Student student, Course course);

    Set<String> getListOfAllCourseCodes();

    /**
     * returns info from a student
     * @param token token of logged in student.
     * @return student info of logged in student
     */
    Student getStudent(UserToken token);



}
