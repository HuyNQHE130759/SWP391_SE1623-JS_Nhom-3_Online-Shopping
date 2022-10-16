/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category;
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
public class CategoryDAO extends DBContext {

    public ArrayList<Category> getAllCategory(Integer pageindex, Integer pagesize) {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT [cateId]\n"
                + "      ,[cateName]\n"
                + "      ,[image]\n"
                + "      ,[status]\n"
                + "  FROM [Category]\n"
                + "  order by [cateId]\n"
                + "  OFFSET (?-1) * ? ROWS\n"
                + "  FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                c.setImage(rs.getString("image"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Category> getAllCategory(String sort, Boolean status, String search, Integer pageindex, Integer pagesize) {
        ArrayList<Category> list = new ArrayList<>();
        HashMap<Integer, Object> params = new HashMap<>();
        int index = 0;
        String sql = "SELECT [cateId]\n"
                + "      ,[cateName]\n"
                + "      ,[image]\n"
                + "      ,[status]\n"
                + "  FROM [Category] where 1=1 ";
        if (status != null) {
            sql += " and a.status = ? ";
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
            sql += " ORDER BY " + sort + " \n"
                    + "OFFSET (?-1) * ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY";
            index++;
            params.put(index, pageindex);
            index++;
            params.put(index, pagesize);
            index++;
            params.put(index, pagesize);
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer position = entry.getKey();
                Object value = entry.getValue();
                stm.setObject(position, value);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                c.setImage(rs.getString("image"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sql);
        return list;
    }

    public int count() {
        try {
            String sql = "SELECT COUNT(*) as total FROM [Category];";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void insert(String cname, String img, boolean status) {
        try {
            String sql = "INSERT INTO [dbo].[Category]\n"
                    + "           ,[cateName]\n"
                    + "           ,[image]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cname);
            stm.setString(2, img);
            stm.setBoolean(3, status);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int cid, String cname, String img, boolean status) {
        try {
            String sql = "UPDATE [Category]\n"
                    + "   SET \n"
                    + "      [cateName] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE [cateId] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cname);
            stm.setString(2, img);
            stm.setBoolean(3, status);
            stm.setInt(4, cid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Category getCategory(Integer cid) {
        Category c = new Category();
        String sql = "SELECT [cateId]\n"
                + "      ,[cateName]\n"
                + "      ,[image]\n"
                + "      ,[status]\n"
                + "  FROM [Category]\n"
                + "  WHERE [cateId] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setObject(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                c.setImage(rs.getString("image"));
                c.setStatus(rs.getBoolean("status"));
            }
            rs.close();
            stm.close();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
