/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Bill;
import entity.BillDetail;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Huynq
 */
public class OrderDAO implements OrderDAOInterface {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
     /**
     * get list of Order History by paging
     *
     * @param cid
     * @return ArrayList<BillDetail>
     */
    @Override
    public ArrayList<Bill> getOrderHistory(int cid) {
        connection = (new DBContext().connection);
        ArrayList<Bill> list_bill = new ArrayList<>();
        try {
            String sql = "Select * from Bill where cid = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while(rs.next()){
                Bill b = new Bill();
                b.setBid(rs.getInt("bid"));
                b.setDateCreate(rs.getString("dateCreate"));
                b.setTotal(rs.getDouble("total"));
                list_bill.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_bill;
    }
    
    @Override
    public ArrayList<Bill> getOrderList() {
        connection = (new DBContext().connection);
        ArrayList<Bill> list_bill = new ArrayList<>();
        try {
            String sql = "Select * from Bill";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Bill b = new Bill();
                b.setCid(rs.getInt("cid"));
                b.setBid(rs.getInt("bid"));
                b.setDateCreate(rs.getString("dateCreate"));
                b.setRecName(rs.getString("recName"));
                b.setTotal(rs.getDouble("total"));
                list_bill.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_bill;
    }

    /**
     * get total paging by user
     *
     * @param cid accountid
     * @return int number of paging
     */
    @Override
    public int countNumberPagingByOrderHistory(int cid) {
        connection = (new DBContext().connection);
        try {
            String sql = "select count(*) from Bill as b\n"
                    + "join BillDetail as db on b.bid = db.bid\n"
                    + "join [User] as u on u.cid = b.cid\n"
                    + "join Product as p on db.pid = p.pid\n"
                    + "where b.cid = '" + cid + "'";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return 0;
    }

    /**
     * get order history information by customer
     *
     * @param cid accountid
     * @param bid orderid
     * @return ArrayList<BillDetail>
     */
    
    @Override
    public HashMap<Product,Integer> getOrderInformationByCustomer(int bid) {
        connection = (new DBContext().connection);
        HashMap<Product,Integer> hashMap = new HashMap<>();
        try {
            String sql = "Select * from BillDetail where bid = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bid);
            rs = ps.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setPid(rs.getInt(2));
                String xSQL = "Select * from Product where pid = ?";
                PreparedStatement qtm = connection.prepareStatement(xSQL);
                qtm.setInt(1, p.getPid());
                ResultSet rs1 = qtm.executeQuery();
                if(rs1.next()){
                    p.setImage(rs1.getString("image"));
                    p.setPname(rs1.getString("pname"));
                    p.setPrice(rs1.getDouble("price"));
                }
                hashMap.put(p,rs.getInt(3));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return hashMap;
    }

 
   

  

    /**
     * get total number paging of order list
     *
     * @return int
     */
    @Override
    public int countNumberPagingByOrderList() {
        connection = (new DBContext().connection);
        try {
            String sql = "select count(*) from Bill as b\n"
                    + "join BillDetail as db on b.bid = db.bid\n"
                    + "join [User] as u on u.cid = b.cid\n"
                    + "join Product as p on db.pid = p.pid";
             ps = connection.prepareStatement(sql);
             rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return 0;
    }

    /**
     * get order list by paging
     *
     * @return ArrayList<BillDetail>
     */
    @Override
    public ArrayList getAllBillDetailByPage(int index) {
        connection = (new DBContext().connection);
        BillDetail bdetail = new BillDetail();
        ArrayList<BillDetail> bi = new ArrayList<>();
        try {
            String sql = "select * from (select ROW_NUMBER() over(order by b.bid asc) as r , b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n"
                    + "join BillDetail as db on b.bid = db.bid\n"
                    + "join [User] as u on u.cid = b.cid\n"
                    + "join Product as p on db.pid = p.pid) as x\n"
                    + "where r between '" + (index * 3 - 2) + "' and '" + (index * 3) + "'";
             ps = connection.prepareStatement(sql);
             rs = ps.executeQuery();
            while (rs.next()) {
                bdetail.setRowNumber(rs.getInt("r"));
                bdetail.setBid(rs.getInt("bid"));
                bdetail.setDateCreate(rs.getDate("dateCreate"));
                bdetail.setTotal(rs.getDouble("total"));
                bdetail.setQuantity(rs.getInt("quantity"));
                bdetail.setCid(rs.getInt("cid"));
                bdetail.setFullName(rs.getString("fullName"));
                bdetail.setAddress(rs.getString("address"));
                bdetail.setPhone(rs.getString("phone"));
                bdetail.setEmail(rs.getString("email"));
                bdetail.setUsername(rs.getString("username"));
                bdetail.setPname(rs.getString("pname"));
                bdetail.setPrice(rs.getDouble("price"));
                bdetail.setImage(rs.getString("image"));
                bi.add(new BillDetail(bdetail.getRowNumber(), bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }

    /**
     * get order list by user id
     *
     * @return ArrayList<BillDetail>
     */
    @Override
    public ArrayList getOrderListById(int bid) {
        connection = (new DBContext().connection);
        BillDetail bdetail = new BillDetail();
        ArrayList<BillDetail> bi = new ArrayList<>();
        try {
            String sql = "select b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n"
                    + "join BillDetail as db on b.bid = db.bid\n"
                    + "join [User] as u on u.cid = b.cid\n"
                    + "join Product as p on db.pid = p.pid\n"
                    + "where b.bid = '" + bid + "'";
             ps = connection.prepareStatement(sql);
             rs = ps.executeQuery();
            while (rs.next()) {
                bdetail.setBid(rs.getInt("bid"));
                bdetail.setDateCreate(rs.getDate("dateCreate"));
                bdetail.setTotal(rs.getDouble("total"));
                bdetail.setQuantity(rs.getInt("quantity"));
                bdetail.setCid(rs.getInt("cid"));
                bdetail.setFullName(rs.getString("fullName"));
                bdetail.setAddress(rs.getString("address"));
                bdetail.setPhone(rs.getString("phone"));
                bdetail.setEmail(rs.getString("email"));
                bdetail.setUsername(rs.getString("username"));
                bdetail.setPname(rs.getString("pname"));
                bdetail.setPrice(rs.getDouble("price"));
                bdetail.setImage(rs.getString("image"));
                bi.add(new BillDetail(bdetail.getRowNumber(), bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }

    @Override
    public ArrayList getOrderListByPageWithDateAsc() {
        connection = (new DBContext().connection);
        BillDetail bdetail = new BillDetail();
        ArrayList<BillDetail> bi = new ArrayList<>();

        try {
            String sql = "select * from (select ROW_NUMBER() over(order by dateCreate asc) as r , b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n"
                    + "join BillDetail as db on b.bid = db.bid\n"
                    + "join [User] as u on u.cid = b.cid\n"
                    + "join Product as p on db.pid = p.pid) as x \n"
                    + "order by dateCreate asc";
             ps = connection.prepareStatement(sql);
             rs = ps.executeQuery();
            while (rs.next()) {
                bdetail.setRowNumber(rs.getInt("r"));
                bdetail.setBid(rs.getInt("bid"));
                bdetail.setDateCreate(rs.getDate("dateCreate"));
                bdetail.setTotal(rs.getDouble("total"));
                bdetail.setQuantity(rs.getInt("quantity"));
                bdetail.setCid(rs.getInt("cid"));
                bdetail.setFullName(rs.getString("fullName"));
                bdetail.setAddress(rs.getString("address"));
                bdetail.setPhone(rs.getString("phone"));
                bdetail.setEmail(rs.getString("email"));
                bdetail.setUsername(rs.getString("username"));
                bdetail.setPname(rs.getString("pname"));
                bdetail.setPrice(rs.getDouble("price"));
                bdetail.setImage(rs.getString("image"));
                bi.add(new BillDetail(bdetail.getRowNumber(), bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }
    
     public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        ArrayList<BillDetail> bi = dao.getAllBillDetailByPage(1);
        for (BillDetail b : bi) {
            System.out.println(b);
            
        }
//     

    }
}
