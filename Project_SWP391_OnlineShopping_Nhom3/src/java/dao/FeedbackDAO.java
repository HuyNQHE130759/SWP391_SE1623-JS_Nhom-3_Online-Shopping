/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category;
import entity.Feedback;
import entity.Product;
import entity.Provider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynq
 */
public class FeedbackDAO extends DBContext {

    public ArrayList<Feedback> getAllFeedback(String status, String pid, String rating, Integer pageindex, Integer pagesize) {
        ArrayList<Feedback> list = new ArrayList<>();
        String sql = "select * from Review r, [User] u, Product p\n"
                + "where r.cid = u.cid and p.pid = r.pid \n"
                + "and r.[status] like ? and r.pid like ? and r.user_rating like ? \n"
                + "order by r.reviewid asc\n"
                + "OFFSET (?-1) * ? ROWS FETCH NEXT ? ROWS ONLY ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" +status + "%");
            stm.setString(2, "%" +pid + "%");
            stm.setString(3, "%" +rating + "%");
            stm.setInt(4, pageindex);
            stm.setInt(5, pagesize);
            stm.setInt(6, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback();
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
