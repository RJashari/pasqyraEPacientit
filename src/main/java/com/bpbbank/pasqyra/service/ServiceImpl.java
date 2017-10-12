/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbbank.pasqyra.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import com.bpbbank.pasqyra.servisi.GjeneratorPDF;
import com.bpbbank.ClientReport;
import com.bpbbank.pasqyra.servisi.MainFrame;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import javax.mail.Session;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import com.bpbbank.pasqyra.servisi.Transaction;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;



public class ServiceImpl {

    private ClientReport[] clientReport;
    private GjeneratorPDF gjeneratorPdf;
    private int index;

    private final Logger LOGGER = Logger.getLogger(ServiceImpl.class);
    private static Properties properties = ServiceImpl.getProperties("application.properties");
    private Connection connection = ServiceImpl.getConnection();
    private Session session;// = Session.getDefaultInstance(properties);

    private static final Map<Integer, Integer> NUMBER_OF_DAYS_IN_MONTH = new HashMap<>();
    private static final String CP_STORED_PROCEDURE = "{call cp_mail_getClients(?,?)}";

    public static final String FROM_DATE = "";
    public static final String TO_DATE = "param2";
    public static final String COMPANY_NAME = "IME";
    public static final String CLIENT_FULL_NAME = "param6";
    public static final String INFO_LOKACIONI = "param7";
    public static final String IBAN = "param8";
    public static final String CURRENCY = "param10";
    public static final String KORPORATAT = "interest";
    public static final String SHUMA_E_MBAJTUR = "interest";
    public static final String INTEREST_REVOLVING_PRODUCT = "interest";
    public static final String PENALTY_INTEREST_INSTALLMENT_REVOLVING = "penalty";
    public static final String FEES = "param11";
    public static final String TOTAL_TO_BE_PAID = "param12";
    public static final String PAYMENT_DEADLINE = "param13";
    public static final String APPROVED_LIMIT = "param14";
    public static final String NOT_USED_LIMIT = "param15";
    public static final String TRANSACTIONS_DESCRIPTION = "param18";
    public static final String NOTIFICATION_1 = "param29";
    public static final String NOTIFICATION_2 = "param30";
    public static final String NOTE = "param34";
    public static final String SENT_DATE = "param35";
    private String exception;

    private void initNumberOfDaysInMonth() {
        NUMBER_OF_DAYS_IN_MONTH.put(1, 31);
        NUMBER_OF_DAYS_IN_MONTH.put(2, 28);
        if (Calendar.getInstance().get(Calendar.YEAR) % 4 == 0) {
            NUMBER_OF_DAYS_IN_MONTH.put(2, 29);
        }
        NUMBER_OF_DAYS_IN_MONTH.put(3, 31);
        NUMBER_OF_DAYS_IN_MONTH.put(4, 30);
        NUMBER_OF_DAYS_IN_MONTH.put(5, 31);
        NUMBER_OF_DAYS_IN_MONTH.put(6, 30);
        NUMBER_OF_DAYS_IN_MONTH.put(7, 31);
        NUMBER_OF_DAYS_IN_MONTH.put(8, 31);
        NUMBER_OF_DAYS_IN_MONTH.put(9, 30);
        NUMBER_OF_DAYS_IN_MONTH.put(10, 31);
        NUMBER_OF_DAYS_IN_MONTH.put(11, 30);
        NUMBER_OF_DAYS_IN_MONTH.put(12, 31);
    }

    public ServiceImpl() {

    }
    
    public ServiceImpl(GjeneratorPDF gjeneratorPdf) {
        super();
        this.gjeneratorPdf = gjeneratorPdf;

    }

