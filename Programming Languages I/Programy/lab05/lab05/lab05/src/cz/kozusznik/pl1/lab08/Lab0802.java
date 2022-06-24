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
public class Lab0802 {

  public void go() {
    Object d1 = new MyDate(2013, 10, 31);
    Object d2 = new MyDate(2013, 10, 31);
    Object d3 = new MyDate(2013, 10, 30);

    if (d1 == d2) {
      System.out.println("Stejne objekty");
    }

    if (d1.equals(d2)) {
      System.out.println("Stejne hodnoty objektu d1 a d2");
    }

    if (d1.equals(d3)) {
      System.out.println("Stejne hodnoty objektu d1 a d3");
    }
  }

}
