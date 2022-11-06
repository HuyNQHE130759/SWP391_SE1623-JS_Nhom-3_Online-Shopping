package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonDAO extends DBContext {

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

    public int getNumberPageShipping(int itemPerPage) {
        try {
            String query = "SELECT count(*) as number FROM (SELECT ROW_NUMBER() OVER (ORDER BY b.bid) AS Number, \n"
                    + "                           b.bid as bid, p.pname as pname, b.total as total, bd.quantity as quantity, bd.status_shipping as statusShipping\n"
                    + "                           FROM Bill b \n"
                    + "                           JOIN BillDetail bd on b.bid = bd.bid \n"
                    + "                           JOIN Product p on p.pid = bd.pid) AS data ";
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
    
    public int getNumberPageReport(int itemPerPage) {
        try {
            String query = "SELECT count(*) as number FROM (SELECT ROW_NUMBER() OVER (ORDER BY b.bid) AS Number \n"
                    + "                           FROM Bill b \n"
                    + "                           JOIN BillDetail bd on b.bid = bd.bid \n JOIN [User] u ON u.cid = b.cid"
                    + "                           JOIN Product p on p.pid = bd.pid) AS data ";
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
