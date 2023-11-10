package DAO;

import Model.Country;
import Model.FirstLevelDivision;

import java.sql.SQLException;
import java.util.List;

public interface FirstLevelDivisionDAO {

    FirstLevelDivision findByID(int fldID) throws SQLException;
    List<FirstLevelDivision> findAll() throws SQLException;

    public List<FirstLevelDivision> findByCountryID(int countryID) throws SQLException;
}
