/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Provider;
import java.util.ArrayList;

/**
 *
 * @author apc
 */
public interface ProviderDAOInterface {
    public ArrayList<Provider> getAllProvider();
    
    public ArrayList<Provider> getAllProvider(Integer pageindex, Integer pagesize);
    
    public ArrayList<Provider> getAllProvider(String sort, Boolean status, String search, Integer pageindex, Integer pagesize);
    
    public int count();
    
    public void insert(String pname, String email, String address, boolean status);
    
    public void update(int pid, String pname, String email, String address, boolean status);
    
    public Provider getProvider(Integer pid);
    
    public boolean isExisted(String pvname);
}
