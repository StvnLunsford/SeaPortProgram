/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author SLunsford
 */
public class World extends Thing {
    ArrayList<SeaPort> ports;
    PortTime time = new PortTime();
    
    public static void parseFile(File inputFile) throws FileNotFoundException{
        World newWorld = new World();
        String line;
        Scanner input = new Scanner(inputFile);
        while(input.hasNext()){
            switch (input.next()){
                case "port":
                    line = input.nextLine();
                    System.out.println(line);
                    Scanner lineInput = new Scanner(line);
                    System.out.println("This is a test port");
                    newWorld.ports.add(new SeaPort(lineInput));
                    break;
                case "dock":
                    System.out.println("This is a test dock");
                    line = input.nextLine();
                Dock dock = new Dock(input);
                    break;
                case "ship":
                    line = input.nextLine();
                    new Ship(input);
                case "cship":
   
                case "pship":

                case "person":
                    
                case "job":
                    
            }
        }
    }
}
