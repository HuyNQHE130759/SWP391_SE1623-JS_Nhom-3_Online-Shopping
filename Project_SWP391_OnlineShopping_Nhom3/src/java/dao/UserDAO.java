/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Role;
import entity.User;
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
public class UserDAO extends DBContext {

    public void insert(User u) {
        String sql = "INSERT INTO [User]\n"
                + "           ([roleid]\n"
                + "           ,[fullName]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[status]\n"
                + "           ,[gender]) VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, u.getRole().getRoleId());
            stm.setString(2, u.getFullName());
            stm.setString(3, u.getAddress());
            stm.setString(4, u.getPhone());
            stm.setString(5, u.getEmail());
            stm.setString(6, u.getUsername());
            stm.setString(7, u.getPassword());
            stm.setBoolean(8, u.isStatus());
            stm.setBoolean(9, u.isGender());
            stm = connection.prepareStatement(sql);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(User u) {
        String sql = "UPDATE [User]\n"
                + "   SET [roleid] = ?\n"
                + "      ,[fullName] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[gender] = ?\n"
                + " WHERE [cid] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, u.getRole().getRoleId());
            stm.setString(2, u.getFullName());
            stm.setString(3, u.getAddress());
            stm.setString(4, u.getPhone());
            stm.setString(5, u.getEmail());
            stm.setBoolean(6, u.isStatus());
            stm.setBoolean(7, u.isGender());
            stm.setInt(8, u.getCid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public User getUserbyId(int cId) {
        String sql = "SELECT [cid]\n"
                + "      ,[roleid]\n"
                + "      ,[fullName]\n"
                + "      ,[address]\n"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[status]\n"
                + "      ,[gender]\n"
                + "  FROM [User] where cId =?";
        String email, username, fullname, password, phone, address, roleName;
        int roleId;
        boolean gender, status;
        User u = null;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cId = rs.getInt("cId");
                email = rs.getString("email");
                fullname = rs.getString("fullname");
                phone = rs.getString("phone");
                address = rs.getString("address");
                roleId = rs.getInt("roleid");
                roleName = rs.getString("rolename");
                username = rs.getString("username");
                password = rs.getString("password");
                gender = rs.getBoolean("gender");
                status = rs.getBoolean("status");
                u = new User();
                u.setCid(cId);
                u.setFullName(fullname);
                u.setUsername(username);
                Role r = new Role();
                r.setRoleId(roleId);
                r.setRname(roleName);
                u.setRole(r);
                u.setEmail(email);
                u.setAddress(address);
                u.setGender(gender);
                u.setPhone(phone);
                u.setStatus(status);
                u.setPassword(password);
            }
            connection.close();
        } catch (SQLException ex) { 
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public ArrayList<User> getAllUser(Integer pageindex, Integer pagesize) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT a.cid, b.roleid, b.rolename, a.fullName ,a.address, a.phone, a.email, a.username, a.password, a.status, a.gender\n"
                + "FROM [User] a\n"
                + "left join Role b on a.roleid = b.roleid\n"
                + "ORDER BY a.cid\n"
                + "OFFSET (?-1) * ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY";
        String email, username, fullname, password, phone, address, roleName;
        int roleId, cId;
        boolean gender, status;
        User u;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cId = rs.getInt("cId");
                email = rs.getString("email");
                fullname = rs.getString("fullname");
                phone = rs.getString("phone");
                address = rs.getString("address");
                roleId = rs.getInt("roleid");
                roleName = rs.getString("rolename");
                username = rs.getString("username");
                password = rs.getString("password");
                gender = rs.getBoolean("gender");
                status = rs.getBoolean("status");
                u = new User();
                u.setCid(cId);
                u.setFullName(fullname);
                u.setUsername(username);
                Role r = new Role();
                r.setRoleId(roleId);
                r.setRname(roleName);
                u.setRole(r);
                u.setEmail(email);
                u.setAddress(address);
                u.setGender(gender);
                u.setPhone(phone);
                u.setStatus(status);
                u.setPassword(password);
                list.add(u);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) { 
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<User> filterUser(String sort, Integer roleId, Boolean status, String search, Integer pageindex, Integer pagesize) {
        ArrayList<User> list = new ArrayList<>();
        HashMap<Integer, Object> params = new HashMap<>();
        int index = 0;
        String email, username, fullname, password, phone, address, roleName;
        int role, cId;
        boolean gender, cStatus;
        User u;
        String sql = "SELECT a.cid, b.roleid, b.rolename, a.fullName ,a.address, a.phone, a.email, a.username, a.password, a.status, a.gender\n"
                + "FROM [User] a\n"
                + "left join Role b on a.roleid = b.roleid where 1=1 ";
        if (roleId != null) {
            sql += " and b.roleid = ? ";
            index++;
            params.put(index, roleId);
        }
        if (status != null) {
            sql += " and a.status = ? ";
            index++;
            params.put(index, status);
        }
        if (search != null) {
            search = "%" + search + "%";
            sql += " and (a.fullName like ? or a.email like ? or a.phone like ?) ";
            index++;
            params.put(index, search);
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
                cId = rs.getInt("cId");
                email = rs.getString("email");
                fullname = rs.getString("fullname");
                phone = rs.getString("phone");
                address = rs.getString("address");
                role = rs.getInt("roleid");
                roleName = rs.getString("rolename");
                username = rs.getString("username");
                password = rs.getString("password");
                gender = rs.getBoolean("gender");
                cStatus = rs.getBoolean("status");
                u = new User();
                u.setCid(cId);
                u.setFullName(fullname);
                u.setUsername(username);
                Role r = new Role();
                r.setRoleId(role);
                r.setRname(roleName);
                u.setRole(r);
                u.setEmail(email);
                u.setAddress(address);
                u.setGender(gender);
                u.setPhone(phone);
                u.setStatus(cStatus);
                u.setPassword(password);
                list.add(u);
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
            String sql = "SELECT COUNT(*) as total FROM [User];";
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

}
