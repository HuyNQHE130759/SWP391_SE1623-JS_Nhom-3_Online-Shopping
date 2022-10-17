package service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;



public class SendMail {

    public static void send(String to, String sub,
            String msg) throws AddressException,
            MessagingException {
        String user = "phongdhhe151364@fpt.edu.vn";
        String pass = "phong15081996@";
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        InternetAddress[] toAddresses = {new InternetAddress(to)};
        message.setRecipients(Message.RecipientType.TO, toAddresses);
        message.setSubject(sub);
        message.setSentDate(new Date());
        message.setText(msg);
        Transport.send(message);
    }

}
