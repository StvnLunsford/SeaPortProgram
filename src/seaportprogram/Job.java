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
public class Job extends Thing {
    double duration;
    ArrayList<String> requirements;
    
    
    public Job(Scanner sc){
        super(sc);
        if (sc.hasNextDouble()) duration = sc.nextDouble();
    }
    
    public String toString(){
        String st = "Job: " + super.toString();
        return st;
    }
}
