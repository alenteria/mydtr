/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydtr.GraphicInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author arnel
 */
public class Config {

    private String excelName;
    private File config;

    public Config() throws IOException {
        config = new File("config.txt");
        if (config.exists()) {
            try {
                BufferedReader lnr = new BufferedReader(new FileReader("config.txt"));
                for (String a = lnr.readLine(); a != null; a = lnr.readLine()) {
                    excelName = a;
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Sorry, an unexpected error occured.");
                System.exit(0);
            } finally {
            }
        } else {




            excelName = JOptionPane.showInputDialog("Enter DTR Name");

            while (excelName == null || excelName.equals("")) {
                excelName = JOptionPane.showInputDialog("Gahig neg ulo dah. \nEnter DTR name ui");
            }

            FileOutputStream out = new FileOutputStream("config.txt");
            PrintStream p = new PrintStream(out);
            p.print(excelName);
            p.close();
            
            new Thread();

        }
    }

    public String getExcelName() {
        return excelName;
    }

    public static void main(String arg[]) {
        try {
            new Config();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                
            }
        });

    }
}
