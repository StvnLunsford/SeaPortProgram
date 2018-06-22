/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

/**
 *
 * @author SLunsford
 */
public class PassengerShip extends Ship {
    int numberOfOccupiedRooms, numberOfPassengers, numberOfRooms;
    
    public PassengerShip(String shipName, int shipIndex, int shipParent, 
            double shipWeight, double shipLength, double shipWidth, 
            double shipDraft, int shipPassengers, int shipRooms, 
            int shipOccupied){
        name = shipName;
        index = shipIndex;
        parent = shipParent;
        weight = shipWeight;
        length = shipLength;
        width = shipWidth;
        draft = shipDraft;
        numberOfPassengers = shipPassengers;
        numberOfRooms = shipRooms;
        numberOfOccupiedRooms = shipOccupied;
    }
}
