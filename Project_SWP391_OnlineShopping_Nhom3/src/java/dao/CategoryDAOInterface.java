/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Category;
import java.util.ArrayList;

/**
 *
 * @author apc
 */
public interface CategoryDAOInterface {
    public ArrayList<Category> getAllCategory();
    
    public ArrayList<Category> getAllCategory(Integer pageindex, Integer pagesize);
    
    public ArrayList<Category> getAllCategory(String sort, Boolean status, String search, Integer pageindex, Integer pagesize);
    
    public int count();
    
    public void insert(String cname, String img, boolean status);
    
    public void update(int cid, String cname, String img, boolean status);
    
    public Category getCategory(Integer cid);
}
