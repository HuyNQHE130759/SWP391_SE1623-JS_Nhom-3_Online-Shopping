/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Huynq
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    int reviewid;
    int cid;
    int pid;
    String user_comment;
    int user_rating;
    Date user_timecomment;
    boolean status;
    String img;
    Product product;
    User user;
}
