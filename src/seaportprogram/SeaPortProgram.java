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
import java.util.ArrayList;
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
    World world;
    
    
    private void runGUI(){
        
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
    
    public void parseFile(File inputFile) throws FileNotFoundException{
        World world = new World();
        world.ports = new ArrayList<>();
        Scanner input = new Scanner(inputFile);
        while (input.hasNextLine()){
            if(! input.hasNext()) continue;
            switch (input.next()){
                case "port":
                    System.out.println("This is a test port");
                    SeaPort port = new SeaPort(input);
                    world.ports.add(port);
                    break;
                case "dock":
                    System.out.println("This is a test dock");
                    Dock dock = new Dock(input);
                    world.assignDock(dock, world.getSeaPortByIndex(dock.parent));
                    break;
                case "ship":
                    System.out.println("This is a test dock");
                    Ship ship = new Ship(input);
                    world.assignShip(ship);
                    break;
                case "cship":
                    CargoShip cship = new CargoShip(input);
                    world.assignShip(cship);
                    break;
                case "pship":
                    PassengerShip pship = new PassengerShip(input);
                    world.assignShip(pship);
                    break;
                case "person":
                    
                case "job":
                    
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SeaPortProgram newProgram = new SeaPortProgram();
        newProgram.runGUI();
    }
    
}
