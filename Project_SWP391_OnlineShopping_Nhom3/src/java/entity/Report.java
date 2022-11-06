package entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Report {
    private int billId;
    private String productName;
    private int quantity;
    private Long price;
    private Long totalPrice;
    private String shipperAccount;
    private String address;
    private String phone;
    private Date dateCreated;
    private String statusShipping;
    
    Map<String, String> mapStatus = new HashMap<>();
    
    public Report() {
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getShipperAccount() {
        return shipperAccount;
    }

    public void setShipperAccount(String shipperAccount) {
        this.shipperAccount = shipperAccount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
    
    
}
