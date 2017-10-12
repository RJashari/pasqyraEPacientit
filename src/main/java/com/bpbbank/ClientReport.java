/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbbank;

import com.bpbbank.pasqyra.servisi.Transaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author rinor.jashari
 */
public class ClientReport {

    private String pershrimiKlientit;
    private String customerId;
    private String dataEShtypjes;
    private String fromDate;
    private String toDate;
    private String account;
    private String currency;
    private String infoKompania;
    private String adresa;
    private String cardNumber;
    private String infoLokacioni;
    private String shumaEMbajtur;
    private String iban;
    private String qarkullimiDebi;
    private String qarkullimiKredi;
    private String totalDebi;
    private String totalKredi;
    private String total;
    private int month;
    private int year;
    private String reportPath;
    private String error;
    private boolean pdfGenerated;
    private boolean status;
    private String timeStamp;
    private String pershkrimi;
    private String referenca;
    private String balansi;
    private String pShqip;
    private String pEnglish;
    

    private List<Transaction> transactions = new ArrayList<>();

    public String getPershrimiKlientit() {
        return pershrimiKlientit;
    }

    public void setPershrimiKlientit(String pershrimiKlientit) {
        this.pershrimiKlientit = pershrimiKlientit;
    }
    
    public String getpEnglish() {
        return pEnglish;
    }

    public String getpShqip() {
        return pShqip;
    }

    public void setpEnglish(String pEnglish) {
        this.pEnglish = pEnglish;
    }

    public void setpShqip(String pShqip) {
        this.pShqip = pShqip;
    }

    public String getBalansi() {
        return balansi;
    }
    
    public void setBalansi(String balansi) {
        this.balansi = balansi;
    }

    
    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public String getReferenca() {
        return referenca;
    }

    public void setReferenca(String referenca) {
        this.referenca = referenca;
    }

    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean isStatus() {
        return status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setCustomerId(String costumerId) {
        this.customerId = costumerId;
    }

    public String getReportPath() {
        return reportPath;
    }

    public String getError() {
        return error;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isPdfGenerated() {
        return pdfGenerated;
    }

    public void setPdfGenerated(boolean pdfGenerated) {
        this.pdfGenerated = pdfGenerated;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getAccount() {
        return account;
    }

    public String getCurrency() {
        return currency;
    }

    public String getInfoKompania() {
        return infoKompania;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getInfoLokacioni() {
        return infoLokacioni;
    }

    public String getShumaEMbajtur() {
        return shumaEMbajtur;
    }

    public String getIban() {
        return iban;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setInfoKompania(String infoKompania) {
        this.infoKompania = infoKompania;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setInfoLokacioni(String infoLokacioni) {
        this.infoLokacioni = infoLokacioni;
    }

    public void setShumaEMbajtur(String shumaEMbajtur) {
        this.shumaEMbajtur = shumaEMbajtur;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDataEShtypjes() {
        return dataEShtypjes;
    }

    public void setDataEShtypjes(String dataEShtypjes) {
        this.dataEShtypjes = dataEShtypjes;
    }
    private static final Logger LOG = Logger.getLogger(ClientReport.class.getName());
    
    public String getQarkullimiDebi() {
        return qarkullimiDebi;
    }

    public String getQarkullimiKredi() {
        return qarkullimiKredi;
    }

    public String getTotalDebi() {
        return totalDebi;
    }

    public String getTotalKredi() {
        return totalKredi;
    }

    public String getTotal() {
        return total;
    }

    public void setQarkullimiDebi(String qarkullimiDebi) {
        this.qarkullimiDebi = qarkullimiDebi;
    }

    public void setQarkullimiKredi(String qarkullimiKredi) {
        this.qarkullimiKredi = qarkullimiKredi;
    }

    public void setTotalDebi(String totalDebi) {
        this.totalDebi = totalDebi;
    }

    public void setTotalKredi(String totalKredi) {
        this.totalKredi = totalKredi;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ClientReport{" + "fromDate=" + fromDate + ", toDate=" + toDate + ", account=" + account + ", currency=" + currency + ", infoKompania=" + infoKompania + ", infoEmriKlinetit=" + adresa + ", infoLokacioni=" + infoLokacioni + ", shumaEMbajtur=" + shumaEMbajtur + ", iban=" + iban + '}';
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
