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
    
    public String toString(Dock dock){
        StringBuilder sb = new StringBuilder();
        sb.append(name + " ");
        sb.append(index + " ");
        sb.append(parent);
        String result = sb.toString();
        return result;
    }
}
