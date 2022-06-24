/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package cz.kozusznik.pl1.lab08;

import cz.kozusznik.pl1.shapes.manager.CanvasManager;
import cz.kozusznik.pl1.shapes.manager.Ellipse;
import cz.kozusznik.pl1.shapes.manager.ITransformable;
import cz.kozusznik.pl1.shapes.manager.GetTransformable;
import cz.kozusznik.pl1.shapes.manager.Rectangle;
import cz.kozusznik.pl1.shapes.manager.Triangle;
import cz.kozusznik.pl1.utils.IO;
import cz.kozusznik.pl1.utils.IO;
import cz.kozusznik.pl1.shapes.*;

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

    Ellipse elipsa = new Ellipse(10,10,10,10);
    CanvasManager.getInstance().add(elipsa);

    Position bod = new Position(50,200);

    Tr.transform(elipsa, bod, 3);
  }
}
