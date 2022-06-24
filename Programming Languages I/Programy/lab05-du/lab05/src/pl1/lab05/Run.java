/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package pl1.lab05;

import pl1.shapes.manager.CanvasManager;
import pl1.shapes.manager.Ellipse;
import pl1.shapes.manager.Transformable;
import pl1.shapes.manager.Rectangle;
import pl1.shapes.manager.Triangle;
import pl1.utils.IO;
import pl1.shapes.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Run {

  public static void main(String[] args) {
    CanvasManager.getInstance();

    Transformer Tr = new Transformer();

    Ellipse elipsa1 = new Ellipse(10,10,10,10);
    CanvasManager.getInstance().add(elipsa1);
    Position bod1 = new Position(50,200);
    Tr.transform(elipsa1, bod1, 3);

    Ellipse elipsa2 = new Ellipse(100,100,50,50);
    CanvasManager.getInstance().add(elipsa2);
    Position bod2 = new Position(0,0);
    Tr.transform(elipsa2, bod2, 0.5);
  }
}
