package dao;

import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO extends DBContext {

    public ArrayList<Category> getAllCategory() throws SQLException {
        try {
            ArrayList<Category> list = new ArrayList<>();
            String query = "SELECT * FROM CATEGORY WHERE [STATUS] = 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rslt = ps.executeQuery();
            while (rslt.next()) {
                Category c = new Category();
                c.setCateId(rslt.getString("cateId"));
                c.setCateName(rslt.getString("cateName"));
                c.setImage(rslt.getString("image"));
                c.setStatus(rslt.getBoolean("status"));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    public String getCategoryNameById(int categoryId) throws SQLException {
        try {
            String query = "SELECT cateName FROM CATEGORY WHERE cateId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return rslt.getString("cateName");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
        return null;
    }
}
