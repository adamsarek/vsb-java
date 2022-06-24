/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package pl1.shapes.manager;

import pl1.shapes.Dimension;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public interface Resizable {
  int getWidth();

  int getHeight();

  void setDimension(int width, int height);

  default Dimension getDimension() {
    return new Dimension(getWidth(), getHeight());
  }

}
