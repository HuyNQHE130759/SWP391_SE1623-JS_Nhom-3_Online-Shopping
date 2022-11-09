package dao;

import entity.Report;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDAO extends DBContext {

    public ArrayList<Report> getListReport(int itemPerPage, int pageCurrent) throws SQLException {
        try {
            ArrayList<Report> list = new ArrayList<>();
            int from = itemPerPage * (pageCurrent - 1) + 1;
            int to = from + itemPerPage - 1;
            String query = "SELECT bid, pname, quantity, price, total, address, phone, shipperAccount, dateCreated, statusShipping \n"
                    + "                     FROM (SELECT ROW_NUMBER() OVER (ORDER BY b.bid) AS Number,\n"
                    + "                    b.bid as bid, p.pname as pname, b.total as total, bd.quantity as quantity, p.price as price, \n"
                    + "                    u.address as address, u.phone as phone, u.username as shipperAccount, b.dateCreate as dateCreated, bd.status_shipping as statusShipping\n"
                    + "                    FROM Bill b \n"
                    + "                    JOIN BillDetail bd on b.bid = bd.bid\n"
                    + "                    JOIN [User] u on b.cid = u.cid \n"
                    + "                    JOIN Product p on p.pid = bd.pid) AS data  \n"
                    + "WHERE Number BETWEEN " + from + " AND " + to + " \n"
                    + "ORDER BY dateCreated";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rslt = ps.executeQuery();
            while (rslt.next()) {
                Report report = new Report();
                report.setBillId(rslt.getInt("bid"));
                report.setProductName(rslt.getString("pname"));
                report.setQuantity(rslt.getInt("quantity"));
                report.setPrice(rslt.getLong("price"));
                report.setTotalPrice(rslt.getLong("total"));
                report.setAddress(rslt.getString("address"));
                report.setPhone(rslt.getString("phone"));
                report.setShipperAccount(rslt.getString("shipperAccount"));
                report.setDateCreated(rslt.getDate("dateCreated"));
                report.setStatusShipping(rslt.getString("statusShipping"));
                list.add(report);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
    
    public double getTotalBill() throws SQLException {
        try {
            String query = "SELECT COUNT(*) as num FROM Bill b JOIN BillDetail bd on b.bid = bd.bid";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return rslt.getDouble("num");
            }
        } catch (SQLException e) {
            throw e;
        } 
        return 0;
    }
    
    public double getTotalBill(String status) throws SQLException {
        try {
            String query = "SELECT COUNT(*) as num FROM Bill b JOIN BillDetail bd on b.bid = bd.bid WHERE bd.status_shipping = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return rslt.getDouble("num");
            }
        } catch (SQLException e) {
            throw e;
        } 
        return 0L;
    }
    
    public Long getSumOfTotalPrice() throws SQLException {
        try {
            String query = "SELECT SUM(b.total) as sumTotalPrice FROM Bill b JOIN BillDetail bd on b.bid = bd.bid WHERE bd.status_shipping = 'done'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return rslt.getLong("sumTotalPrice");
            }
        } catch (SQLException e) {
            throw e;
        } 
        return 0L;
    }
   
}
