package Model;

/**
 * Class for the user
 *
 * @author tylersmall
 */
public class User {

    private int userID;
    private String username;
    private String password;

    /**
     * Default constructor for the user, creates a blank user.
     */
    public User() {
        this.username = "";
        this.password = "";
    }

    /**
     * Constructor for the user where you can set the username and password
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the user id
     * @return user id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user id
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param userName
     */
    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * Gets the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
