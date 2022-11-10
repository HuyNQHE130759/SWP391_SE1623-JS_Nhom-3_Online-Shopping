package entity;

import java.sql.Blob;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Guarantee {
    private int id;
    private String product;
    private String description;
    private String status;
    private String owner;
    private Date pickUpDate;
    private Blob image;
    private String fileName;
    
    Map<String, String> mapStatus = new HashMap<>();

    public Guarantee() {
        mapStatus.put("inprogress", "In Progress");
        mapStatus.put("done", "Done");
        mapStatus.put("canceled", "Canceled");
    }
    
    

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        for(String x: mapStatus.keySet()) {
            if(status.equals(x)) {
                return mapStatus.get(x);
            }
        }
        return null;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
    
    
}
