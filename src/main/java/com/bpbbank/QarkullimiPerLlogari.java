/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbbank;
/**
 *
 * @author rinor.jashari
 */
public class QarkullimiPerLlogari {
    
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
        return clientReport;
    }
    
        
}