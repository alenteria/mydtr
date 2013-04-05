/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pndtr;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author anonymous
 */
public class PNDTR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Config.initialize();

        DTR.addTimeIn();
        
        TimeChecker.checkTime();

    }
}
