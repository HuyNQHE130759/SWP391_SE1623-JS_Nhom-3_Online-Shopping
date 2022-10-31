/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Provider;
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
public class ProviderDAO implements ProviderDAOInterface {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public ArrayList<Provider> getAllProvider() {
        ArrayList<Provider> list = new ArrayList<>();
        String sql = "SELECT [provider_id]"
                + "      ,[provider_name]"
                + "      ,[provider_email]"
                + "      ,[provider_address]"
                + "      ,[provider_status]"
                + "  FROM [dbo].[Provider]";
        try {
            connection = (new DBContext().connection);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Provider p = new Provider();
                p.setProvider_id(rs.getInt("provider_id"));
                p.setProvider_name(rs.getString("provider_name"));
                p.setProvider_email(rs.getString("provider_email"));
                p.setProvider_address(rs.getString("provider_address"));
                p.setStatus(rs.getBoolean("provider_status"));
                list.add(p);
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
    public ArrayList<Provider> getAllProvider(Integer pageindex, Integer pagesize) {
        ArrayList<Provider> list = new ArrayList<>();
        String sql = "SELECT [provider_id]"
                + "      ,[provider_name]"
                + "      ,[provider_email]"
                + "      ,[provider_address]"
                + "      ,[provider_status]"
                + "  FROM [dbo].[Provider]"
                + "  order by [provider_id]"
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
                Provider p = new Provider();
                p.setProvider_id(rs.getInt("provider_id"));
                p.setProvider_name(rs.getString("provider_name"));
                p.setProvider_email(rs.getString("provider_email"));
                p.setProvider_address(rs.getString("provider_address"));
                p.setStatus(rs.getBoolean("provider_status"));
                list.add(p);
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
    public ArrayList<Provider> getAllProvider(String sort, Boolean status, String search, Integer pageindex, Integer pagesize) {
        ArrayList<Provider> list = new ArrayList<>();
        HashMap<Integer, Object> params = new HashMap<>();
        int index = 0;
        String sql = "SELECT [provider_id]"
                + "      ,[provider_name]"
                + "      ,[provider_email]"
                + "      ,[provider_address]"
                + "      ,[provider_status]"
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
                Provider p = new Provider();
                p.setProvider_id(rs.getInt("provider_id"));
                p.setProvider_name(rs.getString("provider_name"));
                p.setProvider_email(rs.getString("provider_email"));
                p.setProvider_address(rs.getString("provider_address"));
                p.setStatus(rs.getBoolean("provider_status"));
                list.add(p);
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
    public int count() {
        try {
            connection = (new DBContext().connection);
            String sql = "SELECT COUNT(*) as total FROM [Provider];";
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
    public void insert(String pname, String email, String address, boolean status) {
        try {
            connection = (new DBContext().connection);
            String sql = "INSERT INTO [Provider]"
                    + "           ([provider_name]"
                    + "           ,[provider_email]"
                    + "           ,[provider_address]"
                    + "           ,[provider_status])"
                    + "     VALUES (?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setBoolean(4, status);
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
    public void update(int pid, String pname, String email, String address, boolean status) {
        try {
            connection = (new DBContext().connection);
            String sql = "UPDATE [Provider]"
                    + "   SET [provider_name] = ?"
                    + "      ,[provider_email] = ?"
                    + "      ,[provider_address] = ?"
                    + "      ,[provider_status] = ?"
                    + " WHERE [provider_id] = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setBoolean(4, status);
            ps.setInt(5, pid);
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
    public Provider getProvider(Integer pid) {
        Provider p = new Provider();
        String sql = "SELECT [provider_id]"
                + "      ,[provider_name]"
                + "      ,[provider_email]"
                + "      ,[provider_address]"
                + "      ,[provider_status]"
                + "  FROM [Provider]"
                + "  WHERE [provider_id] = ?";
        try {
            connection = (new DBContext().connection);
            ps = connection.prepareStatement(sql);
            ps.setObject(1, pid);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setProvider_id(rs.getInt("provider_id"));
                p.setProvider_name(rs.getString("provider_name"));
                p.setProvider_email(rs.getString("provider_email"));
                p.setProvider_address(rs.getString("provider_address"));
                p.setStatus(rs.getBoolean("provider_status"));
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
        return p;
    }
}
