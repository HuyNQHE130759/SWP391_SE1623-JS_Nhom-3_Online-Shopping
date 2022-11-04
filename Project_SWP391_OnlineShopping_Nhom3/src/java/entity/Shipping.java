package entity;

import java.util.HashMap;
import java.util.Map;

public class Shipping {
    private int billId;
    private int pid;
    private String productName;
    private int quantity;
    private double price;
    private double total;
    private String statusShipping;
    
    Map<String, String> mapStatus = new HashMap<>();
    
    public Shipping() {
        mapStatus.put("inprogress", "In Progress");
        mapStatus.put("done", "Done");
        mapStatus.put("canceled", "Canceled");
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatusShipping() {
        for(String x: mapStatus.keySet()) {
            if(statusShipping.equals(x)) {
                return mapStatus.get(x);
            }
        }
        return null;
    }

    public void setStatusShipping(String statusShipping) {
        this.statusShipping = statusShipping;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    
    
}
