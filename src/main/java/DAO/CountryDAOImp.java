package DAO;

import Model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImp {

        public Country findByID(int countryID) throws SQLException {
            Country country = null;
            String sql = "SELECT * FROM COUNTRIES WHERE Country_ID = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, countryID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                country = new Country();
                country.setCountryID(rs.getInt("Country_ID"));
                country.setCountry(rs.getString("Country"));
                Timestamp createDateTimestamp = rs.getTimestamp("Create_Date");
                if (createDateTimestamp != null) {
                    country.setCreateDate(createDateTimestamp.toInstant());
                }
                country.setCreatedBy(rs.getString("Created_By"));
                Timestamp lastUpdateTimestamp = rs.getTimestamp("Last_Update");
                if (lastUpdateTimestamp != null) {
                    country.setLastUpdate(lastUpdateTimestamp.toInstant());
                }
                country.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            }
            return country;
        }




    public List<Country> findAll() throws SQLException {
        Country country = null;
        List<Country> listOfCountries =  new ArrayList<>();

        String sql = "SELECT * FROM COUNTRIES";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            country = new Country();
            country.setCountryID(rs.getInt("Country_ID"));
            country.setCountry(rs.getString("Country"));
            Timestamp createDateTimestamp = rs.getTimestamp("Create_Date");
            if (createDateTimestamp != null) {
                country.setCreateDate(createDateTimestamp.toInstant());
            }
            country.setCreatedBy(rs.getString("Created_By"));
            Timestamp lastUpdateTimestamp = rs.getTimestamp("Last_Update");
            if (lastUpdateTimestamp != null) {
                country.setLastUpdate(lastUpdateTimestamp.toInstant());
            }
            country.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            listOfCountries.add(country);
        }


        return listOfCountries;
    }


}
