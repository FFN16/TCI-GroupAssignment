package model;

import java.util.Objects;

/**
 * A model.Course is an IMMUTABLE object with a given name, code and ec.
 * It's uniquely identified by it's course code (a string).
 * the name of the course (a string) should not be null
 * code should be 3 or 4 characters long and is in upper case
 * the number of EC's (ec) is a positive whole number.
 * If above parametes are wrong, an IllegalArgumentException should be thrown during construction.
 */
public class Course {

    private String name; // the name of the course, e.g., Testing and Continuous Integration
    private String code; // he code of the course, e.g., TCI.
    private int ec; //  are the ECs of the course, e.g., 3

    /**
     * Creates a model.Course object with given name, code and ec.
     *
     * @param name is the name of the course, e.g., Testing and Continuous Integration
     * @param code is the code of the course, e.g., TCI. Code must be three or four upper case characters.
     * @param ec   are the ECs of the course, e.g., 3. ec must be a positive number.
     * @throws IllegalArgumentException If code does not have length 3 or 4, code is not in upper case, or ec is not a positive number.
     * @should create course with valid parameters
     * @should show that logically similar courses are equal
     * @should show that logically not-similar courses are not equal
     * @should throw illegalargument exception if any of the input parameters is null
     * @should throw illegalargument exception if code has invalid format
     */
    public Course(String name, String code, int ec) throws IllegalArgumentException {
        if (code == null) {
            throw new IllegalArgumentException("Code must be all upper case letters.");
        }
        if (code.length() < 3 || code.length() > 4) {
            throw new IllegalArgumentException("Code must be three (3) or four (4) letters.");
        }

        if (!code.toUpperCase().equals(code)) {
            throw new IllegalArgumentException("Code must be all upper case letters.");
        }

        if (ec <= 0) {
            throw new IllegalArgumentException("EC must be a positive integer.");
        }

        if (name == null) {
            throw new IllegalArgumentException("name should not be null");
        }

        this.name = name;
        this.code = code;
        this.ec = ec;  // demo mocking: this could have a typo: this.ec += ec;
    }

    /******************************************************
     *
     *   GETTERS/SETTERS CODE IS GENERATED BY INTELLIJ,
     *   AND UNTOUCHED BY PEOPLE.
     *   THE BEHAVIOUR OF THIS CODE DOES NOT HAVE TO BE TESTED.
     *
     ********************************************************/

    public int getEc() {
        return ec;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }


    /******************************************************
     *
     *   OTHER CODE GENERATED BY INTELLIJ,
     *   BUT BASED ON INPUT OF A PROGRAMMER.
     *   THE BEHAVIOUR OF THIS CODE HAS TO BE TESTED.
     *   (but not present to keep demo code short)
     *
     ********************************************************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
/*
 *
 * Note: An IMMUTABLE object is mostly used for transfering data between layers in your software
 * , so it only has a constructor which sets ALL attributes, and it has getters (generated by the IDE)
 * for all of the attributes.
 * (this is a so called DTO: Data Transfer Object)
 *
 *
 */



