package DAO;

import Model.Country;
import Model.FirstLevelDivision;
import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstLevelDivisionDAOimp implements FirstLevelDivisionDAO {

    public FirstLevelDivision findByID(int fldID) throws SQLException {
        FirstLevelDivision fld = null;
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, fldID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            fld = new FirstLevelDivision();
            fld.setDivisionID(rs.getInt("Division_ID"));
            fld.setDivision(rs.getString("Division"));
            Timestamp createDate = rs.getTimestamp("Create_Date");
            if (createDate != null) {
                fld.setCreateDate(createDate.toInstant());
            }
            fld.setCreatedBy(rs.getString("Created_By"));
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            if (lastUpdate != null) {
                fld.setLastUpdate(lastUpdate.toInstant());
            }
            fld.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            fld.setCountryID(rs.getInt("Country_ID"));
        }


        return fld;
    }
    public List<FirstLevelDivision> findAll() throws SQLException {
        List<FirstLevelDivision> fldList = new ArrayList<>();

        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FirstLevelDivision fld = new FirstLevelDivision();

            fld.setDivisionID(rs.getInt("Division_ID"));
            fld.setDivision(rs.getString("Division"));
            Timestamp createDate = rs.getTimestamp("Create_Date");
            if (createDate != null) {
                fld.setCreateDate(createDate.toInstant());
            }
            fld.setCreatedBy(rs.getString("Created_By"));
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            if (lastUpdate != null) {
                fld.setLastUpdate(lastUpdate.toInstant());
            }
            fld.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            fld.setCountryID(rs.getInt("Country_ID"));

            fldList.add(fld);
        }

        return fldList;
    }

    public List<FirstLevelDivision> findByCountryName(String countryName) throws SQLException {
        List<FirstLevelDivision> fldList = new ArrayList<>();
        CountryDAO countryDAO = new CountryDAOImp();
        Country country = countryDAO.findByCountryName(countryName);

        countryDAO.findByCountryName(countryName);
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, country.getCountryID());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FirstLevelDivision fld = new FirstLevelDivision();

            fld.setDivisionID(rs.getInt("Division_ID"));
            fld.setDivision(rs.getString("Division"));
            Timestamp createDate = rs.getTimestamp("Create_Date");
            if (createDate != null) {
                fld.setCreateDate(createDate.toInstant());
            }
            fld.setCreatedBy(rs.getString("Created_By"));
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            if (lastUpdate != null) {
                fld.setLastUpdate(lastUpdate.toInstant());
            }
            fld.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            fld.setCountryID(rs.getInt("Country_ID"));

            fldList.add(fld);
        }

        return fldList;
    }

    public Map<Integer, String> getAllDivisons() throws SQLException {
        Map<Integer, String> divisions = new HashMap<>();

        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int id = rs.getInt("Division_ID");
            String name = rs.getString("Division");

            divisions.put(id, name);

        }
        return divisions;
    }

    public int findIDByStateName(String stateName) throws SQLException {
        FirstLevelDivision fld = null;
        String sql = "SELECT * FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, stateName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            fld = new FirstLevelDivision();
            fld.setDivisionID(rs.getInt("Division_ID"));
            fld.setDivision(rs.getString("Division"));
            Timestamp createDate = rs.getTimestamp("Create_Date");
            if (createDate != null) {
                fld.setCreateDate(createDate.toInstant());
            }
            fld.setCreatedBy(rs.getString("Created_By"));
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            if (lastUpdate != null) {
                fld.setLastUpdate(lastUpdate.toInstant());
            }
            fld.setLastUpdatedBy(rs.getString("Last_Updated_By"));
            fld.setCountryID(rs.getInt("Country_ID"));
        }


        return fld.getDivisionID();
    }






}
