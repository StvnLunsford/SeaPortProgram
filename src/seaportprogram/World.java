/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author SLunsford
 */
public class World extends Thing {
    ArrayList<SeaPort> ports;
    PortTime time = new PortTime();
    SeaPort workingPort;
    
    
    public World(Scanner sc) {
        super(sc);
        ports = new ArrayList<>();
    }
    
    public void assignPort(SeaPort port){
        ports.add(port);
    }
    
    public void assignDock(Dock dock, SeaPort port){
        port.docks.add(dock);
    }
    
    public void assignShip(Ship ship, java.util.HashMap<Integer, SeaPort> portMap){
        Dock dock = getDockByIndex(ship.parent);
        if (dock != null){
            workingPort = getSeaPortByIndex(dock.parent, portMap);
            dock.setShip(ship);
        } else {
            workingPort = getSeaPortByIndex(ship.parent, portMap);
            workingPort.que.add(ship);
        } // end if docked/else
        workingPort.ships.add(ship);    
    }
    

    
    public void assignPerson(Person person, SeaPort port, java.util.HashMap<Integer, SeaPort> portMap){ 
        getSeaPortByIndex(person.parent, portMap).persons.add(person);
    }
    
    public void assignJob(Job job, java.util.HashMap<Integer, Ship> shipMap){
        getShipByIndex(job.parent, shipMap).jobs.add(job);
    }
    
    public Ship getShipByIndex(int x, java.util.HashMap<Integer, Ship> shipMap){
        return shipMap.get(x);         
    }
        
    public Dock getDockByIndex(int x){
        for (SeaPort port: ports)
            for (Dock dock: port.docks)
            if (dock.index == x)
                return dock;
        return null;
    }
    
    public SeaPort getSeaPortByIndex(int x, java.util.HashMap<Integer, SeaPort> portMap){
        return portMap.get(x);
    }
    
    public Person getPersonByIndex(int x, java.util.HashMap<Integer, Person> personMap){
        return personMap.get(x);
    }
    
    public ArrayList<Thing> searchName(String name){
        ArrayList<Thing> searchResults = new ArrayList<>();
        for (SeaPort port : ports) {
            if (port.name.equalsIgnoreCase(name)){
                searchResults.add(port);
            for (Ship ship : port.ships ) {
                if (ship.name.equalsIgnoreCase(name)){
                    searchResults.add(port);
                }
            }
            }
        }
        return searchResults;
    }
    
    public String toString () {
      String st = " ";
      for (SeaPort port: ports){
        st += "\n\nSeaPort: " + port.toString();
        for (Dock dock: port.docks) st += "\n" + dock;
            st += "\n\n --- List of all ships in que:";
        for (Ship ship: port.que ) { st += "\n   > " + ship;
            st += "\n\n --- List of all jobs on ship";
            for (Job job: ship.jobs) st += "\n > " + job;
            st += "\n\n";
        }
            st += "\n\n --- List of all ships:";
        for (Ship ship: port.ships) { st += "\n   > " + ship;
            st += "\n\n --- List of all jobs on ship";
            for (Job job: ship.jobs) st += "\n > " + job;
            st += "\n\n";
        }
            st += "\n\n --- List of all persons:";
        for (Person person: port.persons) st += "\n   > " + person;
            
        
      }
      return st;
    }
}
