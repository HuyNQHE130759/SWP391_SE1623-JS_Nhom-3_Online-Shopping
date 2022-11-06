/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Bill;
import entity.Product;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Huynq
 */
public interface OrderDAOInterface {
    public ArrayList<Bill> getOrderHistory(int cid);

    public int countNumberPagingByOrderHistory(int cid);

    public HashMap<Product, Integer> getOrderInformationByCustomer(int bid);

    public int countNumberPagingByOrderList();

    public ArrayList getAllBillDetailByPage(int index);

    public ArrayList getOrderListById(int bid);

    public ArrayList getOrderListByPageWithDateAsc();
}
