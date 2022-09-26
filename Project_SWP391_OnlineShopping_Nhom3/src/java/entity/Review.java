/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author HuyNQ
 */
public class Review {
    private int cid;
    private String pid;
    private String user_comment;
    private int rating;
    private Date usertime_comment;

    public Review() {
    }

    public Review(int cid, String pid, String user_comment, int rating, Date usertime_comment) {
        this.cid = cid;
        this.pid = pid;
        this.user_comment = user_comment;
        this.rating = rating;
        this.usertime_comment = usertime_comment;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getUsertime_comment() {
        return usertime_comment;
    }

    public void setUsertime_comment(Date usertime_comment) {
        this.usertime_comment = usertime_comment;
    }
    
}
