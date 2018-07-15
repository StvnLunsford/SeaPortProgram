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
public class ShipComparator implements Comparator<Ship> {
    String value = null;
    
    ShipComparator(String selection) {
        value = selection;
    }


    @Override
    public int compare(Ship ship1, Ship ship2) {
        double result = 0;
        if (value == "Width"){
            result = (ship1.width - ship2.width);
            return (int) result;
        }
            
        if(value =="Length"){
            result =  (ship1.length - ship2.length);
            return (int) result;
        }
        if (value == "Draft"){
            result = (ship1.draft - ship2.draft);
            return (int) result;
        }
        if(value =="Weight"){
            result = (ship1.weight - ship2.weight);
            return (int) result;
        }
        return (int) result;
    }
    
    @Override
    public Comparator<Ship> reversed() {
        return Comparator.super.reversed(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator<Ship> thenComparing(Comparator<? super Ship> other) {
        return Comparator.super.thenComparing(other); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <U> Comparator<Ship> thenComparing(Function<? super Ship, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Ship> thenComparing(Function<? super Ship, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator<Ship> thenComparingInt(ToIntFunction<? super Ship> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator<Ship> thenComparingLong(ToLongFunction<? super Ship> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comparator<Ship> thenComparingDouble(ToDoubleFunction<? super Ship> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor); //To change body of generated methods, choose Tools | Templates.
    }
    
}
