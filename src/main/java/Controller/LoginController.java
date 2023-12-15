package Controller;

import DAO.UserDAOImp;
import HelperClasses.Helper;
import HelperClasses.SessionManager;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The login view for the program.
 *
 * @author tylersmall
 */
public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label timeZoneLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label usernameLabel;

    private UserDAOImp userDAO = new UserDAOImp();

    Locale defaultLocale = Locale.getDefault();
    ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", defaultLocale);

    public void initialize() {
        updateTimezoneLabel();
        usernameLabel.setText(bundle.getString("login.username"));
        passwordLabel.setText(bundle.getString("login.password"));
        loginButton.setText(bundle.getString("login.login"));
    }

    /**
     * Logs the user in or throws and error message if username/password is incorrect.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionLogin(ActionEvent event) throws SQLException, IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();


        if (isValidLogin(username, password)) {
            writeLoginActivity(username, true);
            Helper.nextView("/Model/CustAndAppt.fxml", event);
        }
        else {
            writeLoginActivity(username, false);
            Helper.displayAlert(bundle.getString("login.loginErrorTitle"), bundle.getString("login.loginErrorHeader"), bundle.getString("login.loginErrorMessage"), Alert.AlertType.ERROR);
        }
    }

    /**
     * Logs the user activity to a text file
     * @param username
     * @param isSuccess
     */
    public void writeLoginActivity(String username, boolean isSuccess) {
        String fileName = "login_activity.txt";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (FileWriter fileWriter = new FileWriter(fileName, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.printf("User: %s, Login Time: %s, Success: %b%n",
                    username, now.format(formatter), isSuccess);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception as appropriate for your application
        }
    }

    /**
     * Updates the timezone label to the user's time zone.
     */
    public void updateTimezoneLabel() {
        ZoneId systemDefaultZoneId = ZoneId.systemDefault();
        timeZoneLabel.setText(systemDefaultZoneId.toString());
    }

    /**
     * Checks to see if the login is valid.
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean isValidLogin(String username, String password) throws SQLException {
        User user = userDAO.findUserByUsername(username);

         if (user != null && user.getPassword().equals(password)) {
             SessionManager.setCurrentUser(user);
             return true;
         }

         return false;
    }

}
