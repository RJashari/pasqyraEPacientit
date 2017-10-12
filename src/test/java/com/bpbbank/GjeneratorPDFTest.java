/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbbank;

//import org.junit.Before;
import org.junit.Test;
import com.bpbbank.pasqyra.servisi.GjeneratorPDF;
import com.bpbbank.pasqyra.servisi.Transaction;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rinor.jashari
 */
public class GjeneratorPDFTest {
    
   GjeneratorPDF gj = new GjeneratorPDF("C:\\Users\\rinor.jashari\\Documents\\","2017_11_08\\", "2017.08.01", "2017.08.02" );
    
    @Test
    public void test() throws ParseException{
       try {
           ClientReport cp = this.getClientReport();
           String rez = gj.gjeneroPdf(cp);
//        assertEquals("", rez);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(GjeneratorPDFTest.class.getName()).log(Level.SEVERE, null, ex);
       } catch (MalformedURLException ex) {
           Logger.getLogger(GjeneratorPDFTest.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
       private ClientReport getClientReport(){
        ClientReport clientReport = new ClientReport();
        clientReport.setFromDate("13.07.2017");
        clientReport.setToDate("14.07.2017");
        clientReport.setAccount("1300001001505494");
        clientReport.setCurrency("EUR");
        clientReport.setInfoKompania("K.U.R PRISHTINA SH.A");
        clientReport.setAdresa("TAHIR ZAJMI");
        clientReport.setInfoLokacioni("PRISHTINE");
        clientReport.setShumaEMbajtur("0.00");
        clientReport.setIban("XK05 1300 0100 0150 5494");
        clientReport.setDataEShtypjes("13.08.2017");
        clientReport.setShumaEMbajtur("0.00");
        clientReport.setQarkullimiDebi("14,999.10");
        clientReport.setQarkullimiKredi("6,694.66");
        clientReport.setTotalDebi("5,771,614.12");
        clientReport.setTotalKredi("5,824,472.69");
        clientReport.setTotal("52,858.57");
        clientReport.setTransactions(this.getMyTransactions());
        return clientReport;
    }
    
       private List<Transaction> getMyTransactions() {
           List<Transaction> res = new ArrayList();
           Transaction t = new Transaction();
           Transaction t1 = new Transaction();
           Transaction t2 = new Transaction();
           Transaction t3 = new Transaction();
           
//           
//           t.setDataTransferit("14.08.2017");
//           t.setDataValutes("14.07.2017"); 
//           t.setReferenca("590000435667 ");
//           t.setDescription("Tranaksion"); 
//           t.setDebit("20");
//           t.setCredit("20");
//           t.setBalance("145");
//           res.add(t);
//           
//           t1.setDataTransferit("14.08.2017");
//           t1.setDataValutes("14.07.2017"); 
//           t1.setReferenca("590000435667 ");
//           t1.setDescription("Tranaksion"); 
//           t1.setDebit("20");
//           t1.setCredit("20");
//           t1.setBalance("145");
//           res.add(t1);
//           
//           t2.setDataTransferit("14.08.2017");
//           t2.setDataValutes("14.07.2017"); 
//           t2.setReferenca("590000435667 ");
//           t2.setDescription("Tranaksion"); 
//           t2.setDebit("20");
//           t2.setCredit("20");
//           t2.setBalance("145");
//           res.add(t2);
//           
//           t3.setDataTransferit("14.08.2017");
//           t3.setDataValutes("14.07.2017"); 
//           t3.setReferenca("590000435667 ");
//           t3.setDescription("Tranaksion"); 
//           t3.setDebit("20");
//           t3.setCredit("20");
//           t3.setBalance("145");
//           res.add(t3);
           return res;
       }
}

