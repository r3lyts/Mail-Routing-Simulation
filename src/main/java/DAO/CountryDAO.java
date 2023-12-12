package DAO;

import Model.Country;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for accessing the country database.
 *
 * @author tylersmall
 */
public interface CountryDAO {

    /**
     * Finds a country by id
     * @param countryID
     * @return country
     * @throws SQLException
     */
    Country findByID(int countryID) throws SQLException;

    /**
     * Finds all countries listed in the database.
     * @return list of countries
     * @throws SQLException
     */
    List<Country> findAll() throws SQLException;

    /**
     * Finds a country by country name
     * @param countryName
     * @return a country object
     * @throws SQLException
     */
    Country findByCountryName(String countryName) throws SQLException;
}
