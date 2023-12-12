package Model;

import java.time.Instant;

/**
 * Class for a country
 *
 * @author tylersmall
 */
public class Country {
    private int countryID;
    private String country;
    private Instant createDate;
    private String createdBy;
    private Instant lastUpdate;
    private String lastUpdatedBy;


    /**
     * Empty Constructor for a Country
     */
    public Country() {
    }

    /**
     * Constructor for Country.
     * @param countryID
     * @param country
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     */
    public Country(int countryID, String country, Instant createDate,
                   String createdBy, Instant lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * To string method to easily print a country to the terminal. Only used for testing.
     * @return string with all countries fields.
     */
    //FOR TESTING
    @Override
    public String toString() {
        return "Country{" +
                "countryID=" + countryID +
                ", country='" + country + '\'' +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }

    /**
     * Gets the country id
     * @return country id
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the countries id
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * gets the country
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * gets the create date of the country
     * @return create date
     */
    public Instant getCreateDate() {
        return createDate;
    }

    /**
     * sets the create date of the country
     * @param createDate
     */
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    /**
     * gets the user who created the country
     * @return user who created
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who creates the country
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the time last updated
     * @return last update
     */
    public Instant getLastUpdate() {
        return lastUpdate;
    }

    /**
     * sets the last update time
     * @param timestamp
     */
    public void setLastUpdate(Instant timestamp) {
        this.lastUpdate = timestamp;
    }

    /**
     * gets the user who last updated
     * @return last updated by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
