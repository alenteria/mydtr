/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydtr.GraphicInterface;

/**
 *
 * @author arnel
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 * Simple demo that uses java.util.Timer to schedule a task 
 * to execute once 5 seconds have passed.
 */

public class TimeChecker {
    Timer timer;

    public TimeChecker() {
        timer = new Timer();
        timer.schedule(new RemindTask(), 1*1000);
 }

    class RemindTask extends TimerTask {
        public void run() {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String currentTime = sdf.format(cal.getTime());
            System.out.println(currentTime);
            
            int confLogout = 2;
            
            if( true/*currentTime.equals("17:20")*/){
               confLogout = JOptionPane.showConfirmDialog(null, "Do you want to save your logout now?");
               if(confLogout == 0){
                   //Log to DTR
                   
                   System.exit(0);
               }else{
                  new TimeChecker();
               }
            }
            else{
                new TimeChecker();
            }
            
              //Terminate the timer thread
            
        }
    }

    public static void main(String args[]) {
        new TimeChecker();
        System.out.format("Task scheduled.%n");
    }
}