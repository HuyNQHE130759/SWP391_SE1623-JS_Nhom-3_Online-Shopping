/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author ngoclong
 */
public class Bill {
    private int bid;
    private String dateCreate;
    private double total;
    private String recName;
    private String recAddress;
    private String recPhone;
    private int status;
    private int cid;

    public Bill() {
    }

    public Bill(int bid, String dateCreate, double total, String recName, String recAddress, String recPhone, int status, int cid) {
        this.bid = bid;
        this.dateCreate = dateCreate;
        this.total = total;
        this.recName = recName;
        this.recAddress = recAddress;
        this.recPhone = recPhone;
        this.status = status;
        this.cid = cid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Bill{" + "bid=" + bid + ", dateCreate=" + dateCreate + ", total=" + total + ", recName=" + recName + ", recAddress=" + recAddress + ", recPhone=" + recPhone + ", status=" + status + ", cid=" + cid + '}';
    }
       
    
}
