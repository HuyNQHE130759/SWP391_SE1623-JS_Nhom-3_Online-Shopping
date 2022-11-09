package dao;

import entity.Voucher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VoucherDAO extends DBContext{
    
    public ArrayList<Voucher> getVoucherList(int itemPerPage, int pageCurrent) {
        try {
            ArrayList<Voucher> list = new ArrayList<>();
            String query = "SELECT * FROM ( "
                    + "SELECT ROW_NUMBER() "
                    + "OVER(ORDER BY time_end desc) as Number, "
                    + "* FROM Voucher "
                    + ") as Data where Number between ? and ?";
            PreparedStatement ps = connection.prepareStatement(query);
            int from = itemPerPage * (pageCurrent - 1) + 1;
            int to = from + itemPerPage - 1;
            ps.setInt(1, from);
            ps.setInt(2, to);
            ResultSet rslt = ps.executeQuery();
            while (rslt.next()) {
                Voucher v = new Voucher();
                v.setId(rslt.getInt("id"));
                v.setCode(rslt.getString("code"));
                v.setDiscount(rslt.getInt("discount"));
                v.setDescription(rslt.getString("description"));
                v.setTimeEnd(rslt.getDate("time_end"));
                list.add(v);
            }
            return list;
        } catch (SQLException e) {

        }
        return null;
    }
    
    public void addNewVoucher(Voucher v) throws SQLException {
        try {
            String query = "Insert into [Voucher] values (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, v.getCode());
            ps.setInt(2, v.getDiscount());
            ps.setString(3, v.getDescription());
            ps.setDate(4, v.getTimeEnd());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean voucherCodeIsExist(String voucher) throws SQLException {
        try {
            String query = "Select count(*) as num from Voucher where code = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, voucher);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return Integer.parseInt(rslt.getString("num")) > 0;
            }
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }
    
}
