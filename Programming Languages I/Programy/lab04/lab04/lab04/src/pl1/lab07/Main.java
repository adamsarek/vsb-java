/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package pl1.lab07;

import java.util.ArrayList;

import pl1.shapes.manager.CanvasManager;
import pl1.shapes.manager.Ellipse;
import pl1.shapes.manager.Rectangle;


/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Main {

  public static void main(String[] args) {
    CanvasManager.getInstance();

    Ellipse elipsa = new Ellipse(0,0,10,10);
    Rectangle obdelnik = new Rectangle(50,200,10,10);
    CanvasManager.getInstance().add(elipsa);
    CanvasManager.getInstance().add(obdelnik);

    Pohyb pohyb = new Pohyb();
    pohyb.move(elipsa, 50, 190, 200, 20);
    pohyb.move(obdelnik, 200, 50, 200, 20);
  }
}

