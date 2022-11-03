package dao;

import entity.Shipping;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShippingDAO extends DBContext {

    public ArrayList<Shipping> getShippingList(String status, int itemPerPage, int pageCurrent) throws SQLException {
        try {
            ArrayList<Shipping> list = new ArrayList<>();
            int from = itemPerPage * (pageCurrent - 1) + 1;
            int to = from + itemPerPage - 1;
            String query = "SELECT bid, pname, total, quantity, statusShipping "
                    + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY b.bid) AS Number, \n"
                    + "                           b.bid as bid, p.pname as pname, b.total as total, bd.quantity as quantity, bd.status_shipping as statusShipping\n"
                    + "                           FROM Bill b \n"
                    + "                           JOIN BillDetail bd on b.bid = bd.bid \n"
                    + "                           JOIN Product p on p.pid = bd.pid) AS data \n"
                    + "                           WHERE Number BETWEEN " + from + " AND " + to + " \n";

            if (!status.isEmpty()) {
                query += "AND statusShipping = '" + status + "'";
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rslt = ps.executeQuery();
            while (rslt.next()) {
                Shipping shipping = new Shipping();
                shipping.setBillId(rslt.getInt("bid"));
                shipping.setProductName(rslt.getString("pname"));
                shipping.setQuantity(rslt.getInt("quantity"));
                shipping.setPrice(rslt.getDouble(4));
                shipping.setTotal(shipping.getQuantity() * shipping.getPrice());
                shipping.setStatusShipping(rslt.getString("statusShipping"));
                list.add(shipping);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    public void updateShippingStatus(int billId, String statusShipping) throws SQLException {
        try {
            String query = "UPDATE BillDetail SET status_shipping = ? WHERE bid = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, billId);
            ps.setString(2, statusShipping);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            connection.close();
        }
    }

}
