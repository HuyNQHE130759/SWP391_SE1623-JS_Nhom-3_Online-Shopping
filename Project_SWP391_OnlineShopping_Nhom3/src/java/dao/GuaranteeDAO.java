package dao;

import entity.Guarantee;
import entity.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuaranteeDAO extends DBContext {

    public void createRequestGuarantee(Guarantee g) throws SQLException {
        try {
            String query = "INSERT INTO Guaranteee(product, description, status, owner, image, fileName) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, g.getProduct());
            ps.setString(2, g.getDescription());
            ps.setString(3, "inprogress");
            ps.setString(4, g.getOwner());
            ps.setBlob(5, g.getImage());
            ps.setString(6, g.getFileName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void updateGuarantee(int id, String status, Date pickUpDate) throws SQLException {
        try {
            String query = "UPDATE Guaranteee SET status = ?, pick_up_date = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setDate(2, pickUpDate);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList<Product> getDropdownProduct() throws SQLException {
        try {
            ArrayList<Product> list = new ArrayList<>();
            String query = "SELECT pname FROM Product";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setPname(rs.getString("pname"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList<Guarantee> getListGuarantee(int itemPerPage, int pageCurrent, String owner) throws SQLException {
        try {
            ArrayList<Guarantee> list = new ArrayList<>();
            String query = "SELECT * FROM ( "
                    + "SELECT ROW_NUMBER() "
                    + "OVER(ORDER BY id) as Number, "
                    + "* FROM Guaranteee "
                    + ") as Data where Number between ? and ? ";
            if (owner != null) {
                query += " AND [owner] = '" + owner + "' ";
            }
            query += "ORDER BY status ";
            PreparedStatement ps = connection.prepareStatement(query);
            int from = itemPerPage * (pageCurrent - 1) + 1;
            int to = from + itemPerPage - 1;
            ps.setInt(1, from);
            ps.setInt(2, to);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Guarantee g = new Guarantee();
                g.setId(rs.getInt("id"));
                g.setProduct(rs.getString("product"));
                g.setDescription(rs.getString("description"));
                g.setStatus(rs.getString("status"));
                g.setImage(rs.getBlob("image"));
                g.setOwner(rs.getString("owner"));
                g.setPickUpDate(rs.getDate("pick_up_date"));
                g.setFileName(rs.getString("fileName"));
                list.add(g);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Guarantee getById(int id) {
        try {
            String query = "SELECT * FROM Guaranteee WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Guarantee g = new Guarantee();
                g.setId(rs.getInt("id"));
                g.setProduct(rs.getString("product"));
                g.setDescription(rs.getString("description"));
                g.setStatus(rs.getString("status"));
                g.setImage(rs.getBlob("image"));
                g.setOwner(rs.getString("owner"));
                g.setPickUpDate(rs.getDate("pick_up_date"));
                g.setFileName(rs.getString("fileName"));
                return g;
            }
            
        } catch (SQLException e) {
            
        }
        return null;
    }
}
