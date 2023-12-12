package HelperClasses;

import Model.User;

/**
 * Class for managing the user session
 *
 * @author tylersmall
 */
public class SessionManager {

    private static User currentUser;

    /**
     * Gets the current user who is logged in
     * @return current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user
     * @param user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
