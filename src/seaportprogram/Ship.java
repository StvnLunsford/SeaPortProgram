/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author SLunsford
 */
public class Ship extends Thing {
    PortTime arrivalTime = new PortTime();
    PortTime dockTime = new PortTime();
    double draft, length, weight, width;
    ArrayList<Job> jobs;
    
    
    public Ship(Scanner sc){
    super(sc);
    if (sc.hasNextDouble()) weight = sc.nextDouble();
    if (sc.hasNextDouble()) length = sc.nextDouble();
    if (sc.hasNextDouble()) width = sc.nextDouble();
    if (sc.hasNextDouble()) draft = sc.nextDouble();
    }
    
    public void assignShip(Ship ship){
        
    }
    
    
}
