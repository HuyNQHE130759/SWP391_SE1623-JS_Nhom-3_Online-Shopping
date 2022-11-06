/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import entity.Bill;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynq
 */
public class Support {
    public static ArrayList<Bill> paging(int index, int numberOfBill, ArrayList<Bill> list_bill) {
        ArrayList<Bill> list = new ArrayList<>();
        int lastIndex = Math.min(index * numberOfBill, list_bill.size());
        for (int i = (index - 1) * numberOfBill; i < lastIndex; i++) {
            list.add(list_bill.get(i));
        }
        return list;
    }
    
    public static Date ConvertStringToDate(String date){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            return formatter.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Support.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
