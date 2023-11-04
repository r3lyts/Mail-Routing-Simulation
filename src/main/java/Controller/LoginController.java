package Controller;

import DAO.UserDAOImp;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.time.ZoneId;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label timeZoneLabel;
    private UserDAOImp userDAO = new UserDAOImp();


    public void initialize() {
        updateTimezoneLabel();
    }
    @FXML
    void onActionLogin(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidLogin(username, password)) {
            System.out.println("SUCCESS");
        }
        else {
            System.out.println("FALSE");
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
