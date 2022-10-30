/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {

    int id;
    float totalPrice;
    List<CartItem> items;

    public Cart(int id, float totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    boolean checkExist(int id) {
        for (CartItem item : items) {
            if (item.getProduct().getPid() == id) {
                return true;
            }
        }
        return false;
    }

    CartItem getItemById(int id) {
        for (CartItem item : items) {
            if (item.getProduct().getPid() == id) {
                return item;
            }
        }
        return null;
    }

    public void addItem(CartItem newItem) {
        if (checkExist(newItem.getProduct().getPid())) {
            CartItem oldItem = getItemById(newItem.getProduct().getPid());
            oldItem.setQuantity(oldItem.getQuantity() + newItem.getQuantity());
        } else {
            items.add(newItem);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public void editQuantity(int id, int newQuantity) {
        for (CartItem item : items) {
            if (item.getProduct().getPid() == id) {
                item.setQuantity(newQuantity);
                if (newQuantity == 0) {
                    items.remove(item);
                }
            }
        }
    }

    public float getTotalBill() {
        float sum = 0;
        for (CartItem item : items) {
            sum += (item.getQuantity() * item.getProduct().getPrice());
        }
        return sum;
    }

}
