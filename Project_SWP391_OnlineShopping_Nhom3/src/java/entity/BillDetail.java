/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

public class BillDetail {

    private int bid;
    private String image;
    private int quantity;
    private double price;
    
    private int rowNumber;
    private Date dateCreate;
    private double total;
    private int cid;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String pname;
    

    public BillDetail() {
    }

    public BillDetail(int rowNumber, int bid, Date dateCreate, double total, int quantity, int cid, String fullName, String address, String phone, String email, String username, String pname, double price, String image) {
        this.rowNumber = rowNumber;
        this.bid = bid;
        this.dateCreate = dateCreate;
        this.total = total;
        this.quantity = quantity;
        this.cid = cid;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.pname = pname;
        this.price = price;
        this.image = image;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BillDetail{" + "rowNumber=" + rowNumber + ", bid=" + bid + ", dateCreate=" + dateCreate + ", total=" + total + ", quantity=" + quantity + ", cid=" + cid + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone + ", email=" + email + ", username=" + username + ", pname=" + pname + ", price=" + price + ", image=" + image + '}';
    }

}
