package Controller;

import DAO.*;
import HelperClasses.Helper;
import Model.Appointment;
import Model.Customer;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * This is the main view of the program displaying both customers and appointments.
 *
 * @author tylersmall
 */

public class CustAndApptController implements Initializable {

    @FXML
    private TableView<Appointment> AppointmentTableView;
    @FXML
    private TableColumn<Appointment, Integer> appointmentContactColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIDColum;

    @FXML
    private TableColumn<Appointment, String> appointmentLocationColumn;

    @FXML
    private TableColumn<Appointment, String> appointmentDescriptionColumn;

    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentEndColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIDColumn;

    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentStartColumn;

    @FXML
    private TableColumn<Appointment, String> appointmentTitleColumn;

    @FXML
    private TableColumn<Appointment, String> appointmentTypeColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentUserIDColumn;

    @FXML
    private TableView<Customer> CustomerTableView;
    @FXML
    private TableColumn<Customer, String> customerAddressColumn;

    @FXML
    private TableColumn<Customer, Integer> customerIDColumn;

    @FXML
    private TableColumn<Customer, String> customerNameColumn;

    @FXML
    private TableColumn<Customer, String> customerPhoneColumn;

    @FXML
    private TableColumn<Customer, String> customerPostalCodeColumn;

    @FXML
    private TableColumn<Customer, String> customerStateColumn;

    private static boolean hasShowAlert = false;

    private ObservableList<Customer> observableCustomerList = FXCollections.observableArrayList();
    private ObservableList<Appointment> observableAppointmentList = FXCollections.observableArrayList();

    /**
     * Takes user to the add appointment menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
        Helper.nextView("/Model/AddAppointmentForm.fxml", event);
    }

    /**
     * Takes the user to add customer menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        Helper.nextView("/Model/AddCustomerForm.fxml", event);
    }

    /**
     * Deletes an appointment selected from the appointment table.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws SQLException {
        Appointment selectedAppointment = AppointmentTableView.getSelectionModel().getSelectedItem();
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();

        if (selectedAppointment != null) {
            String apptID = String.valueOf(selectedAppointment.getAppointmentID());
            String apptType = selectedAppointment.getType();
            appointmentDAO.deleteAppointment(selectedAppointment.getAppointmentID());
            AppointmentTableView.getItems().remove(selectedAppointment);
            Helper.displayAlert("Appointment Deletion", "Appointment was successfully deleted.", "Appointment ID: " + apptID + " and Type: " + apptType + " was successfully deleted", Alert.AlertType.INFORMATION);
        }
        else {
            Helper.displayAlert("Select an Item", "No Appointment was Selected", "In order to delete, please select an Appointment.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Deletes a customer selected from the customer table.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws SQLException {
        Customer selectedCustomer = CustomerTableView.getSelectionModel().getSelectedItem();
        CustomerDAO customerDAO = new CustomerDAOImp();

        if (selectedCustomer != null) {
            customerDAO.deleteCustomer(selectedCustomer.getCustomerID());
            CustomerTableView.getItems().remove(selectedCustomer);
            Helper.displayAlert("Customer Deletion", "Customer Deletion", "Customer was successfully deleted.", Alert.AlertType.INFORMATION);
        }
        else {
            Helper.displayAlert("Select an Item", "No Customer was Selected", "In order to delete, please select a Customer.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Logs the user out and takes the user back to the login screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionLogout(ActionEvent event) throws IOException {
        Helper.nextView("/Model/login-form.fxml", event);
    }

    /**
     * Takes the user to the reports view.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReports(ActionEvent event) throws IOException {
        Helper.nextView("/Model/ReportForm.fxml", event);
    }

    /**
     * Takes the user to the update appointment screen with the selected appointment.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException, SQLException {
        Appointment selectedAppointment = AppointmentTableView.getSelectionModel().getSelectedItem();

        if (selectedAppointment != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Model/UpdateAppointmentForm.fxml"));
            Parent updateView = loader.load();

            UpdateAppointmentController uAC = loader.getController();
            uAC.sendAppointment(selectedAppointment);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(updateView));
            stage.show();
            Helper.centerStage(stage);
        }
        else {
            Helper.displayAlert("Select an Appointment", "No Appointment Was Selected", "In order to update, please select an Appointment", Alert.AlertType.ERROR);

        }
    }

    /**
     * Takes the user to the update customer screen with the selected customer.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws SQLException, IOException {
        Customer selectedCustomer = CustomerTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Model/UpdateCustomerForm.fxml"));
            Parent updateView = loader.load();

            UpdateCustomerController uCC = loader.getController();
            uCC.sendCustomer(selectedCustomer);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(updateView));
            stage.show();
            Helper.centerStage(stage);
        }
        else {
            Helper.displayAlert("Select an Item", "No Customer Was Selected", "In order to update, please select a customer", Alert.AlertType.ERROR);

        }
    }

    /**
     * Loads the appointment table with all appointments.
     * @param event
     * @throws SQLException
     */
    @FXML
    void allAppointmentsRadioButton(ActionEvent event) throws SQLException {
        observableAppointmentList.clear();
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        observableAppointmentList = FXCollections.observableArrayList(appointmentDAO.findAll());
        AppointmentTableView.setItems(observableAppointmentList);
    }

