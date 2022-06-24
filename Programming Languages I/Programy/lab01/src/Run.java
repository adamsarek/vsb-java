/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Run {

  public static void main(String[] args) {
    new Rectangle(65, 40, 20, 40, MyColor.GRAY);
    new Triangle(25, 25, 200, 75, MyColor.RED);
    new Rectangle(25, 100, 200, 125, MyColor.LIGHT_GRAY);
    new Rectangle(100, 150, 50, 75, MyColor.BROWN);
    new Rectangle(135, 185, 10, 5, MyColor.YELLOW);
  }

}
