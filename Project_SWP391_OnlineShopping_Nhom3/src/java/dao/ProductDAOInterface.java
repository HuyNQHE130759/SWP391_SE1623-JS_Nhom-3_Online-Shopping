/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Product;
import java.util.ArrayList;

/**
 *
 * @author apc
 */
public interface ProductDAOInterface {
    
    public ArrayList<Product> getAllProduct();

    public ArrayList<Product> getAllProduct(Integer pageindex, Integer pagesize);

    public ArrayList<Product> getAllProduct(String sort, Integer cateId, Integer provider_id, Boolean status, String search, Integer pageindex, Integer pagesize);

    public int count();
    
    public void insert(String pname, String img, float price, String description, boolean status, int cateID);
    
    public void update(Integer pid, String pname, String img, float price, String description, boolean status, int cateID);
    
    public Product getProductById(int pid);
    
}
