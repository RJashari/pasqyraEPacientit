 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbbank.pasqyra.servisi;

import com.bpbbank.ClientReport;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Logger;
import com.bpbbank.pasqyra.service.ServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author rinor.jashari
 */
public class GjeneratorPDF {

    //Lloger factory ??
    private static final Logger LOGGER = Logger.getLogger(GjeneratorPDF.class.getName());
    private final String BASE_LOCATION_FOR_PDFS;
    private final String DAY_SPECIFIC_LOCATION;
    private static final int DATE_COL_WIDTH = 75;
    private static final int DESCRIPTION_COL_WIDTH = 128;
    private static final int CURRENCY_COL_WIDTH = 75;
    private static final int TRANSACTIONS_SUM_COL_WIDTH = 90;
    private static final int INSTALLMENTS_COL_WIDTH = 75;
    private static final int INSTALLMENT_NUMBER_COL_WIDTH = 75;
    private String periodStart;
    private String periodEnd;
    String acc;

    public GjeneratorPDF(String baseLocationForPdfs, String daySpecificLocation, String periodStart, String periodEnd) {
        this.BASE_LOCATION_FOR_PDFS = baseLocationForPdfs;
        this.DAY_SPECIFIC_LOCATION = daySpecificLocation;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public String gjeneroPdf(ClientReport cp) throws FileNotFoundException, MalformedURLException, ParseException{

        
        String fileName = new StringBuilder()
                .append(BASE_LOCATION_FOR_PDFS)
                .append(DAY_SPECIFIC_LOCATION)
//                .append(cp.getFromDate())
                .append("_")
                .append(cp.getAccount())
                .append(".pdf")
                .toString();

        acc = cp.getAccount();
        PdfWriter pdfWriter;
        pdfWriter = new PdfWriter(fileName);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        Image bpbLogo = new Image(ImageDataFactory.create("bpblogo.bmp"));
        bpbLogo.scaleToFit(150, 150);

        Table header = new Table(2);
        header.addCell(new Cell().add(bpbLogo).setBorder(Border.NO_BORDER));


        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy    HH:mm");
        

//        System.out.println("Current Date: " + ft.format(dNow));

//        MonthlyVccStatementInfo statementInfo = statement.get(ServiceImpl.REPORT_PERIOD);
        String dataeShtypjes = "Data e shtypjes";
        document.add(new Paragraph(dataeShtypjes + "              " + ft.format(dNow))
                .setWidthPercent(100).setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(2.0f).setPaddingBottom(2.0f)
                .setPaddingRight(10.0f)
                .setFontSize(8.0f));

        Paragraph headerParagraf = new Paragraph();
        headerParagraf.setWidthPercent(100);
        headerParagraf.setTextAlignment(TextAlignment.CENTER);
        headerParagraf.add("Qarkullimi per llogari");//statement
        headerParagraf.setBold();
        document.add(header);
        document.add(new LineSeparator(new SolidLine()));
        document.add(headerParagraf);
        
        
        Table t = new Table(new float[]{80.0f, 100.0f});
        Table t1 = new Table(new float[]{370.0f,100.0f, 50.0f});
        Table t2 = new Table(new float[]{80.0f, 100.0f});
        Table t3 = new Table(new float[]{265.0f,65.0f,65.0f,65.0f,65.0f});
        Table t4 = new Table(new float[]{80.0f, 100.0f, 40.0f, 80.0f});
        Table t5 = new Table(new float[]{80.0f, 100.0f});
        
        String periudha = "Periudha";
        Cell cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(periudha)
                .setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
        t5.addCell(cell);
        SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy.MM.dd");

        String reformattedStr = df1.format(df2.parse(periodStart));
        String reformattedStr1 = df1.format(df2.parse(periodEnd));
        cell = this.getCellWithDefaultParametersUpper();
        cp.setFromDate(reformattedStr);
        cp.setToDate(reformattedStr1);
        cell.add(new Paragraph(cp.getFromDate() + " - " + cp.getToDate())
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(8.0f));
        t5.addCell(cell);

        String llogaria = "Llogaria";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(llogaria)
                .setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
        t4.addCell(cell);
        
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(cp.getAccount())
                .setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
        t4.addCell(cell);
        String currency = cp.getCurrency();
        if(currency.equals("978")) currency = "EUR";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(currency)
                .setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
        t4.addCell(cell);
        
        
        String korporatat = cp.getPershrimiKlientit();
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(korporatat)
                .setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
        t4.addCell(cell);
        
        String klienti = "Klienti";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(klienti)
                .setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
        t.addCell(cell);

        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(cp.getInfoKompania()+"\n"+cp.getAdresa()+"\n"+cp.getInfoLokacioni())
                .setBorder(Border.NO_BORDER)
                .setFontSize(8.0f));
        t.addCell(cell);
            
        
        cell = this.getCellWithDefaultParametersUpper();
        t1.addCell(cell);
        
        String shumaeMbajtur = "Shuma e mbajtur";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(shumaeMbajtur)
                .setPaddingRight(0.0f)
                .setFontSize(8.0f)
                .setMarginLeft(10.0f)
                .setBorder(Border.NO_BORDER));
        t1.addCell(cell);
        
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(cp.getShumaEMbajtur())
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(8.0f)
                .setBorder(Border.NO_BORDER));
        t1.addCell(cell);
        
        String iban = "IBAN";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(iban)
                .setFontSize(8.0f)
                .setBorder(Border.NO_BORDER));
        t2.addCell(cell);
        
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(cp.getIban())
                .setFontSize(8.0f)
                .setBorder(Border.NO_BORDER));
        t2.addCell(cell);

        document.add(t5);
        document.add(t4);
        document.add(t);
        document.add(t1);
        document.add(t2);
        
        
        document.add(this.getTransactionsTable(cp.getTransactions()));
        
        cell = this.getCellWithDefaultParametersUpper();
        t3.addCell(cell);
        
        String qarkullimi = "Qarkullimi";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(qarkullimi)
                .setBold()
                .setWidthPercent(100).setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(2.0f).setPaddingBottom(1.0f)
                .setFontSize(8.0f));
        t3.addCell(cell);
        //spo di ku me marr qarkullimiin Debi
