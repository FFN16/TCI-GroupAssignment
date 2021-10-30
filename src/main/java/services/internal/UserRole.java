package services.internal;

public enum UserRole {
    STUDENT("Student"), TEACHER("Teacher"), INVIGILATOR("Invigilator"), DATAMANAGER("Data Officer");


    private String roleName;

    private UserRole(String roleName) {
        this.roleName = roleName;
    }


}
