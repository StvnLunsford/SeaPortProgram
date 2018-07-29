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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author SLunsford
 */
public class SeaPortProgram{
    World world;
    String newText = null;
    HashMap<Integer,Thing> mainMap;
    JTextArea textArea = new JTextArea(newText,10,50);
    JPanel treePanel = new JPanel();
    JPanel jobsPanel = new JPanel();
    JScrollPane treePane;
    JTree portTree;
    JFrame frame;
    String[] shipSortBox = {"Weight", "Length", "Width", "Draft"};
    
    //creates the GUI and runs the file parser
    private void runGUI(){
        frame = new JFrame("Sea Port");
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.setSize(1000,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        
        
        JFileChooser fc = new JFileChooser(".");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        JButton btnChooseFile = new JButton("Choose File");
        JButton btnSearchFile = new JButton("Search File");
        JButton btnSortName = new JButton("Sort by Name");
        JButton btnSortShips = new JButton("Sort Ships by:");
        JComboBox sortSelection = new JComboBox(shipSortBox);
        JTextField searchField = new JTextField(10);
        JButton btnRunJobs = new JButton("Run Jobs");
        btnSearchFile.setEnabled(false);
        btnSortName.setEnabled(false);
        btnSortShips.setEnabled(false);
        btnRunJobs.setEnabled(false);
        btnChooseFile.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnChooseFile) {
                if(fc.showOpenDialog(panel)==JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    try {
                        parseFile(file);
                        textArea.append(newText);
                        drawTree();
                        btnSearchFile.setEnabled(true);
                        btnSortName.setEnabled(true);
                        btnSortShips.setEnabled(true);
                        btnRunJobs.setEnabled(true);
                        
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SeaPortProgram.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }  
            frame.repaint();
        });
        
        btnSearchFile.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnSearchFile) {
                String test = "name";
                searchFile(test, searchField.getText());
            }
        });
        
        btnSortName.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnSortName) {
                sortByName(world.ports);
                for (SeaPort port : world.ports) {
                    sortByName(port.docks);
                    sortByName(port.persons);
                    sortByName(port.ships);
            }
                newText = world.toString();
                textArea.append(newText);
            }
        });
        
        btnSortShips.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnSortShips) {
                String selection = (String) sortSelection.getSelectedItem();
                sortShips(selection);
            }
            newText = world.toString();
            textArea.append(newText);
        });
        
        btnRunJobs.addActionListener((ActionEvent e) -> {
            if (e.getSource() == btnRunJobs) {
                for (SeaPort port : world.ports)
                    for (Dock dock : port.docks)
                        for (Job job : dock.ship.jobs) 
                            job.run();
            }
        });
        
        panel.add(btnChooseFile);
        panel.add(btnSearchFile);
        panel.add(searchField);
        panel.add(btnSortName);
        panel.add(btnSortShips);
        panel.add(sortSelection);
        panel.add(btnRunJobs);
        //panel.add(scrollPane, BorderLayout.CENTER);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, jobsPanel);
        JSplitPane centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane, treePanel);
        splitPane.setDividerLocation(300);
        centerSplit.setDividerLocation(800);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(centerSplit,BorderLayout.CENTER);
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
        HashMap<Integer, Person> personMap = new HashMap<>();
        
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
                    personMap.put(person.index, person);
                    world.assignPerson(person, newport, portMap);
                    break;
                case "job":
                    Job job = new Job(jobsPanel, lineScan);
                    world.assignJob(job, shipMap);
                    break;
                default:
                    break;
                    
            }
        lineScan.close(); 
        }

        
        newText = world.toString();
        
        sortByName(world.ports);

    }
    
    
    //method to search the structure based on user-defined criteria
    public void searchFile(String thing, String object){
        ArrayList<Thing> searchResults = new ArrayList<>();
        switch (thing) {
            case "name":
                searchResults = world.searchName(object);
                break;               
        }
        textArea.append(searchResults.toString());
    }
    
    //method to sort all items by name
    private void sortByName(ArrayList<? extends Thing> targetList){
        //It was unnecessary to set a new variable using targetList, thanks for the catch!
        //ArrayList<Thing> sortList = targetList;
        //Collections.sort(sortList, new NameComparator());
        Collections.sort(targetList, new NameComparator());
    }
    
    private void sortShips(String selection){
        for (SeaPort port : world.ports) {
            ArrayList<Ship> sortList = port.que;
            Collections.sort(sortList, new ShipComparator(selection));
        }
    }
    
    private void drawTree(){
        portTree = new JTree(createNode());
        treePane = new JScrollPane(portTree);
        treePanel.add(treePane);
        
    }
    
    
    private DefaultMutableTreeNode createNode(){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Sea Ports");
        DefaultMutableTreeNode portNode;
        for (SeaPort port : world.ports){
            portNode = new DefaultMutableTreeNode(port.name);
            top.add(portNode);
            portNode.add(addDockNode(port.docks, "Docks"));
            portNode.add(addShipNode(port.ships, "Ships"));
            portNode.add(addQueNode(port.que, "Queue"));
            portNode.add(addPersonNode(port.persons, "Person"));
        }
        return top;
    }
    
    private DefaultMutableTreeNode addDockNode(ArrayList<Dock> docks, String name){
        DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(name);
            for (Dock dock : docks)
                subNode.add(new DefaultMutableTreeNode(dock.name));
            return subNode;
    }
    
    private DefaultMutableTreeNode addShipNode(ArrayList<Ship> ships, String name){
        DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(name);
            for (Ship ship : ships)
                subNode.add(new DefaultMutableTreeNode(ship.name));
            return subNode;
    }
    
    private DefaultMutableTreeNode addQueNode(ArrayList<Ship> que, String name){
        DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(name);
            for (Ship ship : que)
                subNode.add(new DefaultMutableTreeNode(ship.name));
            return subNode;
    }
    
    private DefaultMutableTreeNode addPersonNode(ArrayList<Person> persons, String name){
        DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(name);
            for (Person person : persons)
                subNode.add(new DefaultMutableTreeNode(person.name));
            return subNode;
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
