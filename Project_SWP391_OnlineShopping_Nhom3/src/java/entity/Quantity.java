/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Nam
 */
public class Quantity {
    private int boughtQuantity;
    private int productQuantity;

    public Quantity() {
    }

    public Quantity(int boughtQuantity, int productQuantity) {
        this.boughtQuantity = boughtQuantity;
        this.productQuantity = productQuantity;
    }
    
    

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public void setBoughtQuantity(int boughtQuantity) {
        this.boughtQuantity = boughtQuantity;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    
    
}
