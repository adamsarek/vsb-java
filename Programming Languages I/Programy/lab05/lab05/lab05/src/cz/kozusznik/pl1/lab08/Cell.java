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
public class Cell<T> {

  private T val;

  /**
   * @return the val
   */
  public T getVal() {
    return val;
  }

  /**
   * @param val
   *          the val to set
   */
  public void setVal(T val) {
    this.val = val;
  }

}
