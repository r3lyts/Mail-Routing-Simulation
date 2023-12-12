package Model;

import java.time.Instant;
import java.util.Map;

/**
 * Class for a customer
 *
 * @author tylersmall
 */
public class Customer {
    private int customerID;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;
    private Instant createDate;
    private String createdBy;
    private Instant lastUpdate;
    private String lastUpdatedBy;
    private static Map<Integer, String> divisionMap;

    /**
     * To string method for testing the customer class
     * @return string of all customer fields.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "Customer_ID=" + customerID +
                ", Customer_Name='" + name + '\'' +
                ", Address='" + address + '\'' +
                ", Postal_Code='" + postalCode + '\'' +
                ", Phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", Division_ID='" + divisionID + '\'' +
                '}';
    }

    /**
     * Gets the customer id
     * @return customer id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * sets the customer id
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * gets the customer name
     * @return customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the customer address
     * @return customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets the postal code
     * @return postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * sets the postal code
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the customer's phone number
     * @return customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customers phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the division id
     * @return division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets the division id
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Gets the date the customer was created.
     * @return create date
     */
    public Instant getCreateDate() {
        return createDate;
    }

    /**
     * Sets the create date
     * @param createDate
     */
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created the customer.
     * @return created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the time the customer was last updated
     * @return last update
     */
    public Instant getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the last update for the customer
     * @param lastUpdate
     */
    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user who last updated the customer
     * @return last updated by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the customer
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * sets the division map.
     * @param map
     */
    public static void setDivisionMap(Map<Integer, String> map) {
        Customer.divisionMap = map;
    }

    /**
     * Gets the divison name using the division map.
     * @return divison name
     */
    public String getDivisionName() {
        return divisionMap.get(this.divisionID);
    }


}
