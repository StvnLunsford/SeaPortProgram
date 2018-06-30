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
    
    
    
    public void assignPort(SeaPort port){
        ports.add(port);
    }
    
    public void assignDock(Dock dock, SeaPort port){
        port.getDocks().add(dock);
    }
    
    public void assignShip(Ship ship){
        Dock dock = getDockByIndex(ship.parent);
        if (dock == null){
            getSeaPortByIndex(dock.parent).ships.add(ship);
            getSeaPortByIndex(dock.parent).que.add(ship);
            return;
        }
        dock.ship = ship;
        getSeaPortByIndex(dock.parent).ships.add(ship);
    }
    
    public void assignPerson(Person person, SeaPort port){ 
        getSeaPortByIndex(person.parent).persons.add(person);
    }
    
    public Ship getShipByIndex(int x){
        for (SeaPort port: ports)
            for (Ship ship: port.ships)
                if (ship.index == x)
                    return ship;
        return null;         
    }
        
    public Dock getDockByIndex(int x){
        for (SeaPort port: ports)
            for (Dock dock: port.docks)
            if (dock.index == x)
                return dock;
        return null;
    }
    
    public SeaPort getSeaPortByIndex(int x){
        for (SeaPort port: ports)
            if (port.index == x)
                return port;
        return null;
    }
}
