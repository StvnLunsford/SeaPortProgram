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
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author SLunsford
 */
public class SeaPortProgram{
    World world;
    String newText = null;
    HashMap<Integer,Thing> mainMap;
    JTextArea textArea = new JTextArea(newText,10,50);
    
    //creates the GUI and runs the file parser
    private void runGUI(){
        JFrame frame = new JFrame("Sea Port");
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        JFileChooser fc = new JFileChooser(".");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        JButton btnChooseFile = new JButton("Choose File");
        JButton btnSearchFile = new JButton("Search File");
        JTextField searchField = new JTextField(10);
        btnSearchFile.setEnabled(false);
        btnChooseFile.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnChooseFile) {
                if(fc.showOpenDialog(panel)==JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    try {
                        parseFile(file);
                        textArea.append(newText);
                        btnSearchFile.setEnabled(true);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SeaPortProgram.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }   
        });
        
        btnSearchFile.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnSearchFile) {
                String test = "name";
                searchFile(test, searchField.getText());
            }
        });
        
        panel.add(btnChooseFile);
        panel.add(btnSearchFile);
        panel.add(searchField);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    //parses the file that is selected using a switch block
    public void parseFile(File inputFile) throws FileNotFoundException{
        Scanner input = new Scanner(inputFile);
        world = new World(input);
        SeaPort newport;
        String line;
        HashMap<Integer, SeaPort> portMap = new HashMap<>();
        HashMap<Integer, Dock> dockMap = new HashMap<>();
        HashMap<Integer, Ship> shipMap = new HashMap<>();
        
        while (input.hasNextLine()){
            line = input.nextLine();
        if (line.isEmpty()) continue;
        Scanner lineScan = new Scanner(line);
            if(!lineScan.hasNext()) return;
            switch (lineScan.next()){
                case "port":
                    SeaPort port = new SeaPort(lineScan);
                    portMap.put(port.index, port);
                    world.ports.add(port);
                    break;
                case "dock":
                    Dock dock = new Dock(lineScan);
                    newport = world.getSeaPortByIndex(dock.parent, portMap);
                    dockMap.put(dock.index, dock);
                    world.assignDock(dock, newport);
                    break;
                case "ship":
                    Ship ship = new Ship(lineScan);
                    shipMap.put(ship.index, ship);
                    world.assignShip(ship, portMap);
                    break;
                case "cship":
                    CargoShip cship = new CargoShip(lineScan);
                    shipMap.put(cship.index, cship);
                    world.assignShip(cship, portMap);
                    break;
                case "pship":
                    PassengerShip pship = new PassengerShip(lineScan);
                    shipMap.put(pship.index, pship);
                    world.assignShip(pship, portMap);
                    break;
                case "person":
                    Person person = new Person(lineScan);
                    newport = world.getSeaPortByIndex(person.parent, portMap);
                    world.assignPerson(person, newport, portMap);
                    break;
                case "job":
                    
                    break;
                default:
                    break;
                    
            }
            
        }
        newText = world.toString();
    }
    
    public void searchFile(String thing, String object){
        ArrayList<Thing> searchResults = new ArrayList<>();
        switch (thing) {
            case "name":
                searchResults = world.searchName(object);
                break;
                
        }
        textArea.append(searchResults.toString());
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
