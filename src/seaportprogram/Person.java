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
public class Person extends Thing {
    String skill;
    
    
    public Person(Scanner sc){
        super(sc);
        if (sc.hasNext()) skill = sc.next();
    }
    
    public String toString(){
        String st = "Person: " + super.toString();
        return st;
    }
}
