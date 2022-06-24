/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package pl1.lab07;

import java.util.ArrayList;
import pl1.shapes.MyColor;
import pl1.shapes.manager.CanvasManager;
import pl1.shapes.manager.Ellipse;
import pl1.shapes.manager.IPaintable;
import pl1.shapes.manager.MyGraphics;
import pl1.shapes.manager.Rectangle;
import pl1.shapes.manager.Triangle;


/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Main01 {
  IPaintable p; MyGraphics M;

  public static void main(String[] args) {
    CanvasManager.getInstance();
    /*Cl1 c1 = new Cl1();
    Cl2 c2 = new Cl2();
    ArrayList<Imyinterface> list = new ArrayList<Imyinterface>();
    list.add(c1);
    list.add(c2);
    
    for (Imyinterface i:list){
        System.out.println(i.getvalue(10));
        
    }
    
    System.out.println(c1);
    System.out.println(c2);
    
    ArrayList<IValuable> list = new ArrayList<IValuable>();
    Ellipse el = new Ellipse();
    Rectangle rec = new Rectangle();
    list.add(el);
    list.add(rec);
    
    AverageComputer Ac = new AverageComputer();
    Ac.getAverage(list);
    */
    ArrayList<IGetValuable> list = new ArrayList<IGetValuable>();
    Ellipse el = new Ellipse(0,0,10,10); 
    Rectangle rect = new Rectangle(1,50,10,10);
    list.add(el);
    list.add(rect);
    
    CanvasManager.getInstance().add(el);    
    CanvasManager.getInstance().add(rect);
    
    Pohyb pohyb = new Pohyb();
    for (IGetValuable i:list){pohyb.Gogo(i, 10, 10, 10, 500);}
    
  }
}
/*
  interface Imyinterface{
      int getvalue(int i);
  }
  
  class Cl1 implements Imyinterface{
      public void A(){}; 
      @Override
      public int getvalue(int i){return i*i;}
  }
  class Cl2 implements Imyinterface{
      public void B(){};
      @Override
      public int getvalue(int i){return i+i;}
  }
  
*/
