/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @author rinor.jashari
 */
package com.bpbbank.pasqyra.servisi;

import com.bpbbank.pasqyra.service.ServiceImpl;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;  

public class SendMail 
{ 
    public void SendEmail(String fileName) throws IOException
    {    
        
        Properties properties = System.getProperties();
        properties.load(SendMail.class.getClassLoader().getResourceAsStream("application.properties"));
        String to = properties.getProperty("mail.toEmail");//change accordingly   
        String user = properties.getProperty("mail.stmp.user");//change accordingly   
        String fromEmail = properties.getProperty("mail.sender.email");
        String password = properties.getProperty("mail.sender.password");//change accordingly     

        //1) get the session object      
          
        properties.setProperty("mail.smtp.host", "localhost");   
        properties.put("mail.smtp.auth", "true");    

        Session session = Session.getDefaultInstance(properties,   
                new javax.mail.Authenticator() {   
            protected PasswordAuthentication getPasswordAuthentication() {   
                return new PasswordAuthentication(fromEmail,password);    }   });       

        //2) compose message      
        try{    
            MimeMessage message = new MimeMessage(session);    
            message.setFrom(new InternetAddress(fromEmail));     
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
            message.setSubject("Message Aleart");         

            //3) create MimeBodyPart object and set your message text        
            BodyPart messageBodyPart1 = new MimeBodyPart();     
            messageBodyPart1.setText("This is message body");          

            //4) create new MimeBodyPart object and set DataHandler object to this object        
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
            //change accordingly     
            DataSource source = new FileDataSource(fileName);    
            messageBodyPart2.setDataHandler(new DataHandler(source));    
            messageBodyPart2.setFileName(fileName);             

            //5) create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();    
            multipart.addBodyPart(messageBodyPart1);     
            multipart.addBodyPart(messageBodyPart2);      

            //6) set the multiplart object to the message object    
            message.setContent(multipart );        

            //7) send message    
            Transport.send(message);      
            System.out.println("message sent....");   

        }catch (MessagingException ex) {ex.printStackTrace();}  
    }
} 
 