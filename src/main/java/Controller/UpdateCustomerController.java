package Controller;

import DAO.*;
import HelperClasses.Helper;
import HelperClasses.SessionManager;
import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Update customer controller menu.
 *
 * @author tylersmall
 */
public class UpdateCustomerController implements Initializable {


    @FXML
    private TextField addressTextField;

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private ComboBox<String> stateComboBox;

    /**
     * Takes the user back to the main menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Helper.nextView("/Model/CustAndAppt.fxml", event);
    }

    /**
     * Updates the customer to the database if valid.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        FirstLevelDivisionDAO fldDAO = new FirstLevelDivisionDAOimp();
        CustomerDAO customerDAO = new CustomerDAOImp();
        Customer customer = customerDAO.findByCustomerID(Integer.parseInt(idTextField.getText()));

        if (isValidCustomer(customer)) {
            customer.setName(nameTextField.getText());
            customer.setAddress(addressTextField.getText());
            customer.setPhone(phoneTextField.getText());
            customer.setDivisionID(fldDAO.findIDByStateName(stateComboBox.getValue()));
            customer.setPostalCode(postalCodeTextField.getText());
            customer.setCreatedBy(SessionManager.getCurrentUser().getUsername());
            customer.setCreateDate(Instant.now());
            customer.setLastUpdate(Instant.now());
            customer.setLastUpdatedBy(SessionManager.getCurrentUser().getUsername());

            customerDAO.updateCustomer(customer);
            Helper.nextView("/Model/CustAndAppt.fxml", event);
        }
        else {
            Helper.displayAlert("Error", "Could not save due to empty text fields and/or blank combo boxes",
                    "Please make sure all fields and combo boxes are selected", Alert.AlertType.ERROR);
        }
    }

    /**
     * Checks to see if the customer is valid and all fields are correctly checked.
     * @param customer
     * @return boolean
     */
    public boolean isValidCustomer(Customer customer) {
        if (nameTextField.getText().isEmpty() || addressTextField.getText().isEmpty()
                || phoneTextField.getText().isEmpty() || postalCodeTextField.getText().isEmpty()) {
            return false;
        }
        else if (countryComboBox.getValue() == null || stateComboBox.getValue() == null) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Sends selected customer to the view to prepopulate the fields and combo boxes.
     * @param customer
     * @throws SQLException
     */
    public void sendCustomer(Customer customer) throws SQLException {
        CountryDAO countryDAO = new CountryDAOImp();
        FirstLevelDivisionDAO fldDAO = new FirstLevelDivisionDAOimp();
        FirstLevelDivision fld = fldDAO.findByID(customer.getDivisionID());
        Country country = countryDAO.findByID(fld.getCountryID());
        List<String> stateNames = new ArrayList<>();

        idTextField.setText(String.valueOf(customer.getCustomerID()));
        nameTextField.setText(customer.getName());
        addressTextField.setText(customer.getAddress());
        phoneTextField.setText(customer.getPhone());
        postalCodeTextField.setText(customer.getPostalCode());

        countryComboBox.getSelectionModel().select(country.getCountry());
        stateComboBox.getSelectionModel().select(fld.getDivision());
        updateStateComboBox();

    }

    /**
     * Initializes the text fields and combo boxes with the necessary data.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> countryNames = new ArrayList<>();
        List<String> stateNames = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<FirstLevelDivision> fldList = new ArrayList<>();

        CountryDAO countryDAO = new CountryDAOImp();
        FirstLevelDivisionDAO fldDAO = new FirstLevelDivisionDAOimp();

        try {
            countries = countryDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Country country : countries) {
            countryNames.add(country.getCountry());
        }

        countryComboBox.setItems(FXCollections.observableArrayList(countryNames));

        countryComboBox.setOnAction(event -> {
            try {
                updateStateComboBox();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });



    }

    private void updateStateComboBox() throws SQLException {
        String selectedCountry = countryComboBox.getValue();
        FirstLevelDivisionDAO fldDAO = new FirstLevelDivisionDAOimp();

        List<FirstLevelDivision> selectedFLD = fldDAO.findByCountryName(selectedCountry);

        List<String> stateNames = new ArrayList<>();
        for (FirstLevelDivision fld : selectedFLD) {
            stateNames.add(fld.getDivision());
        }
        stateComboBox.setItems(FXCollections.observableArrayList(stateNames));
    }

}