    /**
     * Loads the appointment table with appointments from the current month.
     * @param event
     * @throws SQLException
     */
    @FXML
    void currentMonthRadioButton(ActionEvent event) throws SQLException {
        observableAppointmentList.clear();
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        List<Appointment> allAppointments = appointmentDAO.findAll();
        List<Appointment> currentMonthAppointments = new ArrayList<>();
        for (Appointment appointment : allAppointments) {
            if (LocalDate.now().getMonthValue() == appointment.getLocalStartTime().getMonthValue()) {
                currentMonthAppointments.add(appointment);
            }
        }

        observableAppointmentList = FXCollections.observableArrayList(currentMonthAppointments);
        AppointmentTableView.setItems(observableAppointmentList);

    }

    /**
     * Loads the appointment table with appointments from the current week.
     * @param event
     * @throws SQLException
     */
    @FXML
    void currentWeekRadioButton(ActionEvent event) throws SQLException {
        observableAppointmentList.clear();
        int currentWeek = LocalDate.now().get((WeekFields.of(Locale.getDefault())).weekOfWeekBasedYear());

        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        List<Appointment> allAppointments = appointmentDAO.findAll();
        List<Appointment> currentWeekAppointments = new ArrayList<>();
        for (Appointment appointment : allAppointments) {
            if (currentWeek == appointment.getLocalStartTime().get((WeekFields.of(Locale.getDefault())).weekOfWeekBasedYear())) {
                currentWeekAppointments.add(appointment);
            }
        }

        observableAppointmentList = FXCollections.observableArrayList(currentWeekAppointments);
        AppointmentTableView.setItems(observableAppointmentList);

    }

    /**
     * Gets an appointment that is happening within 15 minutes of a login.
     * @param appointments
     * @return
     */
    public Optional<Appointment> getUpcomingAppointment(List<Appointment> appointments) {
        Instant now = Instant.now();
        ZoneId localZoneId = ZoneId.systemDefault();
        LocalDateTime localNow = LocalDateTime.ofInstant(now, localZoneId);

        for (Appointment appointment : appointments) {
            LocalDateTime appointmentStart = LocalDateTime.ofInstant(appointment.getStartTime(), localZoneId);
            if (!appointmentStart.isBefore(localNow) &&
                    Duration.between(localNow, appointmentStart).toMinutes() <= 15) {
                return Optional.of(appointment);
            }
        }
        return Optional.empty();
    }

    /**
     * Shows the alert for an appointment that is happening within 15 mintues of login.
     * @param appointments
     */
    public void showAlertForUpcomingAppointment(List<Appointment> appointments) {
        Optional<Appointment> upcomingAppointment = getUpcomingAppointment(appointments);

        if (upcomingAppointment.isPresent()) {
            Appointment appointment = upcomingAppointment.get();
            String message = String.format("You have an upcoming appointment.\nID: %d\nDate: %s\nTime: %s",
                    appointment.getAppointmentID(),
                    appointment.getStartTime().atZone(ZoneId.systemDefault()).toLocalDate(),
                    appointment.getStartTime().atZone(ZoneId.systemDefault()).toLocalTime());
            Helper.displayAlert("Upcoming Appointment", "Upcoming Appointment", message, Alert.AlertType.INFORMATION);
        } else {
            Helper.displayAlert("No Upcoming Appointments", "No Upcoming Appointments", "You have no appointments within the next 15 minutes.", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Initializes the tables with data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerDAO customerDAO = new CustomerDAOImp();
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        FirstLevelDivisionDAO fld = new FirstLevelDivisionDAOimp();

        List<Customer> customerList = null;
        List<Appointment> appointmentList = null;
        try {
            //Sets up customerlist and division names
            Map<Integer, String> divisionMap = fld.getAllDivisons();
            customerList = customerDAO.findAllCustomers();
            Customer.setDivisionMap(divisionMap);

            //Sets up AppointmentList
            appointmentList = appointmentDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        observableCustomerList = FXCollections.observableArrayList(customerList);
        observableAppointmentList = FXCollections.observableArrayList(appointmentList);
        CustomerTableView.setItems(observableCustomerList);
        AppointmentTableView.setItems(observableAppointmentList);

        //Customer Table
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerStateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDivisionName()));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        //Appointment Table
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentStartColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLocalStartTime()));
        appointmentEndColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLocalEndTime()));
        appointmentContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentCustomerIDColum.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        if (!hasShowAlert) {
            Platform.runLater(() -> {
                showAlertForUpcomingAppointment(observableAppointmentList);
            });
            hasShowAlert = true;
        }

    }

}
