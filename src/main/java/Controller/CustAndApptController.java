package Controller;

import DAO.*;
import Model.Appointment;
import Model.Customer;
import Model.FirstLevelDivision;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
    private TableColumn<Appointment, Instant> appointmentEndColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIDColumn;

    @FXML
    private TableColumn<Appointment, Instant> appointmentStartColumn;

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

    @FXML
    void onActionAddAppointment(ActionEvent event) {

    }

    @FXML
    void onActionAddCustomer(ActionEvent event) {

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

    }

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

        ObservableList<Customer> observableCustomerList = FXCollections.observableArrayList(customerList);
        ObservableList<Appointment> observableAppointmentList = FXCollections.observableArrayList(appointmentList);
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
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentCustomerIDColum.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
}
