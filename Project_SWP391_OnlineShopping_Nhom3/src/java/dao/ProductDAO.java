/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category;
import entity.Product;
import entity.Provider;
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
public class ProductDAO extends DBContext {

    public ArrayList<Product> getAllProduct(Integer pageindex, Integer pagesize) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT a.[pid],a.[pname],a.[quantity],a.[price],a.[image],a.[description],a.[status],b.[cateId],b.[cateName],c.[provider_id], c.[provider_name]\n"
                + "  FROM [Product] a \n"
                + "  left join Category b on a.cateId = b.cateId\n"
                + "  left join Provider c on a.provider_id = c.provider_id\n"
                + "  order by a.[pid]\n"
                + "  OFFSET (?-1) * ? ROWS\n"
                + "  FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getBoolean("status"));
                Category c = new Category();
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                p.setCategory(c);
                Provider pr = new Provider();
                pr.setProvider_id(rs.getInt("provider_id"));
                pr.setProvider_name(rs.getString("provider_name"));
                p.setProvider(pr);
                list.add(p);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<Product> getAllProduct(String sort, Integer cateId, Integer provider_id, Boolean status, String search, Integer pageindex, Integer pagesize) {
        ArrayList<Product> list = new ArrayList<>();
        HashMap<Integer, Object> params = new HashMap<>();
        int index = 0;
        String sql = "SELECT a.[pid],a.[pname],a.[quantity],a.[price],a.[image],a.[description],a.[status],b.[cateId],b.[cateName],c.[provider_id], c.[provider_name]\n"
                + "  FROM [Product] a \n"
                + "  left join Category b on a.cateId = b.cateId\n"
                + "  left join Provider c on a.provider_id = c.provider_id where 1=1 ";
        if (cateId != null) {
            sql += " and b.cateId = ? ";
            index++;
            params.put(index, cateId);
        }
        if (provider_id != null) {
            sql += " and c.provider_id = ? ";
            index++;
            params.put(index, provider_id);
        }
        if (status != null) {
            sql += " and a.status = ? ";
            index++;
            params.put(index, status);
        }
        if (search != null) {
            search = "%" + search + "%";
            sql += " and (a.pname like ? or a.price like ?) ";
            index++;
            params.put(index, search);
            index++;
            params.put(index, search);
        }
        if (1 == 1) {
            sql += " ORDER BY "+sort+" \n"
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
            System.out.println(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getString("pname"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getBoolean("status"));
                Category c = new Category();
                c.setCateId(rs.getInt("cateId"));
                c.setCateName(rs.getString("cateName"));
                p.setCategory(c);
                Provider pr = new Provider();
                pr.setProvider_id(rs.getInt("provider_id"));
                pr.setProvider_name(rs.getString("provider_name"));
                p.setProvider(pr);
                list.add(p);
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
            String sql = "SELECT COUNT(*) as total FROM [Product];";
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
    
    public void insert(String pname, String img, float price, String description, boolean status, int cateID){
         try {
            String sql = "INSERT INTO [Product]([pname],[quantity],[price],[image],[description],[status],[cateId]) \n"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pname);
            stm.setInt(2, 0);
            stm.setFloat(3, price);
            stm.setString(4, img);
            stm.setString(5, description);
            stm.setBoolean(6, status);
            stm.setInt(7, cateID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void update(Integer pid, String pname, String img, float price, String description, boolean status, int cateID){
         try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET \n"
                    + "      [pname] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[status] = ?\n"
                    + "      ,[cateId] = ?\n"
                    + " WHERE [pid] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(7, pid);
            stm.setString(1, pname);
            stm.setFloat(2, price);
            stm.setString(3, img);
            stm.setString(4, description);
            stm.setBoolean(5, status);
            stm.setInt(6, cateID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

