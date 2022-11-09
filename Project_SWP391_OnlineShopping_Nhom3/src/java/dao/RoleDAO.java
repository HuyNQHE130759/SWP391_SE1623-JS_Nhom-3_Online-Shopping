/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author apc
 */
public class RoleDAO extends DBContext {

    public ArrayList<Role> getAllRoles() {
        ArrayList<Role> list = new ArrayList<>();
        String sql = "Select roleid, rolename FROM Role";
        Role r;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                r = new Role();
                r.setRoleId(rs.getInt("roleid"));
                r.setRname(rs.getString("rolename"));
                list.add(r);
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public int getRoleId(String rolename) {
        try {
            String query = "select roleid from [Role] where rolename = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, rolename);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return rslt.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 1;
    }

    public String getRoleName(int roleid) {
        try {
            String query = "select rolename from [Role] where roleid = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, roleid);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return rslt.getString(1);
            }
        } catch (SQLException e) {
        }
        return "";
    }
    
    public String getRoleNameByUser(String username) {
        try {
            String query = "select r.rolename from [Role] r "
                    + "join [User] u on r.roleid = u.roleid "
                    + "where u.username = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return rslt.getString("rolename");
            }
        } catch (SQLException e) {
        }
        return "";
    }
}
