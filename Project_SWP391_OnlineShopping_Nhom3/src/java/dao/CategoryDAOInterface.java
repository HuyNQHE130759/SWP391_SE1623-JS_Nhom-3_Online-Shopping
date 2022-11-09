/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Category;
import entity.Category1;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author apc
 */
public interface CategoryDAOInterface {
    public ArrayList<Category1> getAllCategory();
    
    public ArrayList<Category1> getAllCategory(Integer pageindex, Integer pagesize);
    
    public ArrayList<Category1> getAllCategory(String sort, Boolean status, String search, Integer pageindex, Integer pagesize);
    
    public int count();
    
    public void insert(String cname, String img, boolean status);
    
    public void update(int cid, String cname, String img, boolean status);
    
    public Category1 getCategory(Integer cid);
    
    public boolean isExisted(String cname);
    
    public ArrayList<Category> getAllCategoryy() throws SQLException;
    
    public String getCategoryNameById(int categoryId) throws SQLException;
}
