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
public class PassengerShip extends Ship {
    int numberOfOccupiedRooms, numberOfPassengers, numberOfRooms;
    
    public PassengerShip(Scanner sc) {
        super (sc);
        if (sc.hasNextInt()) numberOfPassengers = sc.nextInt();
        if (sc.hasNextInt()) numberOfRooms = sc.nextInt();
        if (sc.hasNextInt()) numberOfOccupiedRooms = sc.nextInt();
    }

public String toString(){
        String st = "Ship: " + super.toString() + " " + numberOfOccupiedRooms + " " + numberOfPassengers + " " + numberOfRooms;
        return st;
    }
}


