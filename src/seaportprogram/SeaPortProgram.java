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
        World newWorld = new World();
        Scanner input = new Scanner(inputFile);
        while(input.hasNext()){
            switch (input.next()){
                case "port":
                    System.out.println("This is a test port");
                    String name = input.next();
                    int index = parseInt(input.next());
                    int parent = parseInt(input.next());
                    
                    newWorld.ports.add(new SeaPort(name, index, parent));
                    
                    break;
                case "dock":
                    System.out.println("This is a test dock");
                    name = input.next();
                    index = parseInt(input.next());
                    parent = parseInt(input.next());
                    
                    Dock newDock = new Dock(name, index, parent);
                    System.out.println(newDock.toString(newDock));
                case "ship":
                    System.out.println("This is a test ship");
                    name = input.next();
                    index = parseInt(input.next());
                    parent = parseInt(input.next());
                    double weight = Double.parseDouble(input.next());
                    double length = Double.parseDouble(input.next());
                    double width = Double.parseDouble(input.next());
                    double draft = Double.parseDouble(input.next());
                    
                    Ship newShip = new Ship(name, index, parent, weight, length,
                            width, draft);
                    break;
                case "cship":
                    System.out.println("This is a test ship");
                    name = input.next();
                    index = parseInt(input.next());
                    parent = parseInt(input.next());
                    weight = Double.parseDouble(input.next());
                    length = Double.parseDouble(input.next());
                    width = Double.parseDouble(input.next());
                    draft = Double.parseDouble(input.next());
                    double cargoWeight = Double.parseDouble(input.next());
                    double cargoVolume = Double.parseDouble(input.next());
                    double cargoValue = Double.parseDouble(input.next());
                    
                    CargoShip newCShip = new CargoShip(name, index, parent, weight, length,
                            width, draft, cargoWeight, cargoVolume, cargoValue);
                    break;
                case "pship":
                    System.out.println("This is a test ship");
                    name = input.next();
                    index = parseInt(input.next());
                    parent = parseInt(input.next());
                    weight = Double.parseDouble(input.next());
                    length = Double.parseDouble(input.next());
                    width = Double.parseDouble(input.next());
                    draft = Double.parseDouble(input.next());
                    int passengers = parseInt(input.next());
                    int totRooms = parseInt(input.next());
                    int occupiedRooms = parseInt(input.next());
                    
                    PassengerShip newPShip = new PassengerShip(name, index, parent, weight, length,
                            width, draft, passengers, totRooms, occupiedRooms);
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
        runGUI();
    }
    
}
