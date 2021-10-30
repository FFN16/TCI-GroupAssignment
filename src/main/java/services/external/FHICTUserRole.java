package services.external;

import java.util.UUID;

/**
 * describes the role a user can have. It corresponds to FHICT defined roles.
 */
public interface FHICTUserRole {

    /**
     * returns the name of the userrole, as defined by FHICT
     * @return the name of the userrole.
     */
    String getName();

    /**
     *
     * @return a unique ID, identifying a role
     */
    UUID getRoleID();

}
