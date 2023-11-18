package DAO;

import Model.Country;
import Model.FirstLevelDivision;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FirstLevelDivisionDAO {

    FirstLevelDivision findByID(int fldID) throws SQLException;

    List<FirstLevelDivision> findAll() throws SQLException;

    List<FirstLevelDivision> findByCountryID(int countryID) throws SQLException;

    Map<Integer, String> getAllDivisons() throws SQLException;

    List<FirstLevelDivision> findByCountryName(String countryName) throws SQLException;

    int findIDByStateName(String stateName) throws SQLException;

}


