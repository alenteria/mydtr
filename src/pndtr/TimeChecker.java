/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pndtr;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author anonymous
 */
public class TimeChecker {

    private static int confirmLogout = 3;

    public static void checkTime() {


        final Timer checkT = new Timer(false);

        checkT.scheduleAtFixedRate(
                
                new TimerTask() {

                    public void run() {
                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        String currentTime = sdf.format(cal.getTime());
                        //JOptionPane.showMessageDialog(null, currentTime);
                        if (currentTime.equals("01:11")) {
                            confirmLogout = JOptionPane.showConfirmDialog(null, currentTime + ". Save DTR logout now?");
                            if (confirmLogout == 0) {
                                DTR.addTimeOut();
                                checkT.cancel();
                                System.exit(0);
                            }
                        }
                    }
                }, 0, 40 * 1000);
    }
}
