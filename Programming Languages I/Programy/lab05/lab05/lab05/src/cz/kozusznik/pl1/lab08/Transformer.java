/*


package cz.kozusznik.pl1.lab08;

import cz.kozusznik.pl1.shapes.manager.IPaintable;
import cz.kozusznik.pl1.shapes.manager.ITransformable;
import cz.kozusznik.pl1.utils.IO;


public class Transformer {
    
    public void transform(ITransformable i,int x,int y,double zvetseni){
        System.out.println(49649);

       // i.setPosition(100, 100);

        int krok = 30;
        int oX = i.getX();
        int oY = i.getY();
        int dY = oY;
        int dX = oX;
        int diffX = x - oX;
        int diffY = y - oY;
        int width, height;

        while (krok > 0){
            int oldX = i.getX();
           // System.out.println(oldX);
            int oldY = i.getY();

             width = (int)(i.getWitdh() + zvetseni);
             height = (int)(i.getHeight() + zvetseni);
            dX += diffX / 30;
            System.out.println(dX);
            dY += diffY / 30;
            System.out.println(dY);
            i.setDimension(width, height);
            i.setPosition(dX, dY);
            IO.wait(10);
            krok--;
        }
     // I_like_to_move_it(i,x,y,200); IO.wait(200);
    //  int width = (int)(i.getWitdh() + zvetseni);
     // int heigth = (int)(i.getHeight() + zvetseni);
    //  i.setDimension(width, heigth);
    }
    // Animace pohybu // 
    public void I_like_to_move_it(ITransformable Obj,int newx,int newy,int krok){        
        while (krok > 0){
            int oldX = Obj.getX();
            int oldY = Obj.getY();
            Obj.setPosition(oldX + newx, oldY + newy);
            
            IO.wait(200);
            krok--;
        }
    
    }
    
    
}
*/


package cz.kozusznik.pl1.lab08;

import cz.kozusznik.pl1.shapes.tools.*;
import cz.kozusznik.pl1.utils.IO;
import cz.kozusznik.pl1.shapes.*;
import cz.kozusznik.pl1.shapes.manager.ITransformable;

public class Transformer {
    public static void transform(ITransformable obj, Position newP, double zvetseni) {
        int nowX = obj.getX(); // proměnná obsahuje původní x-ovou pozici
        int nowY = obj.getY(); // proměnná obsahuje původní y-ovou pozici

        float distance = (float) Math.sqrt((nowX - newP.x) * (nowX - newP.x) + (nowY - newP.y) * (nowY - newP.y));
    // výpočet vzdálenosti pomocí pythagorovy věty

        int steps = (int) (distance); // proměnná pro nastavení počtu iterací cyklu
        double oldS = obj.getHeight(); // proměnná, která obsahuje původní velikost objektu
        double newS = oldS * zvetseni; // proměnná, která obsahuje novou velikost objektu
        int lenX = newP.x - obj.getX(); // výpočet vzdálenosti na x-ové ose
        int lenY = newP.y - obj.getY(); // výpočet vzdálenosti na y-ové ose
        float stepX = lenX / (float) steps; // výpočet kroku posunu
        float stepY = lenY / (float) steps; // výpočet kroku posunu

        for (int step = 0; step < steps; step++) {
            // objekt se bude postupně zvětšovat v každém kroku
            obj.setDimension((int) (oldS + oldS * (zvetseni-1.0) * step / steps), (int) (oldS + oldS * (zvetseni-1.0) * step / steps));
            obj.setPosition(nowX + (int) (stepX * step), nowY + (int) (stepY * step)); // objekt se postupně bude posouvat podle počtu kroků
            IO.wait(50);
        }
        obj.setDimension((int) newS, (int) newS);
        obj.setPosition(newP.x, newP.y);
    }
}