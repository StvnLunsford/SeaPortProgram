/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 *
 * @author SLunsford
 */
public class NameComparator implements Comparator<Thing>{


    public int compare(Thing ship1, Thing ship2) {
        
        return ship1.name.compareTo(ship2.name);
        
    }

    @Override
    public Comparator reversed() {
        return Comparator.super.reversed(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator thenComparing(Comparator other) {
        return Comparator.super.thenComparing(other); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator thenComparing(Function keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator thenComparingInt(ToIntFunction keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator thenComparingLong(ToLongFunction keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor); //To change body of generated methods, choose Tools | Templates.
    }
    
}
