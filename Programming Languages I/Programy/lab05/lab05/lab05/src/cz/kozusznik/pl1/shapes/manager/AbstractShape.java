/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package cz.kozusznik.pl1.shapes.manager;

import cz.kozusznik.pl1.shapes.Position;
import cz.kozusznik.pl1.shapes.tools.IMovable;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public abstract class AbstractShape implements IPaintable, IMovable {

  private int xPos;

  private int yPos;

  public AbstractShape(int x, int y) {
    xPos = x;
    yPos = y;
  }

  @Override
  public int getX() {
    return xPos;
  }

  @Override
  public int getY() {
    return yPos;
  }

  @Override
  public void setPosition(int x, int y) {
    xPos = x;
    yPos = y;
    CanvasManager.getInstance().repaint();
  }

  @Override
  public Position getPosition() {
    return new Position(xPos, yPos);
  }

  @Override
  abstract public void paint(MyGraphics g);
}
