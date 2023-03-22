/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author lamis
 */
public class mailUtil {
    
     public static void sendMail(String recepient,String sender,String pass,String subject,String contenu,File f) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

       Properties props = new Properties();//he Properties class provides methods to get data from the properties file and store data into the properties file
         //It creates an empty property list with no default values.
        props.put("mail.smtp.ssl.trust", "*");//trust any host
        props.put("mail.smtp.auth", "true");//enable auth
        props.put("mail.smtp.port", "587");//port
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");//enable ssl
 //Your gmail address
        String myAccountEmail = sender;
        //Your gmail password
        String password = pass;
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
       

       
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient,subject,contenu,f);

        //Send mail
         Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String subject,String contenu,File f) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(subject);
            Multipart emailContent = new MimeMultipart();
   MimeBodyPart textBodyPart = new MimeBodyPart();
   textBodyPart.setText(contenu);
            if(f!=null){
   MimeBodyPart pdfAttachment = new MimeBodyPart();
   pdfAttachment.attachFile(f);
            emailContent.addBodyPart(pdfAttachment);
            }
   emailContent.addBodyPart(textBodyPart);
            message.setContent(emailContent);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(mailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
    
}
