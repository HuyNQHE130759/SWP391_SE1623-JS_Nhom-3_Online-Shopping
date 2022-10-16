/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
public class ProviderDAO extends DBContext {

    public ArrayList<Provider> getAllProvider(Integer pageindex, Integer pagesize) {
        ArrayList<Provider> list = new ArrayList<>();
        String sql = "SELECT [provider_id]\n"
                + "      ,[provider_name]\n"
                + "      ,[provider_email]\n"
                + "      ,[provider_address]\n"
                + "      ,[provider_status]\n"
                + "  FROM [dbo].[Provider]\n"
                + "  order by [provider_id]\n"
                + "  OFFSET (?-1) * ? ROWS\n"
                + "  FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Provider p = new Provider();
                p.setProvider_id(rs.getInt("provider_id"));
                p.setProvider_name(rs.getString("provider_name"));
                p.setProvider_email(rs.getString("provider_email"));
                p.setProvider_address("provider_address");
                p.setStatus(rs.getBoolean("provider_status"));
                list.add(p);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Provider> getAllProvider(String sort, Boolean status, String search, Integer pageindex, Integer pagesize) {
        ArrayList<Provider> list = new ArrayList<>();
        HashMap<Integer, Object> params = new HashMap<>();
        int index = 0;
        String sql = "SELECT [provider_id]\n"
                + "      ,[provider_name]\n"
                + "      ,[provider_email]\n"
                + "      ,[provider_address]\n"
                + "      ,[provider_status]\n"
                + "  FROM [dbo].[Provider] where 1=1 ";
        if (status != null) {
            sql += " and [provider_status] = ? ";
            index++;
            params.put(index, status);
        }
        if (search != null) {
            search = "%" + search + "%";
            sql += " and ([provider_name] like ? or [provider_address] like ?) ";
            index++;
            params.put(index, search);
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
                Provider p = new Provider();
                p.setProvider_id(rs.getInt("provider_id"));
                p.setProvider_name(rs.getString("provider_name"));
                p.setProvider_email(rs.getString("provider_email"));
                p.setProvider_address("provider_address");
                p.setStatus(rs.getBoolean("provider_status"));
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
            String sql = "SELECT COUNT(*) as total FROM [Provider];";
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

    public void insert(String pname, String email, String address, boolean status) {
        try {
            String sql = "INSERT INTO [Provider]\n"
                    + "           ([provider_name]\n"
                    + "           ,[provider_email]\n"
                    + "           ,[provider_address]\n"
                    + "           ,[provider_status])\n"
                    + "     VALUES (?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pname);
            stm.setString(2, email);
            stm.setString(3, address);
            stm.setBoolean(4, status);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int pid, String pname, String email, String address, boolean status) {
        try {
            String sql = "UPDATE [Provider]\n"
                    + "   SET [provider_name] = ?\n"
                    + "      ,[provider_email] = ?\n"
                    + "      ,[provider_address] = ?\n"
                    + "      ,[provider_status] = ?\n"
                    + " WHERE [provider_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pname);
            stm.setString(2, email);
            stm.setString(3, address);
            stm.setBoolean(4, status);
            stm.setInt(5, pid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Provider getProvider(Integer pid) {
        Provider p = new Provider();
        String sql = "SELECT [provider_id]\n"
                + "      ,[provider_name]\n"
                + "      ,[provider_email]\n"
                + "      ,[provider_address]\n"
                + "      ,[provider_status]\n"
                + "  FROM [Provider]\n"
                + "  WHERE [provider_id] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setObject(1, pid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                p.setProvider_id(rs.getInt("provider_id"));
                p.setProvider_name(rs.getString("provider_name"));
                p.setProvider_email(rs.getString("provider_email"));
                p.setProvider_address("provider_address");
                p.setStatus(rs.getBoolean("status"));
            }
            rs.close();
            stm.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
