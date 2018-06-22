/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.util.ArrayList;

/**
 *
 * @author SLunsford
 */
public class SeaPort extends Thing {
    String name;
    int index, parent;
    ArrayList<Dock> docks;
    ArrayList<Ship> que;
    ArrayList<Ship> ships;
    ArrayList<Person> persons;
    
    public SeaPort(String newName, int newIndex, int newParent){
        name = newName;
        index = newIndex;
        parent = newParent;    
    }
    
    public String toString(SeaPort port){
        StringBuilder sb = new StringBuilder();
        sb.append(name + " ");
        sb.append(index + " ");
        sb.append(parent);
        String result = sb.toString();
        return result;
    }
    
}
