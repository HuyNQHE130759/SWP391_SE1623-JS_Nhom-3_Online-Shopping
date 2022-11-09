/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Role;
import entity.User;
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

    private RoleDAO roleDAO = new RoleDAO();

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
            stm.setInt(1, u.getCid());
            stm.setString(2, u.getFullName());
            stm.setString(3, u.getAddress());
            stm.setString(4, u.getPhone());
            stm.setString(5, u.getEmail());
            stm.setString(6, u.getUsername());
            stm.setString(7, u.getPassword());
            stm.setBoolean(8, u.isStatus());
            stm.setBoolean(9, u.isMale());
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
            stm.setInt(1, u.getCid());
            stm.setString(2, u.getFullName());
            stm.setString(3, u.getAddress());
            stm.setString(4, u.getPhone());
            stm.setString(5, u.getEmail());
            stm.setBoolean(6, u.isStatus());
            stm.setBoolean(7, u.isMale());
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
                u.setEmail(email);
                u.setAddress(address);
                u.setMale(gender);
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
                u.setEmail(email);
                u.setAddress(address);
                u.setMale(gender);
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
                u.setEmail(email);
                u.setAddress(address);
                u.setMale(gender);
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
    //funtion check login with account google
    public ArrayList checkLogin(String userIDGoogle) {
        ArrayList<User> ul = new ArrayList<>();
        try {
            String strSelect = "select * from dbo.[User] where user_id_google = ?";
            PreparedStatement stm = connection.prepareStatement(strSelect);
            stm.setString(1, userIDGoogle);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setCid(rs.getInt("cid"));
                u.setFullName(rs.getString("fullName"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setStatus(rs.getBoolean("status"));
                u.setEmail(rs.getString("email"));
                ul.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return ul;
    }
    
     //funtion add user with account google
    public void insertWithIDGoogle(User u) {
        String sql = "INSERT INTO [User]\n"
                + "           ([fullName]\n"
                + "           ,[email]\n"
                + "           ,[user_id_google],[roleid])\n"
                + "           VALUES (?,?,?,?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getFullName());
            stm.setString(2, u.getEmail());
            stm.setString(3, u.getUserIDGoogle());
            stm.setInt(4, 1);
       
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
    

    public ArrayList<User> getListUser(String role, String status, String sort, int itemPerPage, int pageCurrent) {

        ArrayList<User> list = new ArrayList<>();
        try {
            // query to get all User from DB
            String query = "SELECT * "
                    + "FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY cid) AS lineNumb "
                    + "FROM (SELECT u.*, r.rolename "
                    + "FROM [User] u "
                    + "JOIN [Role] r ON u.roleid = r.roleid) as sub "
                    + ") as sub2 "
                    + "WHERE lineNumb BETWEEN ? AND ? ";
            // check cac truong hop 
            // role va status blank
            if (role.isEmpty() && !status.isEmpty()) {
                query += "AND status = " + "'" + status + "' ";
            }
            // role co gia tri va status = blank
            if (!role.isEmpty() && status.isEmpty()) {
                query += "AND rolename = " + "'" + role + "' ";
            }
            // ca role va status deu co gia tri
            if (!role.isEmpty() && !status.isEmpty()) {
                query += "AND rolename = " + "'" + role + "' and status = " + "'" + status + "' ";
            }
            //order by
            query += "order by " + "'" + sort + "' ";

            PreparedStatement ps = connection.prepareStatement(query);
            int from = itemPerPage * (pageCurrent - 1) + 1;
            int to = from + itemPerPage - 1;
            ps.setInt(1, from);
            ps.setInt(2, to);
            ResultSet rslt = ps.executeQuery();
            while (rslt.next()) {
                User u = new User();
                u.setCid(rslt.getInt("cid"));
                u.setFullName(rslt.getString("fullName"));
                u.setUsername(rslt.getString("username"));
                u.setMale(rslt.getBoolean("gender"));
                u.setAddress(rslt.getString("address"));
                u.setEmail(rslt.getString("email"));
                u.setPhone(rslt.getString("phone"));
                u.setRole(rslt.getString("rolename"));
                u.setStatus(rslt.getBoolean("status"));
                list.add(u);
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

    public void addUser(User u) throws SQLException {
        try {
            String query = "Insert into [User] values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, roleDAO.getRoleId(u.getRole()));
            ps.setString(2, u.getFullName());
            ps.setString(3, u.getAddress());
            ps.setString(4, u.getPhone());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getUsername());
            ps.setString(7, u.getPassword());
            ps.setBoolean(8, true);
            ps.setBoolean(9, u.isMale());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int getLastUserId() {
        try {
            String query = "select top 1 cid from [User] order by cid desc";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return rslt.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public boolean userNameIsExist(String username) {
        try {
            String query = "select count(*) as num from [User] where username = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return Integer.parseInt(rslt.getString(1)) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean emailIsExist(String email) {
        try {
            String query = "select count(*) as num from [User] where email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return Integer.parseInt(rslt.getString(1)) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public User getUserById(int cid) {
        try {
            User u = new User();
            String query = "select * from [User] WHERE cid = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cid);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                u.setCid(rslt.getInt("cid"));
                u.setFullName(rslt.getString("fullName"));
                u.setUsername(rslt.getString("username"));
                u.setMale(rslt.getBoolean("gender"));
                u.setAddress(rslt.getString("address"));
                u.setEmail(rslt.getString("email"));
                u.setPhone(rslt.getString("phone"));
                u.setRole(roleDAO.getRoleName(rslt.getInt("roleid")));
                u.setStatus(rslt.getBoolean("status"));
            }
            return u;
        } catch (SQLException e) {

        }
        return null;
    }

    public void updateUser(User u) throws SQLException {
        try {
            String query = "UPDATE [User]\n"
                    + "SET roleid = ?, fullName = ?, address = ?, phone = ?, email = ?, username = ?, password = ?, gender = ?\n"
                    + "WHERE cid = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, roleDAO.getRoleId(u.getRole()));
            ps.setString(2, u.getFullName());
            ps.setString(3, u.getAddress());
            ps.setString(4, u.getPhone());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getUsername());
            ps.setString(7, u.getPassword());
            ps.setBoolean(8, u.isMale());
            ps.setInt(9, u.getCid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
