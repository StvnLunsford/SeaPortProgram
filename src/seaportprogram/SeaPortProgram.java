/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author SLunsford
 */
public class SeaPortProgram extends JFrame{
    
    private static void runGUI(){
        JFrame frame = new JFrame("Sea Port");
        frame.setSize(400,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        JFileChooser fc = new JFileChooser(".");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        JButton btnChooseFile = new JButton("Choose File");
        btnChooseFile.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnChooseFile) {
                int returnVal = fc.showOpenDialog(panel);
            }   
    });
        panel.add(btnChooseFile);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        runGUI();
    }
    
}
