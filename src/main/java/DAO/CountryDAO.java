package DAO;

import Model.Country;

import java.sql.SQLException;
import java.util.List;

public interface CountryDAO {
    Country findByID(int countryID) throws SQLException;
    List<Country> findAll() throws SQLException;
}
