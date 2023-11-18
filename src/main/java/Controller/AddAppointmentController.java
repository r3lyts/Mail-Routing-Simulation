package Controller;

import DAO.ContactDAO;
import DAO.ContactDAOImp;
import DAO.UserDAO;
import DAO.UserDAOImp;
import HelperClasses.Helper;
import Model.Contact;
import Model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private ComboBox<?> customerIDComboBox;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> endTimeComboBox;

    @FXML
    private TextField locationTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<String> startTimeComboBox;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField typeTextField;

    @FXML
    private ComboBox<Integer> userIDComboBox;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Helper.nextView("/Model/CustAndAppt.fxml", event);
    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactDAO contactDAO = new ContactDAOImp();
        UserDAO userDAO = new UserDAOImp();
        List<String> contactNames = new ArrayList<>();
        List<Integer> userIDs = new ArrayList<>();
        List<Contact> contacts;
        List<User> users;
        try {
            contacts = contactDAO.findAll();
            users = userDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Contact contact : contacts) {
            contactNames.add(contact.getContactName());
        }
        for (User user : users) {
            userIDs.add(user.getUserID());
        }

        startTimeComboBox.setItems(FXCollections.observableArrayList(Helper.generateTimeSlots()));
        endTimeComboBox.setItems(FXCollections.observableArrayList(Helper.generateTimeSlots()));
        contactComboBox.setItems(FXCollections.observableArrayList(contactNames));
        userIDComboBox.setItems(FXCollections.observableArrayList(userIDs));
    }
}
