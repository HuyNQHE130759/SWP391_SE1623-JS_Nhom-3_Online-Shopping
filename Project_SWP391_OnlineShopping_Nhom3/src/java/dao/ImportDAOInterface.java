/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Import;
import entity.Product1;
import entity.Provider;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author apc
 */
public interface ImportDAOInterface {
    public ArrayList<Import> getAllImport();
    
    public ArrayList<Import> getAllImport(Integer pageindex, Integer pagesize);
    
    public ArrayList<Import> getAllImport(String sort, Integer provider_id, Date from, Date to,String search, Integer pageindex, Integer pagesize);
    
    public int count();
    
    public void insert(Product1 product, Provider provider, Integer quantity, Date date);
    
    public Import getImportById(int importID);
}
