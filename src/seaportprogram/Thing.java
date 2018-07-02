/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.util.Scanner;

/**
 *
 * @author SLunsford
 */
public class Thing implements Comparable<Thing>{
    int index;
    String name;
    int parent;
    
    public Thing(){
        
    }
    
public Thing(Scanner sc){
    if (sc.hasNext()) name = sc.next();
    if (sc.hasNextInt()) index = sc.nextInt();
    if (sc.hasNextInt()) parent = sc.nextInt();
    
}
    @Override
    public int compareTo(Thing o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString(){
        String st = name + " " + index;
        return st;
    }
    
}
