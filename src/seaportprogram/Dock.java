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
public class Dock extends Thing {
    Ship ship;
    
    public Dock(Scanner sc){
        super(sc);
    }
    
    public String toString(){
        String st = "Dock: " + super.toString();
        return st;
    }

    public void setShip(Ship newShip){
        ship = newShip;
    
    }
}