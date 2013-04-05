package mydtr;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class Mydtr {

  private WritableCellFormat timesBoldUnderline;
  private WritableCellFormat times;
  private String inputFile;
  
public void setOutputFile(String inputFile) {
  this.inputFile = inputFile;
  }

  public void write() throws IOException, WriteException {
    File file = new File(inputFile);
    WorkbookSettings wbSettings = new WorkbookSettings();

    wbSettings.setLocale(new Locale("en", "EN"));

    WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
    workbook.createSheet("Record", 0);
    WritableSheet excleSheet = workbook.getSheet(0);
     createLabel(excleSheet);
   // createContent(excelSheet);

    workbook.write();
    workbook.close();
  }
  public static boolean read(String inputFile) throws IOException  {
    File inputWorkbook = new File(inputFile);
    boolean returns = false;
    if(inputWorkbook.exists()){
        returns = true;
    }
    return returns;
  }

  private void createLabel(WritableSheet sheet)
      throws WriteException {
    // Lets create a times font
    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
    // Define the cell format
    times = new WritableCellFormat(times10pt);
    // Lets automatically wrap the cells
    times.setWrap(true);

    // Create create a bold font with unterlines
    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
        UnderlineStyle.SINGLE);
    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
    // Lets automatically wrap the cells
    timesBoldUnderline.setWrap(true);

    CellView cv = new CellView();
    cv.setFormat(times);
    cv.setFormat(timesBoldUnderline);
  

    // Write a few headers
    addCaption(sheet, 0, 0, "Date");
    addCaption(sheet, 1, 0, "Time In");
    addCaption(sheet, 2, 0, "Time Out");
    

  }

  private void createContent(WritableSheet sheet) throws WriteException,
      RowsExceededException {
    // Write a few number
    for (int i = 1; i < 10; i++) {
      // First column
      addNumber(sheet, 0, i, i + 10);
      // Second column
      addNumber(sheet, 1, i, i * i);
    }
    // Lets calculate the sum of it
    StringBuffer buf = new StringBuffer();
    buf.append("SUM(A2:A10)");
    Formula f = new Formula(0, 10, buf.toString());
    sheet.addCell(f);
    buf = new StringBuffer();
    buf.append("SUM(B2:B10)");
    f = new Formula(1, 10, buf.toString());
    sheet.addCell(f);

    // Now a bit of text
    for (int i = 12; i < 20; i++) {
      // First column
      addLabel(sheet, 0, i, "Boring text " + i);
      // Second column
      addLabel(sheet, 1, i, "Another text");
    }
  }

  private void addCaption(WritableSheet sheet, int column, int row, String s)
      throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, timesBoldUnderline);
    sheet.addCell(label);
  }

  private void addNumber(WritableSheet sheet, int column, int row,
      Integer integer) throws WriteException, RowsExceededException {
    Number number;
    number = new Number(column, row, integer, times);
    sheet.addCell(number);
  }

  private void addLabel(WritableSheet sheet, int column, int row, String s)
      throws WriteException, RowsExceededException {
    Label label;
    label = new Label(column, row, s, times);
    sheet.addCell(label);
  }
 private static void writeData(int row, int col,String val) throws IOException, WriteException{
      Workbook aWorkBook = null;
            try {
                aWorkBook = Workbook.getWorkbook(new File("Arnel-DTR.xls"));
            } catch (BiffException ex) {
                Logger.getLogger(Mydtr.class.getName()).log(Level.SEVERE, null, ex);
            }
                WritableWorkbook aCopy = Workbook.createWorkbook(new File("Arnel-DTR.xls"), aWorkBook);
                WritableSheet aCopySheet = aCopy.getSheet(0);//index of the needed sheet
                WritableCell aWritableCell = aCopySheet.getWritableCell(1,1);//no need!
                Label anotherWritableCell =  new Label(col,row ,val);
                //position of the new cell in column,row
            //can be a new Label() or new Number() or new Formula

            aCopySheet.addCell(anotherWritableCell);

           aCopy.write();
           aCopy.close();  
 }
  public static void main(String[] args) throws WriteException, IOException,ArrayIndexOutOfBoundsException, ParseException {
       String day = "";
       String time = "";
      if(read("Arnel-DTR.xls")){
         
            Workbook w = null;
            try {
                w = Workbook.getWorkbook(new File("Arnel-DTR.xls"));
            } catch (BiffException ex) {
                Logger.getLogger(Mydtr.class.getName()).log(Level.SEVERE, null, ex);
            }
        Sheet sheet =  w.getSheet(0);    
        
        int row = sheet.getRows();
        int rowToAdd = row;
         
        
            String date = (sheet.getCell(0, 0)).getContents();
            String timeIn = (sheet.getCell(1, 0)).getContents();
            String timeOut = (sheet.getCell(2,0)).getContents();
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dayF = new SimpleDateFormat("MM-dd-yyyy");
             day = dayF.format(cal.getTime());
            
             final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
             final String t = (String)sdf.format(cal.getTime());

            try {
                final Date dateObj = sdf.parse(t);
                time = new SimpleDateFormat("K:mm").format(dateObj);
            } catch (final ParseException e) {
                e.printStackTrace();
            }

            if(date.equals("Date")){
                rowToAdd +=1;
            }else{
                
            }
            if(date.equals(day)){
                if(timeIn.trim().equals("")){
                    //Put the time in
                   writeData(rowToAdd,1,time);                    
                }else{
                    //Put the timeOut
                    writeData(rowToAdd,2,time);
                }
            }else{
               rowToAdd +=1;
               writeData(rowToAdd,0,day);
               writeData(rowToAdd,1,time);
            }
            
          
       
        }else{
        Mydtr test = new Mydtr();
        test.setOutputFile("Arnel-DTR.xls");
        test.write();
        System.out.println("success! ");
        }
      }
    
} 