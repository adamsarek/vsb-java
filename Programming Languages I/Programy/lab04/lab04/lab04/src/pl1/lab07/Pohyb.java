/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl1.lab07;

import pl1.utils.IO;


public class Pohyb {
    public void move(GetValuable Obj, int newX, int newY, int krok, int time){

        double step = (double) krok;
        double posX = (double) Obj.getX();
        double posY = (double) Obj.getY();
        double diffX = newX - Obj.getX();
        double diffY = newY - Obj.getY();

        while (krok > 0){

            posX += (diffX / step);
            posY += (diffY / step);

            Obj.setPosition((int)posX, (int)posY);
            IO.wait(time);
            krok--;
        }
    }
}
