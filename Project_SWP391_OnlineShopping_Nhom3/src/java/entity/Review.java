/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;


public class Review {
    private int reviewid;
    private int cid;
    private String pid;
    private String user_comment;
    private int rating;
    private Date usertime_comment;
    private String pname;
    private String image;
    private String fullname;
    private Boolean status;
    public Review() {
    }

    public Review(int cid, String pid, String user_comment, int rating, Date usertime_comment) {
        this.cid = cid;
        this.pid = pid;
        this.user_comment = user_comment;
        this.rating = rating;
        this.usertime_comment = usertime_comment;
    }

    public Review(int reviewid, int cid, String pid, String user_comment, int rating, Date usertime_comment, String pname, String image, String fullname, Boolean status) {
        this.reviewid = reviewid;
        this.cid = cid;
        this.pid = pid;
        this.user_comment = user_comment;
        this.rating = rating;
        this.usertime_comment = usertime_comment;
        this.pname = pname;
        this.image = image;
        this.fullname = fullname;
        this.status = status;
    }

    public Boolean getIsHide() {
        return status;
    }

    public void setIsHide(Boolean status) {
        this.status = status;
    }

    

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Review{" + "reviewid=" + reviewid + ", cid=" + cid + ", pid=" + pid + ", user_comment=" + user_comment + ", rating=" + rating + ", usertime_comment=" + usertime_comment + ", pname=" + pname + ", image=" + image + ", fullname=" + fullname + ", status=" + status + '}';
    }

  

    
    
    
    
}
