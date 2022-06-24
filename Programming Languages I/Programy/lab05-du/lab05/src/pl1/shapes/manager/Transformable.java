/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package pl1.shapes.manager;

import pl1.shapes.tools.Movable;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public interface Transformable extends Movable, Resizable {
    void setPosition(int x, int y);
    int getX();
    int getY();
}
