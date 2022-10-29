/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Huynq
 */
public class Feedback {

    int reviewid;
    int cid;
    int pid;
    String img;
    String user_comment;
    int user_rating;
    Date user_timecomment;
    boolean status;
    Product product;
    User user;

    public Feedback(int reviewid, int cid, int pid, String img, String user_comment, int user_rating, Date user_timecomment, boolean status, Product product, User user) {
        this.reviewid = reviewid;
        this.cid = cid;
        this.pid = pid;
        this.img = img;
        this.user_comment = user_comment;
        this.user_rating = user_rating;
        this.user_timecomment = user_timecomment;
        this.status = status;
        this.product = product;
        this.user = user;
    }

    public Feedback() {
    }

    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public int getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(int user_rating) {
        this.user_rating = user_rating;
    }

    public Date getUser_timecomment() {
        return user_timecomment;
    }

    public void setUser_timecomment(Date user_timecomment) {
        this.user_timecomment = user_timecomment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
