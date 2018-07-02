/*
 * @Author Steven Lusnford
 * This program reads a file from the local PC and uses it to populate
 * a text area with a Sea Port structure.
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author SLunsford
 */
public class SeaPortProgram extends JFrame{
    World world;
    String newText = null;
    
    //creates the GUI and runs the file parser
    private void runGUI(){
        JFrame frame = new JFrame("Sea Port");
        JTextArea textArea = new JTextArea(newText,10,50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.setSize(600,300);
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
                        textArea.append(newText);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SeaPortProgram.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }   
    });
        panel.add(btnChooseFile);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    //parses the file that is selected using a switch block
    public void parseFile(File inputFile) throws FileNotFoundException{
        Scanner input = new Scanner(inputFile);
        world = new World(input);
        SeaPort newport;
        while (input.hasNextLine()){
            if(! input.hasNext()) continue;
            switch (input.next()){
                case "port":
                    SeaPort port = new SeaPort(input);
                    world.ports.add(port);
                    break;
                case "dock":
                    Dock dock = new Dock(input);
                    newport = world.getSeaPortByIndex(dock.parent);
                    world.assignDock(dock, newport);
                    break;
                case "ship":
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
                    Person person = new Person(input);
                    newport = world.getSeaPortByIndex(person.parent);
                    world.assignPerson(person, newport);
                    break;
                case "job":
                    
                    break;
                    
            }
            
        }
        newText = world.toString();
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
