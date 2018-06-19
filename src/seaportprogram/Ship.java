/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.util.ArrayList;

/**
 *
 * @author SLunsford
 */
public class Ship extends Thing {
    PortTime arrivalTime = new PortTime();
    PortTime dockTime = new PortTime();
    double draft, lenght, weight, width;
    ArrayList<Job> jobs;
}
