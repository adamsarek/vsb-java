/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl1.lab07;

import java.util.ArrayList;

/**
 *
 * @author kon0318
 */
public class AverageComputer{
    
    public void getAverage(ArrayList<IValuable> list){
        for (IValuable i:list){
            System.out.println(i);
            System.out.println(i.getValue());
        }
    }
    
}
