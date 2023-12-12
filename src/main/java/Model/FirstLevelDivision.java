package Model;

import java.time.Instant;

/**
 * Class for the first level divisons.
 *
 * @author tylersmall
 */
public class FirstLevelDivision {

    private int divisionID;
    private String Division;
    private Instant createDate;
    private String createdBy;
    private Instant lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /**
     * To string method used for testing.
     * @return string with first level division fields
     */
    @Override
    public String toString() {
        return "FirstLevelDivision{" +
                "Division_ID=" + divisionID +
                ", Division='" + Division + '\'' +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", Country_ID='" + countryID + '\'' +
                '}';
    }

    /**
     * gets the division id
     * @return division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * sets the division id
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * gets the division
     * @return division
     */
    public String getDivision() {
        return Division;
    }

    /**
     * Sets the division
     * @param division
     */
    public void setDivision(String division) {
        Division = division;
    }

    /**
     * gets the date created
     * @return create date
     */
    public Instant getCreateDate() {
        return createDate;
    }

    /**
     * sets the create date
     * @param createDate
     */
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the user who created first level division
     * @return user who created fld
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the first level division
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
     * @param lastUpdate
     */
    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user last updated by
     * @return user who last updated
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated by
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the country id
     * @return country id
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the country id
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
