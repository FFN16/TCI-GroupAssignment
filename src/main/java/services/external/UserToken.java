package services.external;

import java.util.Set;

/**
 * this describes the usertoken which is received from the external authentication server.
 */
public interface UserToken {

    /**
     *
     * @return the string of the user belonging to this token
     */
    String getUserName();

    /**
     *
     * @return the set of userRoles belonging to this token. The returned set is empty when no roles are present for this token.
     */
    Set<FHICTUserRole> getUserRoles();


}