//        String qarkullimiDebi =  "14,999.10";//temp
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(cp.getQarkullimiDebi())
//        cell.add(new Paragraph(qarkullimiDebi)
                .setBold()
                .setWidthPercent(100).setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(2.0f).setPaddingBottom(1.0f)
                .setFontSize(8.0f));
        t3.addCell(cell);
        
        //spo di ku me marr qarkullimiin Kredi
        cell = this.getCellWithDefaultParametersUpper();
//        String qarkullimiKredi = "6,694.66";
        cell.add(new Paragraph(cp.getQarkullimiKredi())
//        cell.add(new Paragraph(qarkullimiKredi)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(2.0f).setPaddingBottom(1.0f)
                .setFontSize(8.0f));
        t3.addCell(cell);
        
        cell = this.getCellWithDefaultParametersUpper();
        t3.addCell(cell);
        
        cell = this.getCellWithDefaultParametersUpper();
        t3.addCell(cell);
        
        String totalLabel = "Total";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(totalLabel)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(1.0f).setPaddingBottom(2.0f)
                .setFontSize(8.0f));
        t3.addCell(cell);
        
//        String totalDebi = "5,771,614.12";
         //spo di ku me marr totalin e qarkullimit Debi
        cell = this.getCellWithDefaultParametersUpper();
      cell.add(new Paragraph(cp.getTotalDebi())
//        cell.add(new Paragraph(totalDebi)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(1.0f).setPaddingBottom(2.0f)
                .setFontSize(8.0f));
        t3.addCell(cell);
        
//        String totalKredi = "5,824,472.69";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(cp.getTotalKredi())
//        cell.add(new Paragraph(totalKredi)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(1.0f).setPaddingBottom(2.0f)
                .setFontSize(8.0f));
        t3.addCell(cell);
        
//         String total = "52,858.57";
        cell = this.getCellWithDefaultParametersUpper();
        cell.add(new Paragraph(cp.getTotal())
//        cell.add(new Paragraph(total)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddingTop(1.0f).setPaddingBottom(2.0f)
                .setFontSize(8.0f));
        t3.addCell(cell);
        
        document.add(t3);

        document.add(new Paragraph(cp.getpShqip())
                .setWidthPercent(100).setTextAlignment(TextAlignment.LEFT)
                .setPaddingTop(10.0f).setPaddingBottom(2.0f)
                .setPaddingRight(10.0f)
                .setFontSize(6.0f));
     

        document.add(new Paragraph(cp.getpEnglish())
                .setWidthPercent(100).setTextAlignment(TextAlignment.LEFT)
                .setPaddingTop(10.0f).setPaddingBottom(2.0f)
                .setPaddingRight(10.0f)
                .setFontSize(6.0f));
        document.close();

        return fileName;

    }

    protected Table getTransactionsTable(List<Transaction> transactions) {
        LOGGER.info("Writing transactions in pdf.");
        Table table = new Table(new float[]{70, 70, 70, 120, 65, 65, 65});
        table
                .addHeaderCell(this.getCellWithDefaultParameters().add("Data.trans\nTrans. date").setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Data valutes\nValue date").setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Referenca\nReference").setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Pershkrimi\nDescription").setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Debi\nDebit").setFontSize(9.0f).setTextAlignment(TextAlignment.RIGHT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Kredi\nCredit").setFontSize(9.0f).setTextAlignment(TextAlignment.RIGHT))
                .addHeaderCell(this.getCellWithDefaultParameters().add("Balansi\nBalance").setFontSize(9.0f).setTextAlignment(TextAlignment.RIGHT));

        transactions.forEach((transaction) -> {
            table
                    .addCell(this.getCellWithDefaultParameters().add(transaction.getDataTransferit()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(transaction.getDataValutes()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(transaction.getReferenca()).setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(transaction.getDescription()).setFontSize(8.0f).setTextAlignment(TextAlignment.LEFT))
                    .addCell(this.getCellWithDefaultParameters().add(transaction.getDebit()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().add(transaction.getCredit()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT))
                    .addCell(this.getCellWithDefaultParameters().add(transaction.getBalance()+"").setFontSize(8.0f).setTextAlignment(TextAlignment.RIGHT));
        });
        table.setBorderBottom(new SolidBorder(0.5f));
        return table;
    }


    Cell getCellWithDefaultParameters() {
        Cell cell = new Cell();
        cell.setPaddings(0.1f, 0f, 0f, 0f);
        cell.setHeight(2.0f);
        cell.setBorder(Border.NO_BORDER);
        cell.setBorderTop(new SolidBorder(0.5f));
//        cell.setBorderBottom(new SolidBorder(0.5f));
        return cell;
    }
    Cell getCellWithDefaultParametersUpper() {
        Cell cell = new Cell();
        cell.setPaddings(1.0f, 0f, 1.0f, 0.0f);
        cell.setHeight(2.0f);
        cell.setBorder(Border.NO_BORDER);
        return cell;
    }
    public String getFileName(){
        
        String filename = new StringBuilder()
                .append(BASE_LOCATION_FOR_PDFS)
                .append(DAY_SPECIFIC_LOCATION)
//                .append(cp.getFromDate())
                .append("_")
                .append(acc)
                .append(".pdf")
                .toString();
        
        return filename;

    }

}
