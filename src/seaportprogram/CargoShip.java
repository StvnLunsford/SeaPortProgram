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
public class CargoShip extends Ship {
    double cargoValue, cargoVolume, cargoWeight;
    
    public CargoShip(Scanner sc){
        super(sc);
        if(sc.hasNextDouble()) cargoWeight = sc.nextDouble();
        if(sc.hasNextDouble()) cargoVolume = sc.nextDouble();
        if(sc.hasNextDouble()) cargoValue = sc.nextDouble();
    }
    
    public String toString(){
        String st = "Cargo Ship: " + super.toString() + " " + cargoValue + " " + cargoVolume + " " + cargoWeight;
        return st;
    }
}
    
