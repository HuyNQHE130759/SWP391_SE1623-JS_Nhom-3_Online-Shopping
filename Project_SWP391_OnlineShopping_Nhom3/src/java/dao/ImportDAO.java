/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Import;
import entity.Product1;
import entity.Provider;
import java.sql.Connection;
import java.sql.Date;
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
public class ImportDAO implements ImportDAOInterface {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public ArrayList<Import> getAllImport() {
        ArrayList<Import> list = new ArrayList<>();
        String sql = "SELECT [importid]"
                + "      ,[pid]"
                + "      ,[provider_id]"
                + "      ,[quantity]"
                + "      ,[date]"
                + "  FROM [Import History]";
        try {
            connection = (new DBContext().connection);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Import imp = new Import();
                imp.setImportId(rs.getInt("importid"));
                Product1 pd = new Product1();
                pd.setPid(rs.getInt("pid"));
                imp.setProduct(pd);
                Provider pv = new Provider();
                pv.setProvider_id(rs.getInt("provider_id"));
                imp.setProvider(pv);
                imp.setQuantity(rs.getInt("quantity"));
                imp.setDate(rs.getDate("date"));
                list.add(imp);
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
    public ArrayList<Import> getAllImport(Integer pageindex, Integer pagesize) {
        ArrayList<Import> list = new ArrayList<>();
        String sql = "SELECT a.[importid], b.[pid], b.[pname], c.[provider_id], c.[provider_name], a.[quantity], a.[date] FROM [Import History] a"
                + "  left join [Product] b on b.pid = a.pid"
                + "  left join [Provider] c on a.provider_id = c.provider_id"
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
                Import imp = new Import();
                imp.setImportId(rs.getInt("importid"));
                Product1 pd = new Product1();
                pd.setPid(rs.getInt("pid"));
                pd.setPname(rs.getString("pname"));
                imp.setProduct(pd);
                Provider pv = new Provider();
                pv.setProvider_id(rs.getInt("provider_id"));
                pv.setProvider_name(rs.getString("provider_name"));
                imp.setProvider(pv);
                imp.setQuantity(rs.getInt("quantity"));
                imp.setDate(rs.getDate("date"));
                list.add(imp);
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
    public ArrayList<Import> getAllImport(String sort, Integer provider_id, Date from, Date to, String search, Integer pageindex, Integer pagesize) {
        ArrayList<Import> list = new ArrayList<>();
        HashMap<Integer, Object> params = new HashMap<>();
        int index = 0;
        String sql = "SELECT a.[importid], b.[pid], b.[pname], c.[provider_id], c.[provider_name], a.[quantity], a.[date] FROM [Import History] a"
                + "  left join [Product] b on b.pid = a.pid"
                + "  left join [Provider] c on a.provider_id = c.provider_id where 1=1 ";
        if (provider_id != null) {
            sql += " and c.[provider_id] = ? ";
            index++;
            params.put(index, provider_id);
        }
        if (from != null) {
            sql += " and a.[date]] >= ? ";
            index++;
            params.put(index, from);
        }
        if (to != null) {
            sql += " and a.[date] <= ? ";
            index++;
            params.put(index, to);
        }
        if (search != null) {
            search = "%" + search + "%";
            sql += " and (b.[pname] like ? or c.[provider_name] like ?) ";
            index++;
            params.put(index, search);
            index++;
            params.put(index, search);
        }
        if (1 == 1) {
            sql += " ORDER BY " + sort + "  "
                    + "OFFSET (?-1) * ? ROWS  "
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
            System.out.println(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer position = entry.getKey();
                Object value = entry.getValue();
                ps.setObject(position, value);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Import imp = new Import();
                imp.setImportId(rs.getInt("importid"));
                Product1 pd = new Product1();
                pd.setPid(rs.getInt("pid"));
                pd.setPname(rs.getString("pname"));
                imp.setProduct(pd);
                Provider pv = new Provider();
                pv.setProvider_id(rs.getInt("provider_id"));
                pv.setProvider_name(rs.getString("provider_name"));
                imp.setProvider(pv);
                imp.setQuantity(rs.getInt("quantity"));
                imp.setDate(rs.getDate("date"));
                list.add(imp);
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
        System.out.println("Import size: " +list.size());
        return list;
    }

    @Override
    public int count() {
        connection = (new DBContext().connection);
        try {
            String sql = "SELECT COUNT(*) as total FROM [Import History];";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    @Override
    public void insert(Product1 product, Provider provider, Integer quantity, Date date) {
        try {
            connection = (new DBContext().connection);
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Import History]([pid],[provider_id],[quantity],[date])"
                    + "     VALUES(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, product.getPid());
            ps.setInt(2, provider.getProvider_id());
            ps.setInt(3, quantity);
            ps.setDate(4, date);
            ps.executeUpdate();
            String add_quantity_sql = "UPDATE [Product] "
                    + "SET [quantity] = [quantity] + ? "
                    + "WHERE [pid] = ? ";
            PreparedStatement ps_quantity = connection.prepareStatement(add_quantity_sql);
            ps_quantity.setInt(1, quantity);
            ps_quantity.setInt(2, product.getPid());
            ps_quantity.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Import getImportById(int importID) {
        Import imp = new Import();
        String sql = "SELECT [importid]"
                + "      ,[pid]"
                + "      ,[provider_id]"
                + "      ,[quantity]"
                + "      ,[date]"
                + "  FROM [Import History]"
                + "  WHERE importid = ?";
        try {
            connection = (new DBContext().connection);
            ps = connection.prepareStatement(sql);
            ps.setInt(1, importID);
            rs = ps.executeQuery();
            while (rs.next()) {
                imp.setImportId(rs.getInt("importid"));
                Product1 pd = new Product1();
                pd.setPid(rs.getInt("pid"));
                imp.setProduct(pd);
                Provider pv = new Provider();
                pv.setProvider_id(rs.getInt("provider_id"));
                imp.setProvider(pv);
                imp.setQuantity(rs.getInt("quantity"));
                imp.setDate(rs.getDate("date"));
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
        return imp;
    }

}
