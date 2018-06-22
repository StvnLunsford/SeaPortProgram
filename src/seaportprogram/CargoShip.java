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
public class CargoShip extends Ship {
    double cargoValue, cargoVolume, cargoWeight;
    
    public CargoShip(String shipName, int shipIndex, int shipParent, 
            double shipWeight, double shipLength, double shipWidth, 
            double shipDraft, double shipCWeight, double shipCVolume, 
            double shipCValue){
        name = shipName;
        index = shipIndex;
        parent = shipParent;
        weight = shipWeight;
        length = shipLength;
        width = shipWidth;
        draft = shipDraft;
        cargoValue = shipCValue;
        cargoVolume = shipCVolume;
        cargoWeight = shipCWeight;
    }
    
}
    
