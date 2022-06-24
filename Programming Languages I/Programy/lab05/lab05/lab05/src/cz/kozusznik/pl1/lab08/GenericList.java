/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.kozusznik.pl1.lab08;

import java.util.ArrayList;

/**
 *
 * @author Jaroslav
 */
class GenericList<T>
{
    T ob; // объявление объекта типа T
    ArrayList<T> a;
    
    GenericList(T o) {
        this.a  = new ArrayList();
    }
    
    void push(T inp){
        this.a.add(inp);
    }
    
    void pop(T inp){
        this.a.remove(inp);
    }
    int size(T inp){
       return this.a.size();
    }
    
}