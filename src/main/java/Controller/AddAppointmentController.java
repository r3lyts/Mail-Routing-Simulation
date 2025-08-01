package Controller;

import DAO.*;
import HelperClasses.Helper;
import HelperClasses.SessionManager;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Appointment controller for the add appointment menu in the application.
 *
 * @author tylersmall
 */
public class AddAppointmentController implements Initializable {

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private ComboBox<Integer> customerIDComboBox;

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

    /**
     * Takes user back to the main menu
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Helper.nextView("/Model/CustAndAppt.fxml", event);
    }


    /**
     * Saves the added appointment and adds it to the database.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        Appointment appointment = new Appointment();
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        ContactDAO contactDAO = new ContactDAOImp();


        if (isValidAppointment(appointment)) {
            LocalDateTime startLocalTime = Helper.combineDateAndTime(startDatePicker.getValue(), startTimeComboBox.getValue());
            LocalDateTime endLocalTime = Helper.combineDateAndTime(endDatePicker.getValue(), endTimeComboBox.getValue());

            appointment.setTitle(titleTextField.getText());
            appointment.setType(typeTextField.getText());
            appointment.setDescription(descriptionTextField.getText());
            appointment.setLocation(locationTextField.getText());
            appointment.setStartTime(Helper.convertLocalToUTC(startLocalTime));
            appointment.setEndTime(Helper.convertLocalToUTC(endLocalTime));
            appointment.setCreateDate(Instant.now());
            appointment.setCreatedBy(SessionManager.getCurrentUser().getUsername());
            appointment.setLastUpdate(Instant.now());
            appointment.setLastUpdatedBy(SessionManager.getCurrentUser().getUsername());
            appointment.setUserID(userIDComboBox.getValue());
            appointment.setCustomerID(customerIDComboBox.getValue());
            appointment.setContactID(contactDAO.getContactIDbyName(contactComboBox.getValue()));

            appointmentDAO.addAppointment(appointment);
            Helper.nextView("/Model/CustAndAppt.fxml", event);


        }

    }

    /**
     * Checks to see if the appointment is valid.
     * @param appointment
     * @return boolean
     * @throws SQLException
     */
    public boolean isValidAppointment(Appointment appointment) throws SQLException {
        //Checks Text fields to see if they are empty
        if (typeTextField.getText().isEmpty() || titleTextField.getText().isEmpty() || locationTextField.getText().isEmpty()
            || descriptionTextField.getText().isEmpty()) {
            Helper.displayAlert("Blank Text Field", "Empty Text Field", "Please ensure all text fields have values", Alert.AlertType.ERROR);
            return false;
        }
        //Checks combo boxes for blank values
        if (contactComboBox.getValue() == null || customerIDComboBox.getValue() == null || userIDComboBox.getValue() == null) {
            Helper.displayAlert("Blank Combo Box", "Empty Combo Box", "Please ensure all combo boxes have a selected value", Alert.AlertType.ERROR);
            return false;
        }

        //Checks date pickers for blank values
        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null || endTimeComboBox.getValue() == null || startTimeComboBox.getValue() == null) {
            Helper.displayAlert("Blank Date Picker", "Empty Date Picker", "Please ensure a date has been picked for the start and end times", Alert.AlertType.ERROR);
            return false;
        }
        else {
            LocalDateTime startLocalTime = Helper.combineDateAndTime(startDatePicker.getValue(), startTimeComboBox.getValue());
            LocalDateTime endLocalTime = Helper.combineDateAndTime(endDatePicker.getValue(), endTimeComboBox.getValue());

            if (!(isValidSchedulingTime(startLocalTime, endLocalTime))) {
                return false;
            }
        }


        return true;
    }

    /**
     * Checks to see if the scheduling time for the appointment is valid and that the customer is not scheduled with overlapping appointments.
     * @param startTime
     * @param endTime
     * @return boolean
     * @throws SQLException
     */
    public boolean isValidSchedulingTime(LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        ContactDAO contactDAO = new ContactDAOImp();
        List<Appointment> appointments = appointmentDAO.findAll();

        boolean sameContact = false;
        //checks if appointments is before 8am or after 10pm
        if (isEasternTimeWithinRange(startTime) || isEasternTimeWithinRange(endTime)) {
            Helper.displayAlert("Scheduling Conflict", "Time is outside of business hours", "Please schedule a time between 8:00am to 10:00pm ET", Alert.AlertType.ERROR);
            return false;
        }
        //makes sure start time if before end time
        if (startTime.isAfter(endTime)) {
            Helper.displayAlert("Scheduling Conflict", "Start time should begin before End Time", "Please schedule a start time that is before the end time", Alert.AlertType.ERROR);
            return false;
        }

        //checks contacts for simultaneous appointments
        if (customerIDComboBox != null) {
            for (Appointment appointment : appointments) {
                if ((customerIDComboBox.getValue() == appointment.getCustomerID())) {
                    if (Helper.convertUTCToLocal(appointment.getStartTime()).isBefore(endTime) && startTime.isBefore(Helper.convertUTCToLocal(appointment.getEndTime()))) {
                        Helper.displayAlert("Scheduling Conflict", "The customer has conflicting appointments", "Please schedule a start and end time where the customer has no appointments scheduled.", Alert.AlertType.ERROR);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks to see if the local date is within range of EST business hours.
     * @param dateTime
     * @return boolean
     */
    public boolean isEasternTimeWithinRange(LocalDateTime dateTime) {
        ZonedDateTime easternTime = convertToEastern(dateTime);
        LocalTime time = easternTime.toLocalTime();

        LocalTime startTime = LocalTime.of(8, 0); // 8 AM
        LocalTime endTime = LocalTime.of(22, 0); // 10 PM

        return time.isBefore(startTime) && time.isBefore(endTime);
    }

    /**
     * Converts local time to EST
     * @param localDateTime
     * @return EST time
     */
    public ZonedDateTime convertToEastern(LocalDateTime localDateTime) {
        ZoneId easternZoneId = ZoneId.of("America/New_York"); // Eastern Time zone
        ZonedDateTime easternZonedDateTime = localDateTime.atZone(easternZoneId);

        return easternZonedDateTime;
    }


    /**
     * Initializes the view with data
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactDAO contactDAO = new ContactDAOImp();
        UserDAO userDAO = new UserDAOImp();
        CustomerDAO customerDAO = new CustomerDAOImp();
        List<String> contactNames = new ArrayList<>();
        List<Integer> userIDs = new ArrayList<>();
        List<Integer> customerIDs = new ArrayList<>();
        List<Contact> contacts;
        List<User> users;
        List<Customer> customers;
        try {
            contacts = contactDAO.findAll();
            users = userDAO.findAll();
            customers = customerDAO.findAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Contact contact : contacts) {
            contactNames.add(contact.getContactName());
        }
        for (User user : users) {
            userIDs.add(user.getUserID());
        }
        for (Customer customer : customers) {
            customerIDs.add(customer.getCustomerID());
        }

        startTimeComboBox.setItems(FXCollections.observableArrayList(Helper.generateTimeSlots()));
        endTimeComboBox.setItems(FXCollections.observableArrayList(Helper.generateTimeSlots()));
        contactComboBox.setItems(FXCollections.observableArrayList(contactNames));
        userIDComboBox.setItems(FXCollections.observableArrayList(userIDs));
        customerIDComboBox.setItems(FXCollections.observableArrayList(customerIDs));
    }
}
