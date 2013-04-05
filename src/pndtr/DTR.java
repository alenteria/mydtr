/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pndtr;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author anonymous
 */
public class DTR {

    private static String excelName = Config.getExcelName();

    public DTR() {
    }

    public static String getExcelName() {
        return Config.getExcelName();
    }

    private static void writeData(int row, int col, String val) {
        try {
            Workbook aWorkBook = null;
            try {
                try {
                    aWorkBook = Workbook.getWorkbook(new File(excelName));
                } catch (IOException ex) {
                    Logger.getLogger(DTR.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (BiffException ex) {
                Logger.getLogger(DTR.class.getName()).log(Level.SEVERE, null, ex);
            }
            WritableWorkbook aCopy = Workbook.createWorkbook(new File(excelName), aWorkBook);
            WritableSheet aCopySheet = aCopy.getSheet(0);//index of the needed sheet
            WritableCell aWritableCell = aCopySheet.getWritableCell(1, 1);//no need!
            Label anotherWritableCell = new Label(col, row, val);
            //position of the new cell in column,row
            //can be a new Label() or new Number() or new Formula

            aCopySheet.addCell(anotherWritableCell);

            aCopy.write();
            aCopy.close();
        } catch (WriteException ex) {
            Logger.getLogger(DTR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DTR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addTimeIn(){

        String day = "";
        String time = "";

        Workbook w = null;

        try {
            try {
                w = Workbook.getWorkbook(new File(excelName));
            } catch (IOException ex) {
                Logger.getLogger(DTR.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (BiffException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.exit(0);
        }

        Sheet sheet = w.getSheet(0);

        int row = sheet.getRows() - 1;
        int rowToAdd = row;


        String date = (sheet.getCell(0, row)).getContents();
        String timeIn = (sheet.getCell(1, row)).getContents();
        String timeOut = (sheet.getCell(2, row)).getContents();
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat dayF = new SimpleDateFormat("MM-dd-yyyy");
        day = dayF.format(cal.getTime());

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        final String t = (String) sdf.format(cal.getTime());

        try {
            final Date dateObj = sdf.parse(t);
            time = new SimpleDateFormat("K:mm").format(dateObj);
        } catch (final ParseException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }

        if (date.equals("Date")) {
            rowToAdd += 1;
        } else {
        }

        if (date.equals(day)) {
            if (timeIn.trim().equals("")) {
                //Put the time in
                writeData(rowToAdd, 1, time);
            }
        } else {
            rowToAdd += 1;
            writeData(rowToAdd, 0, day);
            writeData(rowToAdd, 1, time);
        }

    }

    public static void addTimeOut() {
        String day = "";
        String time = "";
        Workbook w = null;
        try {
            try {
                w = Workbook.getWorkbook(new File(excelName));
            } catch (IOException ex) {
                Logger.getLogger(DTR.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (BiffException ex) {
            Logger.getLogger(DTR.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sheet sheet = w.getSheet(0);

        int row = sheet.getRows() - 1;
        int rowToAdd = row;
        //System.out.println(row);

        String date = (sheet.getCell(0, row)).getContents();
        String timeIn = (sheet.getCell(1, row)).getContents();
        String timeOut = (sheet.getCell(2, row)).getContents();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dayF = new SimpleDateFormat("MM-dd-yyyy");
        day = dayF.format(cal.getTime());

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        final String t = (String) sdf.format(cal.getTime());

        try {
            final Date dateObj = sdf.parse(t);
            time = new SimpleDateFormat("K:mm").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        if (date.equals("Date")) {
            rowToAdd += 1;
        } else {
        }
        System.out.println(time);
        if (date.equals(day)) {
            writeData(rowToAdd, 2, time);

        } else {

            rowToAdd += 1;
            writeData(rowToAdd, 0, day);
            writeData(rowToAdd, 2, time);
        }

    }
}
