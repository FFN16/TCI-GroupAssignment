package model;

import java.awt.*;
import java.util.Objects;

/**
 * student is an immutable object. it's used for passing around information on students.
 * it is uniquely identified by the studentnumber and name combination.
 */

public class Student {
    private String name;
    private long studentnumber;
    private Image photo;

    /**
    * @should only sign up before exam starts
    * @should show that logically similar students are equal
    * @should show that logically not-similar students are not equal
    */
    //assuming that validity of student number and photo was verified upon login

    //Constructor
    public Student(String name,long studentnumber,Image photo){
        this.name=name;
        this.studentnumber= studentnumber;
        this.photo = photo;
    }
    //Getters
    public String getName() {
        return name;
    }

    public long getStudentnumber() {
        return studentnumber;
    }

    public Image getPhoto() {
        return photo;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Student)) return false;
        Student stud = (Student) obj;
        
        return name.equals(stud.name) && Objects.equals(studentnumber,stud.studentnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentnumber); //Here assuming that uniqueness of student number is already guaranteed
    }



}
