package pl1.lab05;

import pl1.shapes.tools.*;
import pl1.utils.IO;
import pl1.shapes.*;
import pl1.shapes.manager.Transformable;

public class Transformer {
    public static void transform(Transformable obj, Position newP, double zvetseni) {
        int nowX = obj.getX(); // proměnná obsahuje původní x-ovou pozici
        int nowY = obj.getY(); // proměnná obsahuje původní y-ovou pozici

        // výpočet vzdálenosti pomocí pythagorovy věty
        float distance = (float) Math.sqrt((nowX - newP.x) * (nowX - newP.x) + (nowY - newP.y) * (nowY - newP.y));

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