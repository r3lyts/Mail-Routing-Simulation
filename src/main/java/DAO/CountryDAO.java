package DAO;

import Model.Country;

import java.util.List;

public interface CountryDAO {
    Country findByID(int countryID);
    List<Country> findAll();
}
