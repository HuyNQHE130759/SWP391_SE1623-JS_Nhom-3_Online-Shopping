/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category;
import entity.Product;
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
public class ProductDAO implements ProductDAOInterface {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public ArrayList<Product> getAllProduct(Integer pageindex, Integer pagesize) {
        connection = (new DBContext().connection);
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT a.[pid],a.[pname],a.[quantity],a.[price],a.[image],a.[description],a.[status],b.[cateId],b.[cateName],c.[provider_id], c.[provider_name]"
                + "  FROM [Product] a"
                + "  left join Category b on a.cateId = b.cateId"
                + "  left join Provider c on a.provider_id = c.provider_id"
                + "  order by a.[pid] "
                + "  OFFSET (?-1) * ? ROWS "
                + "  FETCH NEXT ? ROWS ONLY";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pageindex);
            ps.setInt(2, pagesize);
            ps.setInt(3, pagesize);
            rs = ps.executeQuery();
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
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ArrayList<Product> getAllProduct(String sort, Integer cateId, Integer provider_id, Boolean status, String search, Integer pageindex, Integer pagesize) {
        connection = (new DBContext().connection);
        ArrayList<Product> list = new ArrayList<>();
        HashMap<Integer, Object> params = new HashMap<>();
        int index = 0;
        String sql = "SELECT a.[pid],a.[pname],a.[quantity],a.[price],a.[image],a.[description],a.[status],b.[cateId],b.[cateName],c.[provider_id], c.[provider_name] "
                + "  FROM [Product] a  "
                + "  left join Category b on a.cateId = b.cateId "
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
            ps = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer position = entry.getKey();
                Object value = entry.getValue();
                ps.setObject(position, value);
            }
            System.out.println(sql);
            rs = ps.executeQuery();
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
        return list;
    }

    @Override
    public int count() {
        connection = (new DBContext().connection);
        try {
            String sql = "SELECT COUNT(*) as total FROM [Product];";
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
    public void insert(String pname, String img, float price, String description, boolean status, int cateID) {
        connection = (new DBContext().connection);
        try {
            String sql = "INSERT INTO [Product]([pname],[quantity],[price],[image],[description],[status],[cateId]) \n"
                    + "VALUES (?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setInt(2, 0);
            ps.setFloat(3, price);
            ps.setString(4, img);
            ps.setString(5, description);
            ps.setBoolean(6, status);
            ps.setInt(7, cateID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Integer pid, String pname, String img, float price, String description, boolean status, int cateID) {
        connection = (new DBContext().connection);
        try {
            String sql = "UPDATE [dbo].[Product] "
                    + "   SET "
                    + "      [pname] = ?"
                    + "      ,[price] = ?"
                    + "      ,[image] = ?"
                    + "      ,[description] = ?"
                    + "      ,[status] = ?"
                    + "      ,[cateId] = ?"
                    + " WHERE [pid] = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(7, pid);
            ps.setString(1, pname);
            ps.setFloat(2, price);
            ps.setString(3, img);
            ps.setString(4, description);
            ps.setBoolean(5, status);
            ps.setInt(6, cateID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Product getProductById(int pid) {
        Product p = new Product();
        try {
            String strSelect = "SELECT a.[pid],a.[pname],a.[quantity],a.[price],a.[image],a.[description],a.[status],b.[cateId],b.[cateName],c.[provider_id], c.[provider_name] "
                    + "  FROM [Product] a  "
                    + "  left join Category b on a.cateId = b.cateId "
                    + "  left join Provider c on a.provider_id = c.provider_id where pid = ?";
            ps.setInt(1, pid);
            rs = ps.executeQuery(strSelect);
            while (rs.next()) {
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        connection = (new DBContext().connection);
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT a.[pid],a.[pname],a.[quantity],a.[price],a.[image],a.[description],a.[status],b.[cateId],b.[cateName],c.[provider_id], c.[provider_name]"
                + "  FROM [Product] a"
                + "  left join Category b on a.cateId = b.cateId"
                + "  left join Provider c on a.provider_id = c.provider_id";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
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
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
}