    private static Properties getProperties(String filename) {
        Properties properties = new Properties();
        try {
            properties.load(ServiceImpl.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Connection getConnection() {
        Driver driver = new SQLServerDriver();
        try {
            String dbUrl = properties.getProperty("db.url");
            String dbName = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            DriverManager.registerDriver(driver);
            return DriverManager.getConnection(dbUrl, dbName, dbPassword);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException(sqlException.getMessage());
        }
    }

    

    public ClientReport getClientMonthlyReport(String periodStart, String periodEnd, String id, String account, String currency) throws SQLException, ParseException {

        LOGGER.info("Retreiving from the database all client reports from id: " + id);

        ClientReport cReport = new ClientReport();

        CallableStatement statement;
        String sp = "{call pasqyrat_e_klienteve(?, ?, ?, ?, ?)}";
        try {
            statement = connection.prepareCall(sp);

            statement.setString(1, periodStart);
            statement.setString(2, periodEnd);
            statement.setString(4, id);
            statement.setString(3, account);
            statement.setString(5, currency);
            statement.execute();
            
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            cReport.setInfoKompania(resultSet.getString("IME")+" "+resultSet.getString("PREZIME"));
            cReport.setCustomerId(resultSet.getString("EMBG"));
            cReport.setAdresa(resultSet.getString("ADRESA"));
            cReport.setInfoLokacioni(resultSet.getString("MESTO"));
            
            cReport.setFromDate(periodStart);
            cReport.setFromDate(periodEnd);
//            opis, id
            while(!statement.getMoreResults())statement.getResultSet();
            resultSet = statement.getResultSet();
            resultSet.next();

//          amountonhold
            while(!statement.getMoreResults())statement.getResultSet();
            resultSet= statement.getResultSet();
            resultSet.next(); 
            cReport.setShumaEMbajtur(resultSet.getString("AMOUNTONHOLD"));
            cReport.setPershrimiKlientit(resultSet.getString("Pershkrimi"));

//          no column name
            while(!statement.getMoreResults())statement.getResultSet();
            resultSet= statement.getResultSet();
            resultSet.next(); 

            
//          dp, iznos
            while(!statement.getMoreResults())statement.getResultSet();
            resultSet= statement.getResultSet();
            resultSet.next(); 
            
            //KOD
            while(!statement.getMoreResults())statement.getResultSet();
//            List<Transaction> transaction = new ArrayList();
            resultSet = statement.getResultSet();      
            while(resultSet.next()){
                
//               sostojba.add(resultSet.getString("SOSTOJBA"));
                
            
            
                
            cReport.setAccount(resultSet.getString("PARTIJA").trim());
            cReport.setCurrency(resultSet.getString("VALUTA").trim());
            
            }
              
            
            //descript_subtype
            while(!statement.getMoreResults())statement.getResultSet();
            resultSet= statement.getResultSet();
            resultSet.next();
            cReport.setIban(resultSet.getString("IBAN"));

            
            
            //tipstavka
            int i =0;
            while(!statement.getMoreResults())statement.getResultSet();
            resultSet = statement.getResultSet();  
            List<Transaction> transaction = new ArrayList();

//            String sostojbaString = sostojba.get(i);
           
            BigDecimal qarkullimiDebit = toDecimal("0");
            BigDecimal qarkullimiKredit = toDecimal("0");
            BigDecimal totaliKredit = toDecimal("0");
            BigDecimal totaliDebit = toDecimal("0");
            BigDecimal sostojba_dec = toDecimal("0");
            String sostojbaFormated;
            String totaliKreditFormated;
            String totaliDebitFormated;
            String debitFormated;
            String kreditFormated;
//            DecimalFormat df = new DecimalFormat();
//              df.setMinimumFractionDigits(3);
//              df.setMaximumFractionDigits(3);
           
            while(resultSet.next()){
                Transaction transactions = new Transaction();
                
                String dataTrans = resultSet.getString("dkniz");
                String dataVal = resultSet.getString("dval");
                String dataTrans_date = toDate(dataTrans);
                String dataVal_date = toDate(dataVal);
                String debit = resultSet.getString("debit");
                String credit = resultSet.getString("credit");
                BigDecimal debit_dec = toDecimal(debit);
                BigDecimal credit_dec = toDecimal(credit);
                if(transaction.size() < 1){
                     sostojba_dec = credit_dec.subtract(debit_dec);
                }
//                sostojbaFormated = formatBigDecimal(sostojba_dec);

                if(transaction.size()<1){
                    totaliDebit = debit_dec;
                    totaliKredit = credit_dec;
                }
                else{
                    qarkullimiDebit = qarkullimiDebit.add(debit_dec);
                    qarkullimiKredit = qarkullimiKredit.add(credit_dec);
                    totaliDebit = totaliDebit.add(debit_dec);
                    totaliKredit = totaliKredit.add(credit_dec);
                }  
                if(transaction.size()>=1){
                    sostojba_dec = sostojba_dec.add(credit_dec).subtract(debit_dec);
                }
                kreditFormated = formatBigDecimal(credit_dec);
                debitFormated = formatBigDecimal(debit_dec);
                sostojbaFormated = formatBigDecimal(sostojba_dec);
                transactions.setDataTransferit(dataTrans_date);
                transactions.setDataValutes(dataVal_date);
                transactions.setReferenca(resultSet.getString("unikum").trim());
                transactions.setDescription(resultSet.getString("opis"));
                transactions.setDebit(debitFormated);
                transactions.setCredit(kreditFormated);
                transactions.setBalance(sostojbaFormated);
                

                
                totaliKreditFormated = formatBigDecimal(totaliKredit);
                totaliDebitFormated = formatBigDecimal(totaliDebit);
                String qarkullimiDebitFormated = formatBigDecimal(qarkullimiDebit);
                String qarkullimiKreditFormated = formatBigDecimal(qarkullimiKredit);
                cReport.setTotal(sostojbaFormated);
                cReport.setTotalKredi(totaliKreditFormated);
                cReport.setTotalDebi(totaliDebitFormated);
                cReport.setQarkullimiDebi(qarkullimiDebitFormated);
                cReport.setQarkullimiKredi(qarkullimiKreditFormated);
//                cReport.setTotal(sostojba_dec+"");
//                cReport.setTotalKredi(totaliKredit+"");
//                cReport.setTotalDebi(totaliDebit+"");
//                cReport.setQarkullimiDebi(qarkullimiDebit+"");
//                cReport.setQarkullimiKredi(qarkullimiKredit+"");
                
                transaction.add(transactions);

                cReport.setTransactions(transaction);


            }

            while(!statement.getMoreResults())statement.getResultSet();
            resultSet= statement.getResultSet();
            resultSet.next(); 
            cReport.setpShqip(resultSet.getString("vrednost").trim());
            
            while(!statement.getMoreResults())statement.getResultSet();
            resultSet= statement.getResultSet();
            resultSet.next();
            cReport.setpEnglish(resultSet.getString("vrednost"));

            while(!statement.getMoreResults())statement.getResultSet();
            while (resultSet == null) {
                resultSet = statement.getResultSet();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to generate Report", e);
            System.out.println("Failed to generate Report" + e);
            exception = e.getMessage();
          
        }
        
        return cReport;
    }
    
    private BigDecimal toDecimal(String value){
        
        
        BigDecimal valueDecimal = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);//rounding Gabimi 1, duhet me testu
        
        return valueDecimal;        
    }
    private String toDate(String data) throws ParseException{
        
//        String test = "2017.09.03";
        SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy.MM.dd");
        
        String reformattedStr = df1.format(df2.parse(data));
        

        return reformattedStr;
    }
    private String formatBigDecimal(BigDecimal bd){
        
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat)nf;
        
        df.setGroupingSize(3);
        df.setRoundingMode(RoundingMode.HALF_UP);
        
        return df.format(bd);
        
    
    }
//    public void setExceptions(Exception e){
//        String exc = e.getMessage();
//        exception = exc;
//        
//    }
    public String getExceptions(){
        return exception;
    }
}
