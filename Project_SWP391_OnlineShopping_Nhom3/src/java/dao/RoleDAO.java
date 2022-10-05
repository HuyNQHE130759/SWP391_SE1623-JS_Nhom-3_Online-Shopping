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
}
