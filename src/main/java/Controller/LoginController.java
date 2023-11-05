package Controller;

import DAO.UserDAOImp;
import Helper.Helper;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

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
    @FXML
    void onActionLogin(ActionEvent event) throws SQLException, IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidLogin(username, password)) {
            Helper.nextView("/Model/CustAndAppt.fxml", event);
        }
        else {
            Helper.displayAlert(bundle.getString("login.loginErrorTitle"), bundle.getString("login.loginErrorHeader"), bundle.getString("login.loginErrorMessage"), Alert.AlertType.ERROR);
        }
    }

    public void updateTimezoneLabel() {
        ZoneId systemDefaultZoneId = ZoneId.systemDefault();
        timeZoneLabel.setText(systemDefaultZoneId.toString());
    }

    public boolean isValidLogin(String username, String password) throws SQLException {
        User user = userDAO.findUserByUsername(username);
         if (user != null) {
             return user.getPassword().equals(password);
         }

         return false;
    }

}
