/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
                if(fc.showOpenDialog(panel)==JFileChooser.APPROVE_OPTION){
                    int returnVal = fc.showOpenDialog(panel);
                    File file = fc.getSelectedFile();
                    try {
                        parseFile(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SeaPortProgram.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }   
    });
        panel.add(btnChooseFile);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    public static void parseFile(File inputFile) throws FileNotFoundException{
        Scanner input = new Scanner(inputFile);
        while(input.hasNext()){
            switch (input.next()){
                case "port":
                    System.out.println("This is a test port");
                    String name = input.next();
                    int index = parseInt(input.next());
                    int parent = parseInt(input.next());
                    
                    SeaPort newPort = new SeaPort(name, index, parent);
                    
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        runGUI();
    }
    
}