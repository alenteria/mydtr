/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pndtr;

import java.io.*;
import java.util.Locale;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author anonymous
 */
public class Config {

    private static String excelName;
    private static File config;
    private static String configName = "config.naifjfox";
    //private WritableCellFormat timesBoldUnderline;
    //private WritableCellFormat times;

    public Config() {

        initialize();
        
    }
    
    public static void initialize(){
        config = new File(configName);
        if (config.exists()) {
            try {
                BufferedReader lnr = new BufferedReader(new FileReader(configName));
                for (String a = lnr.readLine(); a != null; a = lnr.readLine()) {
                    excelName = a;
                    createSheet();
                }
                lnr.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {

            createConfigTxt();

        }
    
    }

    public static String getExcelName() {
        File configFile = new File(configName);
        if (configFile.exists()) {
            try {
                BufferedReader lnr = new BufferedReader(new FileReader(configName));
                for (String a = lnr.readLine(); a != null; a = lnr.readLine()) {
                    excelName = a;
                    
                }
                lnr.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {

            createConfigTxt();

        }
        return excelName;
    }

    public static void createConfigTxt() {

        FileOutputStream out = null;

        excelName = JOptionPane.showInputDialog("Enter DTR Name");
        while (excelName == null || excelName.equals("")) {
            excelName = JOptionPane.showInputDialog("Gahig neg ulo dah. \nEnter DTR name ui");
        }

        excelName += ".xls";

        try {
            out = new FileOutputStream(configName);
            PrintStream p = new PrintStream(out);
            p.print(excelName);
            p.close();

            createSheet();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "An error encountered:" + ex + ". System will halt.");
            System.exit(0);
        } finally {
        }
    }

    private static void createSheet() {

        File file = new File(excelName);

        if (!file.exists()) {

            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));

            WritableWorkbook workbook;
            try {
                workbook = Workbook.createWorkbook(file, wbSettings);
                workbook.createSheet("Daily Time Record", 0);
                WritableSheet excelSheet = workbook.getSheet(0);
                try {
                    addInitialLabels(excelSheet);
                    // createContent(excelSheet);
                } catch (RowsExceededException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                } catch (WriteException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

                workbook.write();
                workbook.close();



            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    private static void addInitialLabels(WritableSheet excleSheet) throws RowsExceededException, WriteException {
        Label date = new Label(0, 0, "Date");
        Label timeIn = new Label(1, 0, "Time In");
        Label timeOut = new Label(2, 0, "Time Out");
        Label remarks = new Label(3, 0, "Remarks");
        excleSheet.addCell(date);
        excleSheet.addCell(timeIn);
        excleSheet.addCell(timeOut);
        excleSheet.addCell(remarks);
    }

}
