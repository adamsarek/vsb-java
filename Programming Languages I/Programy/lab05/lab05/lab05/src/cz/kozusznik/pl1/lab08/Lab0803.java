/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package cz.kozusznik.pl1.lab08;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Lab0803 {

  public static void go() {
    Cell<Integer> cell = new Cell<Integer>();
    cell.setVal(10);

    //.......

    //cell.setVal("10");
    //......
    int i = cell.getVal();
    System.out.println("i = " + i);
  }

}
