/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apc
 */
public class CategoryDAO implements CategoryDAOInterface {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public ArrayList<Category1> getAllCategory() {
        ArrayList<Category1> list = new ArrayList<>();
        String sql = "SELECT [cateId]"
                + "      ,[cateName]"
                + "      ,[image]"
                + "      ,[status]"
                + "  FROM [Category]";
        try {
            connection = (new DBContext().connection);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category1 c = new Category1();
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                c.setImage(rs.getString("image"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ArrayList<Category1> getAllCategory(Integer pageindex, Integer pagesize) {
        ArrayList<Category1> list = new ArrayList<>();
        String sql = "SELECT [cateId]"
                + "      ,[cateName]"
                + "      ,[image]"
                + "      ,[status]"
                + "  FROM [Category]"
                + "  order by [cateId]"
                + "  OFFSET (?-1) * ? ROWS"
                + "  FETCH NEXT ? ROWS ONLY";
        try {
            connection = (new DBContext().connection);
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pageindex);
            ps.setInt(2, pagesize);
            ps.setInt(3, pagesize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category1 c = new Category1();
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                c.setImage(rs.getString("image"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ArrayList<Category1> getAllCategory(String sort, Boolean status, String search, Integer pageindex, Integer pagesize) {
        ArrayList<Category1> list = new ArrayList<>();
        HashMap<Integer, Object> params = new HashMap<>();
        int index = 0;
        String sql = "SELECT [cateId]"
                + "      ,[cateName]"
                + "      ,[image]"
                + "      ,[status]"
                + "  FROM [Category] where 1=1 ";
        if (status != null) {
            sql += " and status = ? ";
            index++;
            params.put(index, status);
        }
        if (search != null) {
            search = "%" + search + "%";
            sql += " and (cateName like ?) ";
            index++;
            params.put(index, search);
        }
        if (1 == 1) {
            sql += " ORDER BY " + sort + " "
                    + "OFFSET (?-1) * ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            index++;
            params.put(index, pageindex);
            index++;
            params.put(index, pagesize);
            index++;
            params.put(index, pagesize);
        }
        try {
            connection = (new DBContext().connection);
            ps = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer position = entry.getKey();
                Object value = entry.getValue();
                ps.setObject(position, value);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Category1 c = new Category1();
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                c.setImage(rs.getString("image"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public int count() {
        try {
            connection = (new DBContext().connection);
            String sql = "SELECT COUNT(*) as total FROM [Category];";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    @Override
    public void insert(String cname, String img, boolean status) {
        try {
            connection = (new DBContext().connection);
            String sql = "INSERT INTO [dbo].[Category]"
                    + "           ,[cateName]"
                    + "           ,[image]"
                    + "           ,[status])"
                    + "     VALUES"
                    + "           (?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cname);
            ps.setString(2, img);
            ps.setBoolean(3, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(int cid, String cname, String img, boolean status) {
        try {
            connection = (new DBContext().connection);
            String sql = "UPDATE [Category]"
                    + "   SET "
                    + "      [cateName] = ?"
                    + "      ,[image] = ?"
                    + "      ,[status] = ?"
                    + " WHERE [cateId] = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cname);
            ps.setString(2, img);
            ps.setBoolean(3, status);
            ps.setInt(4, cid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Category1 getCategory(Integer cid) {
        Category1 c = new Category1();
        String sql = "SELECT [cateId]"
                + "      ,[cateName]"
                + "      ,[image]"
                + "      ,[status]"
                + "  FROM [Category]"
                + "  WHERE [cateId] = ?";
        try {
            connection = (new DBContext().connection);
            ps = connection.prepareStatement(sql);
            ps.setObject(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                c.setImage(rs.getString("image"));
                c.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }

    @Override
    public boolean isExisted(String cname) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = (new DBContext().connection);
        try {
            String query = "select count(*) as num from [Category] where cateName = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, cname);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString(1)) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }
}
