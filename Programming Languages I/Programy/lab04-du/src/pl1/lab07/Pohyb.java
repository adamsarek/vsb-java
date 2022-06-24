/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl1.lab07;

import pl1.shapes.manager.IPaintable;
import pl1.shapes.manager.MyGraphics;
import pl1.utils.IO;

/**
 *
 * @author Jaroslav
 */
public class Pohyb {
    public void Gogo(IGetValuable Obj,int newx,int newy,int krok, int time){
        
        while (krok > 0){
            int oldX = Obj.getX();
            int oldY = Obj.getY();
            Obj.setPosition(oldX + newx, oldY + newy);
            IO.wait(time);
            krok--;
        }
    
    }
    
}
