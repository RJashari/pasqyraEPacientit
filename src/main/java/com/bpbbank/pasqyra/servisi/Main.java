/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * and open the template in the editor.
 */
package com.bpbbank.pasqyra.servisi;

import com.bpbbank.pasqyra.service.ServiceImpl;
import com.bpbbank.ClientReport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFileChooser;
import java.nio.file.*;
import org.apache.log4j.Logger;

/**
 *
 * @author rinor.jashari
 */
public class Main {
    
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException, ParseException, IOException, Exception{
        LOGGER.info("Starting the application up!");
        Properties properties = new Properties();
        SendMail sendMail = new SendMail();
        ClientReport cp = new ClientReport();
        
        try{
            
            properties.load(Main.class.getClassLoader().getResourceAsStream(args.length > 0 ? args[0] : "application.properties"));
            String baseLocationForPdfs = properties.getProperty("baseLocationForPdfs");
            String daySpecificLocation = Main.getDate();
            String folderDate;
            folderDate = Main.getDate();
          
            String periodStart = "2017.07.13";
            String periodEnd = "2017.07.20";
            Main.compareDate(periodStart, periodEnd);
            String id = "70433736";//1008419236 70433736
            String account = "1300001001505494";// 1300003300014778 1300001001505494
            String currency = "978";
           
            
            boolean isFolder = new File(baseLocationForPdfs+folderDate).isFile();
            
            
//            String daySpecificLocation = "12_09_2017";//Main.getDate();
            String saveFolder;
            if(isFolder){
                ServiceImpl serviceImpl = new ServiceImpl();
                ClientReport cr = serviceImpl.getClientMonthlyReport(periodStart, periodEnd, id, account, currency);
                GjeneratorPDF gjeneratorPdf = new GjeneratorPDF(baseLocationForPdfs+folderDate+"\\",daySpecificLocation,periodStart, periodEnd);
            
               gjeneratorPdf.gjeneroPdf(cr);
            
            String filename = gjeneratorPdf.getFileName();
            //            sendMail.SendEmail(filename); // ben dergimin e file-it me email, ka problem ne lidhjen me local
                
                
            } else {
                saveFolder = Main.createFolder(folderDate, baseLocationForPdfs);
                ServiceImpl serviceImpl = new ServiceImpl();

                ClientReport cr = serviceImpl.getClientMonthlyReport(periodStart, periodEnd, id, account, currency);
                GjeneratorPDF gjeneratorPdf = new GjeneratorPDF(baseLocationForPdfs+saveFolder+"\\",daySpecificLocation,periodStart, periodEnd);
                gjeneratorPdf.gjeneroPdf(cr);

                String filename = gjeneratorPdf.getFileName();
                
                
//            sendMail.SendEmail(filename); // ben dergimin e file-it me email, ka problem ne lidhjen me local
            }
            
            
            
            System.out.println(baseLocationForPdfs);

            
            
           
        
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    private static String fileLocation(){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        String filePath;
        chooser.showOpenDialog(null);
            
            File file = chooser.getSelectedFile();
            filePath = file.getAbsolutePath();
        
        return filePath+"\\";
    }
    static String getDate(){
        
        Date date = new Date();
        
        SimpleDateFormat ft = new SimpleDateFormat ("dd_MM_yyyy");
        return ft.format(date);
    }
    static String createFolder(String date, String path){
           
            File dir = new File(path+date);
            dir.mkdir();
        
        return date;
    }
    static void compareDate(String periodStart, String periodEnd) throws ParseException,Exception{
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        
        Date dateStart = simpleDateFormat.parse(periodStart);
        Date dateEnd = simpleDateFormat.parse(periodEnd);
        
        if(dateEnd.before(dateStart)){
            
        throw new IOException("Data e transaksionit duhet te jete me e vogel se data e valutes");
        }
        
    }

}
