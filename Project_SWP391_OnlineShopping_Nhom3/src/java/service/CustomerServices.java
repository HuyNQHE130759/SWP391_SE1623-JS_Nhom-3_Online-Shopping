/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DAO;
import java.util.Random;

/**
 *
 * @author HuyNQ
 */
public class CustomerServices {

    public String resetCustomerPassword(String email) {
        DAO dao = new DAO();
        int uid = dao.findIdByEmail(email);
        if(uid ==-1){
            return "EmailNotFound";
        }
        String randomPassword = new CustomerServices().randomPassword();
        dao.changePassword(uid, randomPassword);
        return randomPassword;
    }

    public String randomPassword() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString);
        return generatedString;
    }
}