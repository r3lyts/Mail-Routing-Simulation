package DAO;

import Model.Country;
import Model.FirstLevelDivision;

import java.util.List;

public interface FirstLevelDivisionDAO {

    FirstLevelDivision findByID(int fldID);
    List<FirstLevelDivision> findAll();

    public List<FirstLevelDivision> findByCountryID(int countryID);
}
