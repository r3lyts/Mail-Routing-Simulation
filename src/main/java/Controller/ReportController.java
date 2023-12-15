package Controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImp;
import DAO.ContactDAO;
import DAO.ContactDAOImp;
import HelperClasses.Helper;
import Model.Appointment;
import Model.AppointmentSummary;
import Model.Contact;
import Model.ContactAppointmentSummary;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Displays three separate reports regarding combinations of customers and appointments.
 *
 * @author tylersmall
 */
public class ReportController implements Initializable {

    @FXML
    private ComboBox<String> contactComboBox;

    //Appointment Type Table
    @FXML
    private TableView<AppointmentSummary> AppointmentTypeTableView;
    @FXML
    private TableColumn<AppointmentSummary, String> appointmentMonth;
    @FXML
    private TableColumn<AppointmentSummary, String> appointmentType;
    @FXML
    private TableColumn<AppointmentSummary, Integer> totalAppointments;


    //Contact Overview Table
    @FXML
    private TableView<Appointment> contactOverviewTableView;

    @FXML
    private TableColumn<Appointment, Integer> contactTableAppointmentCustomerID;

    @FXML
    private TableColumn<Appointment, String> contactTableAppointmentDescription;

    @FXML
    private TableColumn<Appointment, LocalDateTime> contactTableAppointmentEnd;

    @FXML
    private TableColumn<Appointment, Integer> contactTableAppointmentID;

    @FXML
    private TableColumn<Appointment, String> contactTableAppointmentLocation;

    @FXML
    private TableColumn<Appointment, LocalDateTime> contactTableAppointmentStart;

    @FXML
    private TableColumn<Appointment, String> contactTableAppointmentTitle;

    @FXML
    private TableColumn<Appointment, String> contactTableAppointmentType;




    //Contacts name and total appointments table
    @FXML
    private TableView<ContactAppointmentSummary> numOfAppointmentsByContactTableView;
    @FXML
    private TableColumn<ContactAppointmentSummary, Integer> contactTotalAppointments;
    @FXML
    private TableColumn<ContactAppointmentSummary, String> contactName;

    /**
     * Takes the user back to the main screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        Helper.nextView("/Model/CustAndAppt.fxml", event);
    }

    /**
     * Aggreagates all appointments into a list that holds and the month of number of appointments per month.
     * @param appointments
     * @return lists of aggregated appointments.
     */
    public List<AppointmentSummary> aggregateAppointments(List<Appointment> appointments) {
        Map<String, Integer> countMap = new HashMap<>();

        for (Appointment appointment : appointments) {
            String month = appointment.getLocalStartTime().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            String type = appointment.getType();
            String key = type + " - " + month;

            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }

        //LAMBDA EXPRESSION
        return countMap.entrySet().stream()
                .map(entry -> {
                    String[] parts = entry.getKey().split(" - ");
                    String type = parts[0];
                    String month = parts[1];
                    int count = entry.getValue();
                    return new AppointmentSummary(type, month, count);
                })
                .collect(Collectors.toList());
    }

    /**
     * Aggregates contact appointments into a list that shows how many appointments each contact has.
     * @param appointments
     * @return list of appointments per contact
     * @throws SQLException
     */
    public List<ContactAppointmentSummary> aggregateContactAppointments(List<Appointment> appointments) throws SQLException {
        Map<String, Integer> appointmentCount = new HashMap<>();
        ContactDAO contactDAO = new ContactDAOImp();


        for (Appointment appointment : appointments) {
            String contactName = contactDAO.getContactName(appointment.getContactID());
            appointmentCount.put(contactName, appointmentCount.getOrDefault(contactName, 0) + 1);
        }

        List<ContactAppointmentSummary> summaryList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : appointmentCount.entrySet()) {
            summaryList.add(new ContactAppointmentSummary(entry.getKey(), entry.getValue()));
        }

        return summaryList;
    }

    /**
     * Updates the contact view based on the selection of the combo box.
     * @throws SQLException
     */
    private void updateContactView() throws SQLException {
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        ContactDAO contactDAO = new ContactDAOImp();
        List<Appointment> appointments = appointmentDAO.findAll();
        List<Appointment> contactAppointments = new ArrayList<>();

        if (contactComboBox.getValue() != null) {
            int contactID = contactDAO.getContactIDbyName(contactComboBox.getValue());
            for (Appointment appointment : appointments) {
                if (contactID == appointment.getContactID()) {
                    contactAppointments.add(appointment);
                }
            }
        }

        contactTableAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        contactTableAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        contactTableAppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactTableAppointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactTableAppointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactTableAppointmentStart.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLocalStartTime()));
        contactTableAppointmentEnd.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLocalEndTime()));
        contactTableAppointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        contactOverviewTableView.setItems(FXCollections.observableArrayList(contactAppointments));
    }

    /**
     * Initializes the tables with data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        ContactDAO contactDAO = new ContactDAOImp();
        try {
            List<Appointment> allAppointments = appointmentDAO.findAll();
            List<AppointmentSummary> summaryAppointments = aggregateAppointments(allAppointments);
            List<Contact> contacts = contactDAO.findAll();
            List<String> contactNames = new ArrayList<>();

            for (Contact contact : contacts) {
                contactNames.add(contact.getContactName());
            }

            //Appointment Summary Table
            appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
            appointmentMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
            totalAppointments.setCellValueFactory(new PropertyValueFactory<>("count"));
            AppointmentTypeTableView.setItems(FXCollections.observableArrayList(summaryAppointments));

            //Contact Summary Table
            contactComboBox.setItems(FXCollections.observableArrayList(contactNames));
            //LAMBDA EXPRESSION
            contactComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    try {
                        updateContactView();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            //contact total appointments
            contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            contactTotalAppointments.setCellValueFactory(new PropertyValueFactory<>("totalAppointments"));
            List<ContactAppointmentSummary> contactAppointmentSummaries = aggregateContactAppointments(allAppointments);
            numOfAppointmentsByContactTableView.setItems(FXCollections.observableArrayList(contactAppointmentSummaries));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





    }
}

