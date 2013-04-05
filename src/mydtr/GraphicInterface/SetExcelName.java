/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mydtr.GraphicInterface;

import javax.swing.JOptionPane;

/**
 *
 * @author OJT06
 */
public class SetExcelName {
    
    private JOptionPane excelNameSetter = new JOptionPane("Set DTR Name");
    
    public SetExcelName(){
        initUI();
    }
    
    private void initUI(){
        String dtrName = excelNameSetter.showInputDialog("DTR Name: ");
    }
    
}
