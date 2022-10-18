package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonDAO extends DBContext{
    public int getNumberPage(int itemPerPage, String module) {
        try {
            String query = "SELECT COUNT(*) as number FROM " + module;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rslt = ps.executeQuery();
            while (rslt.next()) {
                int number = rslt.getInt("number");
                return number % itemPerPage != 0 ? number / itemPerPage + 1 : number / itemPerPage;
            }
        } catch (SQLException e) {
            
        }
        return -1;
    }
}
