/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class CartDB {
    private int cID;
    private int cuID;
    private String pID;
    private int pQuantity;

    public CartDB() {
    }

    public CartDB(int cID, int cuID, String pID, int pQuantity) {
        this.cID = cID;
        this.cuID = cuID;
        this.pID = pID;
        this.pQuantity = pQuantity;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getCuID() {
        return cuID;
    }

    public void setCuID(int cuID) {
        this.cuID = cuID;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }
    
    
}
