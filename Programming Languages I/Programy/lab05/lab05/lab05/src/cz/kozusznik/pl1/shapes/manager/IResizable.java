/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package cz.kozusznik.pl1.shapes.manager;

import cz.kozusznik.pl1.shapes.Dimension;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public interface IResizable {
  int getWitdh();

  int getHeight();

  void setDimension(int width, int height);

  default Dimension getDimension() {
    return new Dimension(getWitdh(), getHeight());
  }

}
