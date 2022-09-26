/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author BVLT
 */
public class CheckOut {
    private int BId;
    private int CId;
    private Date crateDate;
    private int TotalPrice;

    public CheckOut() {
    }

    public CheckOut(int BId, int CId, Date crateDate, int TotalPrice) {
        this.BId = BId;
        this.CId = CId;
        this.crateDate = crateDate;
        this.TotalPrice = TotalPrice;
    }

    public int getBId() {
        return BId;
    }

    public void setBId(int BId) {
        this.BId = BId;
    }

    public int getCId() {
        return CId;
    }

    public void setCId(int CId) {
        this.CId = CId;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    
    
    
}
