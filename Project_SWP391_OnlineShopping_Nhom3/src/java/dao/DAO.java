/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Bill;
import entity.BillDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Cart;
import entity.Category;
import entity.CheckOut;
import entity.Product;
import entity.Provider;
import entity.Review;
import entity.User;

public class DAO extends DBContext {

    private Connection con;
    private Statement state;
    private ResultSet rs;

    public DAO() {
        connect();
    }

    public void connect() {
        try {
            con = (new DBContext().connection);
            System.out.println("Success!");
            state = con.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            System.out.println("Error connection: " + e.getMessage());
        }
    }

    public ArrayList checkLogin(String username, String pass) {
        User u = new User();
        ArrayList<User> ul = new ArrayList<>();
        try {
            String strSelect = "select * from dbo.[User] where username = '" + username + "' and password='" + pass + "' and status = 1";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
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

    public int findIdByEmail(String email) {
        try {
            String sql = "   select [cid] from [User] where [email] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public ArrayList getCategory() {
        Category c = new Category();
        ArrayList<Category> cl = new ArrayList<>();
        try {
            String strSelect = "select * from Category";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
//                c.setCateId(rs.getInt(1));
                c.setCateName(rs.getString(2));
                c.setImage(rs.getString(3));
                c.setStatus(rs.getBoolean(4));

                cl.add(new Category(c.getCateId(), c.getCateName(), c.getImage(), c.isStatus()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return cl;
    }
    public ArrayList getProvider() {
        ArrayList<Provider> list = new ArrayList<>();
        try {
            String strSelect = "select * from Provider";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                Provider p = new Provider();
                p.setProvider_id(rs.getInt(1));
                p.setProvider_name(rs.getString(2));
                p.setProvider_email(rs.getString(3));
                p.setProvider_address(rs.getString(4));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return list;
    }

    public ArrayList getAllProduct() {
        Product p = new Product();
        ArrayList<Product> pl = new ArrayList<>();
        try {
            String strSelect = "select * from [dbo].[Product]";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
//                p.setPid(rs.getInt(1));
                p.setPname(rs.getString(2));
                p.setQuantity(rs.getInt(3));
                p.setPrice(rs.getDouble(4));
                p.setImage(rs.getString(5));
                p.setDescription(rs.getString(6));
                p.setStatus(rs.getBoolean(7));
                p.setCateId(rs.getString(8));

                pl.add(new Product(p.getPid(), p.getPname(), p.getQuantity(), p.getPrice(),
                        p.getImage(), p.getDescription(), p.isStatus(), p.getCateId()));
            }
        } catch (SQLException e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return pl;
    }

    public ArrayList getProductbyCate(String cid) {
        Product p = new Product();
        ArrayList<Product> pl = new ArrayList<>();
        try {
            String strSelect = "select * from [dbo].[Product] where cateId= '" + cid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
//                p.setPid(rs.getInt(1));
                p.setPname(rs.getString(2));
                p.setQuantity(rs.getInt(3));
                p.setPrice(rs.getDouble(4));
                p.setImage(rs.getString(5));
                p.setDescription(rs.getString(6));
                p.setStatus(rs.getBoolean(7));
                p.setCateId(rs.getString(8));

                pl.add(new Product(p.getPid(), p.getPname(), p.getQuantity(), p.getPrice(),
                        p.getImage(), p.getDescription(), p.isStatus(), p.getCateId()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return pl;
    }

    public ArrayList getSingleProduct(String pid) {
        Product p = new Product();
        ArrayList<Product> pl = new ArrayList<>();
        try {
            String strSelect = "select * from [dbo].[Product] where pid= '" + pid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
//                p.setPid(rs.getInt(1));
                p.setPname(rs.getString(2));
                p.setQuantity(rs.getInt(3));
                p.setPrice(rs.getDouble(4));
                p.setImage(rs.getString(5));
                p.setDescription(rs.getString(6));
                p.setStatus(rs.getBoolean(7));
                p.setCateId(rs.getString(8));

                pl.add(new Product(p.getPid(), p.getPname(), p.getQuantity(), p.getPrice(),
                        p.getImage(), p.getDescription(), p.isStatus(), p.getCateId()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return pl;
    }

    public void insertCustomer(String cname, String caddress, String cphone, String cusname, String cpassword, boolean cstatus, String email, String gender) {

        //System.out.println(p_pid);
        try {
            rs = state.executeQuery("INSERT INTO [dbo].[User]([fullName],[address] ,[phone] ,[username],[password],[status],[email],[gender],[roleid]) VALUES ('" + cname + "' ,'" + caddress + "' ,'" + cphone + "','" + cusname + "','" + cpassword + "','" + cstatus + "','" + email + "','" + gender + "','" + 1 + "')");
        } catch (Exception e) {
            System.out.println("Error Customer " + e.getMessage());
        }

    }

    public void addCommentOfficial(int cid, String pid, String user_comment, int user_rating, Date user_timecomment) {
        try {
            String sql = "insert into [dbo].[Review] values('" + cid + "','" + pid + "','" + user_comment + "','" + user_rating + "','" + user_timecomment + "')";
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Review> getListReview(String pid) {
        ArrayList<Review> rl = new ArrayList();
        try {
            rs = state.executeQuery("Select * from Review where pid = '" + pid + "'");
            while (rs.next()) {
                int us_id = rs.getInt(1);
                String g_id = rs.getString(2);
                String u_comment = rs.getString(3);
                int u_rating = rs.getInt(4);
                Date u_timecomment = rs.getDate(5);
                rl.add(new Review(us_id, g_id, u_comment, u_rating, u_timecomment));
            }
        } catch (Exception e) {
            System.out.println("Error!!" + e.getMessage());
        }
        return rl;
    }

    public User getSingleUser(int cid) {
        User u = new User();
        ArrayList<User> ul = new ArrayList<>();
        try {
            String strSelect = "select * from [dbo].[User] where cid = '" + cid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                u.setCid(rs.getInt(1));
                u.setFullName(rs.getString(2));
                u.setAddress(rs.getString(3));
                u.setPhone(rs.getString(4));
                u.setUsername(rs.getString(5));
                u.setPassword(rs.getString(6));
                u.setStatus(rs.getBoolean(7));

            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return u;
    }
    
    public void updateUserInfo(User user) {
        try {
            rs = state.executeQuery("UPDATE [dbo].[User]\n"
                    + "   SET [fullName] = '"+user.getFullName()+"'\n"
                    + "      ,[address] = '"+user.getAddress()+"'\n"
                    + "      ,[phone] = '"+user.getPhone()+"'\n"
                    + "      ,[email] = '"+user.getEmail()+"'\n"
                    + "      ,[gender] = "+(user.isGender() ? 1 : 0)+"\n"
                    + " WHERE cid = "+user.getCid());
            ;
        } catch (Exception e) {
            System.out.println("Error Customer " + e.getMessage());
        }
    }
    
    public void insertContact(String cname, String cemail, String Subject, String Message) {

        //System.out.println(p_pid);
        try {
            rs = state.executeQuery("INSERT INTO [dbo].[Contact]\n"
                    + "           ([ContactName]\n"
                    + "           ,[ContactEmail]\n"
                    + "           ,[Subject]\n"
                    + "           ,[Message])\n"
                    + "     VALUES\n"
                    + "           ('" + cname + "'\n"
                    + "           ,'" + cemail + "'\n"
                    + "           ,'" + Subject + "'\n"
                    + "           ,'" + Message + "')");
        } catch (Exception e) {
            System.out.println("Error Customer " + e.getMessage());
        }

    }

    public ArrayList getCartbyUser(int cid) {
        Cart c = new Cart();
        ArrayList<Cart> cl = new ArrayList<>();
        try {
            String strSelect = "select * from Cart where cid = '" + cid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                c.setcID(rs.getInt(1));
                c.setCuID(rs.getInt(2));
                c.setpID(rs.getString(3));
                c.setpQuantity(rs.getInt(4));

                cl.add(new Cart(c.getcID(), c.getCuID(), c.getpID(), c.getpQuantity()));

            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return cl;
    }

    public void inserttoCart(String pid, int cid, int quantity) {
        int cartID = 0;
        try {
            String strSelect = "select top 1 [CartId] from Cart";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                cartID = rs.getInt(1);

            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        if (cartID != 0) {
            cartID++;
        } else {
            cartID = 1;
        }
        //System.out.println(p_pid);
        try {
            rs = state.executeQuery("INSERT INTO [dbo].[Cart]\n"
                    + "           ([CartId]\n"
                    + "           ,[cid]\n"
                    + "           ,[pid]\n"
                    + "           ,[pQuantity])\n"
                    + "     VALUES\n"
                    + "           ('" + cartID + "'\n"
                    + "           ,'" + cid + "'\n"
                    + "           ,'" + pid + "'\n"
                    + "           ,'" + quantity + "')");
        } catch (Exception e) {
            System.out.println("Error Customer " + e.getMessage());
        }
    }

    public void removeCart(String pid, int cid) {

        try {
            rs = state.executeQuery("DELETE FROM [dbo].[Cart] WHERE ProductID = '" + pid + "' and CustomerID = '" + cid + "'");
        } catch (Exception e) {
            System.out.println("Error Product " + e.getMessage());
        }

    }

    public void insertCheckout(int cid, Date createDate, double totalPrice) {

        //System.out.println(p_pid);
        try {
            rs = state.executeQuery("INSERT INTO [dbo].[CheckOut]\n"
                    + "           ([Cid]\n"
                    + "           ,[CreateDate]\n"
                    + "           ,[TotalPrice])\n"
                    + "     VALUES\n"
                    + "           ('" + cid + "'\n"
                    + "           ,'" + createDate + "'\n"
                    + "           ,'" + totalPrice + "')");
        } catch (Exception e) {
            System.out.println("Error Customer " + e.getMessage());
        }
        try {
            rs = state.executeQuery("DELETE FROM [dbo].[Cart] CustomerID = '" + cid + "'");
        } catch (Exception e) {
            System.out.println("Error Product " + e.getMessage());
        }
    }

    public ArrayList getProduct() {
        Product p = new Product();
        ArrayList<Product> pl = new ArrayList<>();
        try {
            String strSelect = "select * from Product";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
//                p.setPid(rs.getInt(1));
                p.setPname(rs.getString(2));
                p.setQuantity(rs.getInt(3));
                p.setPrice(rs.getInt(4));
                p.setImage(rs.getString(5));
                p.setDescription(rs.getString(6));
                p.setStatus(rs.getBoolean(7));
                p.setCateId(rs.getString(8));

                pl.add(new Product(p.getPid(), p.getPname(), p.getQuantity(), p.getPrice(), p.getImage(), p.getDescription(), p.isStatus(), p.getCateId()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return pl;
    }

    public void removeProduct(String pid) {

        try {
            rs = state.executeQuery("DELETE FROM [Product] WHERE pid = '" + pid + "'");
        } catch (Exception e) {
            System.out.println("Error Product " + e.getMessage());
        }

    }

    public void updateProduct(String pid, String pname, int pquantity, int pprice, String image, String description, boolean status, String cid) {
        try {
            rs = state.executeQuery("UPDATE [dbo].[Product]\n"
                    + "   SET \n"
                    + "      [pname] = '" + pname + "'\n"
                    + "      ,[quantity] = '" + pquantity + "'\n"
                    + "      ,[price] = '" + pprice + "'\n"
                    + "      ,[image] = '" + image + "'\n"
                    + "      ,[description] = '" + description + "'\n"
                    + "      ,[status] = '" + status + "'\n"
                    + "      ,[cateId] = '" + cid + "'\n"
                    + " WHERE [pid] = '" + pid + "'");
        } catch (SQLException e) {
            System.out.println("Error ProductUp " + e.getMessage());
        }
    }

    public void insertProduct(String pid, String pname, String img, int quantity, int money, String description, boolean status, String cateID) {

        try {
            rs = state.executeQuery("INSERT INTO [Product]([pid],[pname],[quantity],[price],[image],[description],[status],[cateId]) VALUES ('" + pid + "','" + pname + "','" + quantity + "' ,'" + money + "','" + img + "','" + description + "','" + status + "','" + cateID + "')");
        } catch (Exception e) {
            System.out.println("Error Category " + e.getMessage());
        }

    }

    public Product getProductById(String pid) {
        Product p = new Product();
        try {
            String strSelect = "select * from Product WHERE pid = '" + pid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
//                p.setPid(rs.getInt(1));
                p.setPname(rs.getString(2));
                p.setQuantity(rs.getInt(3));
                p.setPrice(rs.getInt(4));
                p.setImage(rs.getString(5));
                p.setDescription(rs.getString(6));
                p.setStatus(rs.getBoolean(7));
                p.setCateId(rs.getString(8));

            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return p;
    }

    public ArrayList getCheckout() {
        CheckOut c = new CheckOut();
        ArrayList<CheckOut> cl = new ArrayList<>();
        try {
            String strSelect = "select * from CheckOut";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                c.setBId(rs.getInt(1));
                c.setCId(rs.getInt(2));
                c.setCrateDate(rs.getDate(3));
                c.setTotalPrice(rs.getInt(4));

                cl.add(new CheckOut(c.getBId(), c.getCId(), c.getCrateDate(), (int) c.getTotalPrice()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return cl;
    }

    public void changePassword(int cid, String cpassword) {
        try {
            rs = state.executeQuery("UPDATE [dbo].[User]\n"
                    + "   SET \n"
                    + "      [password] = '" + cpassword + "' \n"
                    + " WHERE cid = '" + cid + "'");
        } catch (SQLException e) {
            System.out.println("Error ProductUp " + e.getMessage());
        }
    }

    public void activeAccount(String email) {
        try {
            rs = state.executeQuery("update [User] set [status] = 1  where [email] = '" + email + "'");
        } catch (SQLException e) {
            System.out.println("Error ProductUp " + e.getMessage());
        }
    }

    public boolean checkEmailExist(String email) {
        int uid = -1;
        try {
            PreparedStatement stm = connection.prepareStatement("  select [cid] from [User] where [email] = ?");
            stm.setString(1, email);
            rs = stm.executeQuery();
            while (rs.next()) {
                uid = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return uid != -1;
    }

    public ArrayList<User> getListUser(String role, String status, String sort) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String query = "select * "
                    + "from [User] u "
                    + "left join [Role] r on u.roleid = r.roleid ";
            
            if(role.isEmpty() && !status.isEmpty()) {
                query += "where status = " + "'" + status + "' ";
            }
            if(!role.isEmpty() && status.isEmpty()) {
                query += "where rolename = " + "'" + role + "' ";
            }
            if(!role.isEmpty() && !status.isEmpty()) {
                query += "where rolename = " + "'" + role + "' and status = " + "'" + status + "' ";
            }
            
            query += "order by " + "'" + sort + "' ";
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rslt = ps.executeQuery();
            while(rslt.next()) {
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
    }; 
    
    
    public ArrayList getBillList() {
        Bill bill = new Bill();
        ArrayList<Bill> bi = new ArrayList<>();
        try {
            String strSelect = "select * from Bill";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bill.setBid(rs.getInt(1));
                bill.setDateCreate(rs.getString(2));
                bill.setTotal(rs.getDouble(3));
                bill.setRecName(rs.getString(4));
                bill.setRecAddress(rs.getString(5));
                bill.setRecPhone(rs.getString(6));
                bill.setStatus(rs.getInt(7));
                bill.setCid(rs.getInt(8));
                bi.add(new Bill(bill.getBid(), bill.getDateCreate(), bill.getTotal(), bill.getRecName(), bill.getRecAddress(), bill.getRecPhone(), bill.getStatus(), bill.getCid()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }

        return bi;
    }
    
     public Bill getBillById(int bid) {
        Bill bill = new Bill();
        try {
            String strSelect = "select * from Bill WHERE bid = '" + bid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bill.setBid(rs.getInt(1));
                bill.setDateCreate(rs.getString(2));
                bill.setTotal(rs.getDouble(3));
                bill.setRecName(rs.getString(4));
                bill.setRecAddress(rs.getString(5));
                bill.setRecPhone(rs.getString(6));
                bill.setStatus(rs.getInt(7));
                bill.setCid(rs.getInt(8));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bill;
    }
     
     public ArrayList getAllBillDetail() {
         BillDetail bdetail = new BillDetail();
         ArrayList<BillDetail> bi = new ArrayList<>();
        try {
            String strSelect = "select b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n" +
"join BillDetail as db on b.bid = db.bid\n" +
"join [User] as u on u.cid = b.cid\n" +
"join Product as p on db.pid = p.pid\n";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bdetail.setBid(rs.getInt(1));
                bdetail.setDateCreate(rs.getDate(2));
                bdetail.setTotal(rs.getDouble(3));
                bdetail.setQuantity(rs.getInt(4));
                bdetail.setCid(rs.getInt(5));
                bdetail.setFullName(rs.getString(7));
                bdetail.setAddress(rs.getString(8));
                bdetail.setPhone(rs.getString(9));
                bdetail.setEmail(rs.getString(10));
                bdetail.setUsername(rs.getString(11));
                bdetail.setPname(rs.getString(15));
                bdetail.setPrice(rs.getDouble(16));
                bdetail.setImage(rs.getString(17));
bi.add(new BillDetail(bdetail.getRowNumber(),bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }
     
     public ArrayList getBillDetailById(int bid) {
         BillDetail bdetail = new BillDetail();
         ArrayList<BillDetail> bi = new ArrayList<>();
        try {
            String strSelect = "select b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n" +
"join BillDetail as db on b.bid = db.bid\n" +
"join [User] as u on u.cid = b.cid\n" +
"join Product as p on db.pid = p.pid\n" +
"where b.bid = '" + bid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bdetail.setBid(rs.getInt(1));
                bdetail.setDateCreate(rs.getDate(2));
                bdetail.setTotal(rs.getDouble(3));
                bdetail.setQuantity(rs.getInt(4));
                bdetail.setCid(rs.getInt(5));
                bdetail.setFullName(rs.getString(7));
                bdetail.setAddress(rs.getString(8));
                bdetail.setPhone(rs.getString(9));
                bdetail.setEmail(rs.getString(10));
                bdetail.setUsername(rs.getString(11));
                bdetail.setPname(rs.getString(15));
                bdetail.setPrice(rs.getDouble(16));
                bdetail.setImage(rs.getString(17));
bi.add(new BillDetail(bdetail.getRowNumber(),bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }
     
     public ArrayList getOrderHistoryById(int cid) {
         BillDetail bdetail = new BillDetail();
         ArrayList<BillDetail> bi = new ArrayList<>();
        try {
            String strSelect = "select b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n" +
"join BillDetail as db on b.bid = db.bid\n" +
"join [User] as u on u.cid = b.cid\n" +
"join Product as p on db.pid = p.pid\n" +
"where b.cid = '" + cid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bdetail.setBid(rs.getInt(1));
                bdetail.setDateCreate(rs.getDate(2));
                bdetail.setTotal(rs.getDouble(3));
                bdetail.setQuantity(rs.getInt(4));
                bdetail.setCid(rs.getInt(5));
                bdetail.setFullName(rs.getString(7));
                bdetail.setAddress(rs.getString(8));
                bdetail.setPhone(rs.getString(9));
                bdetail.setEmail(rs.getString(10));
                bdetail.setUsername(rs.getString(11));
                bdetail.setPname(rs.getString(15));
                bdetail.setPrice(rs.getDouble(16));
                bdetail.setImage(rs.getString(17));
bi.add(new BillDetail(bdetail.getRowNumber(),bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }
     
     public ArrayList getOrderHistoryByPage(int cid,int index) {
         BillDetail bdetail = new BillDetail();
         ArrayList<BillDetail> bi = new ArrayList<>();
         
        try {
            String strSelect = "select * from (select ROW_NUMBER() over(order by b.bid asc) as r , b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n" +
"join BillDetail as db on b.bid = db.bid\n" +
"join [User] as u on u.cid = b.cid\n" +
"join Product as p on db.pid = p.pid) as x\n" +
"where r between '" + (index*3-2) + "' and '" + (index*3) + "' AND x.cid = '" + cid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bdetail.setRowNumber(rs.getInt(1));
                bdetail.setBid(rs.getInt(2));
                bdetail.setDateCreate(rs.getDate(3));
                bdetail.setTotal(rs.getDouble(4));
                bdetail.setQuantity(rs.getInt(5));
                bdetail.setCid(rs.getInt(6));
                bdetail.setFullName(rs.getString(8));
                bdetail.setAddress(rs.getString(9));
                bdetail.setPhone(rs.getString(10));
                bdetail.setEmail(rs.getString(11));
                bdetail.setUsername(rs.getString(12));
                bdetail.setPname(rs.getString(16));
                bdetail.setPrice(rs.getDouble(17));
                bdetail.setImage(rs.getString(18));
            bi.add(new BillDetail(bdetail.getRowNumber(),bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }
     
      public ArrayList getOrderHistoryByPageWithTotalAsc(int cid,int index) {
         BillDetail bdetail = new BillDetail();
         ArrayList<BillDetail> bi = new ArrayList<>();
         
        try {
            String strSelect = "select * from (select ROW_NUMBER() over(order by b.total asc) as r , b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n" +
"join BillDetail as db on b.bid = db.bid\n" +
"join [User] as u on u.cid = b.cid\n" +
"join Product as p on db.pid = p.pid) as x\n" +
"where x.cid = '" + cid + "' order by total asc";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bdetail.setRowNumber(rs.getInt(1));
                bdetail.setBid(rs.getInt(2));
                bdetail.setDateCreate(rs.getDate(3));
                bdetail.setTotal(rs.getDouble(4));
                bdetail.setQuantity(rs.getInt(5));
                bdetail.setCid(rs.getInt(6));
                bdetail.setFullName(rs.getString(8));
                bdetail.setAddress(rs.getString(9));
                bdetail.setPhone(rs.getString(10));
                bdetail.setEmail(rs.getString(11));
                bdetail.setUsername(rs.getString(12));
                bdetail.setPname(rs.getString(16));
                bdetail.setPrice(rs.getDouble(17));
                bdetail.setImage(rs.getString(18));
            bi.add(new BillDetail(bdetail.getRowNumber(),bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }
      
      public ArrayList getOrderHistoryByPageWithDateAsc(int cid,int index) {
         BillDetail bdetail = new BillDetail();
         ArrayList<BillDetail> bi = new ArrayList<>();
         
        try {
            String strSelect = "select * from (select ROW_NUMBER() over(order by dateCreate asc) as r , b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n" +
"join BillDetail as db on b.bid = db.bid\n" +
"join [User] as u on u.cid = b.cid\n" +
"join Product as p on db.pid = p.pid) as x\n" +
"where x.cid = '" + cid + "' order by dateCreate asc";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bdetail.setRowNumber(rs.getInt(1));
                bdetail.setBid(rs.getInt(2));
                bdetail.setDateCreate(rs.getDate(3));
                bdetail.setTotal(rs.getDouble(4));
                bdetail.setQuantity(rs.getInt(5));
                bdetail.setCid(rs.getInt(6));
                bdetail.setFullName(rs.getString(8));
                bdetail.setAddress(rs.getString(9));
                bdetail.setPhone(rs.getString(10));
                bdetail.setEmail(rs.getString(11));
                bdetail.setUsername(rs.getString(12));
                bdetail.setPname(rs.getString(16));
                bdetail.setPrice(rs.getDouble(17));
                bdetail.setImage(rs.getString(18));
            bi.add(new BillDetail(bdetail.getRowNumber(),bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }
     
     public ArrayList getOrderInformationByCustomer(int cid,int bid) {
         BillDetail bdetail = new BillDetail();
         ArrayList<BillDetail> bi = new ArrayList<>();
        try {
            String strSelect = "select b.bid,b.dateCreate,b.total,db.quantity,u.*,p.pname,p.price,p.image from Bill as b\n" +
"join BillDetail as db on b.bid = db.bid\n" +
"join [User] as u on u.cid = b.cid\n" +
"join Product as p on db.pid = p.pid\n" +
"where b.cid = '" + cid + "' AND b.bid = '" + bid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                bdetail.setBid(rs.getInt(1));
                bdetail.setDateCreate(rs.getDate(2));
                bdetail.setTotal(rs.getDouble(3));
                bdetail.setQuantity(rs.getInt(4));
                bdetail.setCid(rs.getInt(5));
                bdetail.setFullName(rs.getString(7));
                bdetail.setAddress(rs.getString(8));
                bdetail.setPhone(rs.getString(9));
                bdetail.setEmail(rs.getString(10));
                bdetail.setUsername(rs.getString(11));
                bdetail.setPname(rs.getString(15));
                bdetail.setPrice(rs.getDouble(16));
                bdetail.setImage(rs.getString(17));
                bi.add(new BillDetail(bdetail.getRowNumber(),bdetail.getBid(), bdetail.getDateCreate(), bdetail.getTotal(), bdetail.getQuantity(), bdetail.getCid(), bdetail.getFullName(), bdetail.getAddress(), bdetail.getPhone(), bdetail.getEmail(), bdetail.getUsername(), bdetail.getPname(), bdetail.getPrice(), bdetail.getImage()));
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return bi;
    }
     
   

      public int countNumberPhanTrang(int cid) {
        try {
            String strSelect = "select count(*) from Bill as b\n" +
"join BillDetail as db on b.bid = db.bid\n" +
"join [User] as u on u.cid = b.cid\n" +
"join Product as p on db.pid = p.pid\n" +
"where b.cid = '" + cid + "'";
            rs = state.executeQuery(strSelect);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error user: " + e.getMessage());
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new DAO().checkLogin("Huy", "123"));
        DAO dao = new DAO();
  ArrayList<BillDetail> bdetail = dao.getOrderHistoryByPageWithDateAsc(2,2);
            for (BillDetail bill : bdetail) {
                System.out.println(bill);
        }
        
    }
}
