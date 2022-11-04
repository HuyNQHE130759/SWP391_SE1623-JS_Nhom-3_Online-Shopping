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
            String query = "SELECT bid, pname, price, total, quantity, statusShipping "
                    + "FROM (SELECT ROW_NUMBER() OVER (ORDER BY b.bid) AS Number, \n"
                    + "                           b.bid as bid, p.pname as pname, b.total as total, bd.quantity as quantity, p.price as price, bd.status_shipping as statusShipping\n"
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
                shipping.setPrice(rslt.getDouble("price"));
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

    public int updateShippingStatus(int billId, String statusShipping) throws SQLException {
        try {
            String query = "UPDATE BillDetail SET status_shipping = ? WHERE bid = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, statusShipping);
            ps.setInt(2, billId);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public int getProductIdByBillId(int billId) throws Exception {
        try {
            String query = "SELECT * FROM BillDetail where bid = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, billId);
            ResultSet resultSet = prepareStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("pid");
            }
        } catch (Exception e) {
            throw e;
        } 
        return -1;
    }

    public int getValueQuantity(int billId) throws SQLException {
        try {
            String query = "SELECT b.bid as billId, p.pid as productId, p.quantity as currentQuantity, bd.quantity as billQuantity "
                    + "FROM Bill b "
                    + "JOIN BillDetail bd ON b.bid = bd.bid "
                    + "JOIN Product p ON bd.pid = p.pid "
                    + "WHERE b.bid = ? ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, billId);
            ResultSet resultSet = prepareStatement.executeQuery();
            int quantity = 0;
            if(resultSet.next()) {
                quantity = resultSet.getInt("currentQuantity") - resultSet.getInt("billQuantity");
            }
            return quantity;
        } catch (Exception e) {
            throw e;
        } 
    }

    public void updateQuantity(int productId, int billId) {
        try {
            String query = "UPDATE Product SET quantity = ? WHERE pid = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            int quantity = getValueQuantity(billId);
            prepareStatement.setInt(1, quantity);
            prepareStatement.setInt(2, productId);
            prepareStatement.executeUpdate();
        } catch (Exception e) {
        }
    }

}
