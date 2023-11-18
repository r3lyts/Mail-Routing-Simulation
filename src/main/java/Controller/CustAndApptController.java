package Controller;

import DAO.*;
import HelperClasses.Helper;
import Model.Appointment;
import Model.Customer;
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
    void onActionAddCustomer(ActionEvent event) throws IOException {
        Helper.nextView("/Model/AddCustomerForm.fxml", event);
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

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

    @FXML
    void onActionLogout(ActionEvent event) throws IOException {
        Helper.nextView("/Model/login-form.fxml", event);
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

    }

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
