/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category1;
import entity.Feedback;
import entity.Product1;
import entity.Provider;
import entity.Review;
import entity.Role;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class to access database by query and get or insert or edit data in
 * table review
 *
 * @author Huynq
 */
public class FeedbackDAO extends DBContext {
private PreparedStatement ps = null;
    private ResultSet rs = null;
    /**
     * get list of feedback by parameter filter
     *
     * @param status
     * @param pid product id
     * @param rating
     * @param pageindex
     * @param pagesize
     * @return ArrayList<Feedback>
     */
    public ArrayList<Feedback> getAllFeedback(String status, String pid, String rating, Integer pageindex, Integer pagesize) {
        ArrayList<Feedback> list = new ArrayList<>();
        String sql = "select * from Review r, [User] u, Product p\n"
                + "where r.cid = u.cid and p.pid = r.pid \n"
                + "and r.[status] like ? and r.pid like ? and r.user_rating like ? \n"
                + "order by r.reviewid asc\n"
                + "OFFSET (?-1) * ? ROWS FETCH NEXT ? ROWS ONLY ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + status + "%");
            stm.setString(2, "%" + pid + "%");
            stm.setString(3, "%" + rating + "%");
            stm.setInt(4, pageindex);
            stm.setInt(5, pagesize);
            stm.setInt(6, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setReviewid(rs.getInt(1));
                f.setCid(rs.getInt(2));
                f.setPid(rs.getInt(3));
                f.setImg(rs.getString(4));
                f.setUser_comment(rs.getString(5));
                f.setUser_rating(rs.getInt(6));
                f.setUser_timecomment(rs.getDate(7));
                f.setStatus(rs.getBoolean(8));
                User u = new User();
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString(14));
                u.setPhone(rs.getString("phone"));
                f.setUser(u);
                Product1 p = new Product1();
                p.setPname(rs.getString("pname"));
                f.setProduct(p);
                list.add(f);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * *
     * Use for get detail of feedback
     *
     * @param fid feedback id
     * @returnFeedback
     */
    public Feedback getFeedback(String fid) {
        String sql = "	select * from Review r, [User] u, Product p where r.cid = u.cid and p.pid = r.pid and r.reviewid = " + fid;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setReviewid(rs.getInt(1));
                f.setCid(rs.getInt(2));
                f.setPid(rs.getInt(3));
                f.setImg(rs.getString(4));
                f.setUser_comment(rs.getString(5));
                f.setUser_rating(rs.getInt(6));
                f.setUser_timecomment(rs.getDate(7));
                f.setStatus(rs.getBoolean(8));
                User u = new User();
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString(14));
                u.setPhone(rs.getString("phone"));
                f.setUser(u);
                Product1 p = new Product1();
                p.setPname(rs.getString("pname"));
                f.setProduct(p);
                return f;
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * *
     * Use for change status of feedback
     *
     * @param fid feedback id
     * @param status
     */
    public void updatestatus(String fid, String status) {
        try {
            String sql = "update Review set [status] = ? where  reviewid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, fid);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Use for update feedback
     *
     * @param comment
     * @param rating
     * @param img
     * @param status
     * @param fid feedback id
     */
    public void updateFeedback(String comment, String rating, String img, String status, String fid) {
        try {
            String sql = "update Review set [user_comment] = ? ,[user_rating] = ?, img = ?  ,[status]= ? where  reviewid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, comment);
            stm.setString(2, rating);
            stm.setString(3, img);
            stm.setString(4, status);
            stm.setString(5, fid);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /**
     * Add new feedback
     *
     * @param cid customer id
     * @param pid product id
     * @param comment
     * @param rating
     * @param img image
     */
    public void insertFeedback(String cid, String pid, String comment, String rating, String img) {
        try {
            String sql = "insert into Review ([cid] ,[pid] ,[user_comment] ,[user_rating],img ,[user_timecomment] ,[status])\n"
                    + " values (?,?,?,?,?, GETDATE(), 1);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cid);
            stm.setString(2, pid);
            stm.setString(3, comment);
            stm.setString(4, rating);
            stm.setString(5, img);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * delete feedback by feedback id
     *
     * @param fid feedback id
     */
    public void deleteFeedback(String fid) {
        try {
            String sql = "delete Review where  reviewid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, fid);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Get all User with paging
     *
     * @param pageindex
     * @param pagesize
     * @return
     */
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
    
    public ArrayList<Review> getReviewByID(String pid, String hide, String rate, String name) {

        ArrayList<Review> list_review = new ArrayList<>();
        try {
            String sql = "select r.*, p.pname,p.image,u.fullName from Review as r\n"
                    + "join Product as p on r.pid = p.pid\n"
                    + "join [User] as u on r.cid = u.cid\n"
                    + "where r.pid=?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, pid);

            rs = ps.executeQuery();
            while (rs.next()) {
                Review r = new Review();
                r.setReviewid(rs.getInt("reviewid"));
                r.setCid(rs.getInt("cid"));
                r.setPid(rs.getInt("pid"));
                r.setUser_comment(rs.getString("user_comment"));
                r.setRating(rs.getInt("user_rating"));
                r.setUsertime_comment(rs.getDate("user_timecomment"));
                r.setPname(rs.getString("pname"));
                r.setImage(rs.getString("image"));
                r.setFullname(rs.getString("fullName"));
                r.setIsHide(rs.getBoolean("status"));

                list_review.add(r);
            }
            if (hide != null && !hide.isEmpty()) {
                boolean hideBool = Integer.parseInt(hide) == 1;
                for (int i = 0; i < list_review.size(); i++) {
                    if (list_review.get(i).getIsHide() != hideBool) {
                        list_review.remove(i);
                        i = -1;
                    }
                }

            }
            if (rate != null && !rate.isEmpty()) {
                int rateInt = Integer.parseInt(rate);
                for (int i = 0; i < list_review.size(); i++) {
                    if (list_review.get(i).getRating() != rateInt) {
                        list_review.remove(i);
                        i = -1;
                    }
                }
            }
            if (name != null && !name.isEmpty()) {

                for (int i = 0; i < list_review.size(); i++) {
                    
                    if (!list_review.get(i).getFullname().equalsIgnoreCase(name)) {
                        list_review.remove(i);
                        i = -1;
                        
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list_review;
    }

    public int countCommentIsHide(int hide, String pid) {
        connection = (new DBContext().connection);
        try {
            String sql = "select count(*) from Review as r\n"
                    + "join Product as p on r.pid = p.pid\n"
                    + "join [User] as u on r.cid = u.cid\n"
                    + "where r.status=? and r.pid = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, hide);
            ps.setString(2, pid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return 0;
    }

    public void updateHide(boolean hide, int rid) {
        String sql = "update Review set status = ? where reviewid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, hide);
            stm.setInt(2, rid);
            stm.execute();
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

    public static void main(String[] args) {
        FeedbackDAO ao = new FeedbackDAO();
//        ao.insertFeedback("1010", "3", "comment abc123", "2", "img12332.png");
//        ao.deleteFeedback("2");
//        ao.updatestatus("3", "0");
//        ao.updateFeedback("update qweqwe commnet", "2", "update img", "0", "4");
//        ArrayList<Feedback> flist = ao.getAllFeedback("", "", "", 1, 3);
//        for (Feedback f : flist) {
//            System.out.println(f.getUser_comment() + " - " + f.getUser_rating() + " - " + f.getUser().getFullName() + " " + f.getProduct().getPname());
//        }
//        Feedback f = ao.getFeedback("1");
//        System.out.println(f.getUser_comment() + " - " + f.getUser_rating() + " - " + f.getUser().getEmail()+ " " + f.getProduct().getPname());
            int number = ao.countCommentIsHide(0, "3");
            System.out.println(number);

    }
}
