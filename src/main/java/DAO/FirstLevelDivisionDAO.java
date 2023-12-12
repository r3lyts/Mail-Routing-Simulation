package DAO;

import Model.Country;
import Model.FirstLevelDivision;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Interface for accessing the first level division table in the database.
 *
 * @author tylersmall
 */
public interface FirstLevelDivisionDAO {

    /**
     * finds a fld by fld id
     * @param fldID
     * @return first level division
     * @throws SQLException
     */
    FirstLevelDivision findByID(int fldID) throws SQLException;

    /**
     * Finds all the first level divisions in the database
     * @return list of flds
     * @throws SQLException
     */
    List<FirstLevelDivision> findAll() throws SQLException;


    /**
     * Gets all the divisions
     * @return all divisions in a map
     * @throws SQLException
     */
    Map<Integer, String> getAllDivisons() throws SQLException;

    /**
     * Finds the divisions by name
     * @param countryName
     * @return list of first level divisions
     * @throws SQLException
     */
    List<FirstLevelDivision> findByCountryName(String countryName) throws SQLException;

    /**
     * find id by name state
     * @param stateName
     * @return id
     * @throws SQLException
     */
    int findIDByStateName(String stateName) throws SQLException;

}


