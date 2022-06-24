/*******************************************************************************
 * Kožusznik Jan
 * Copyright (c) 2019 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/

package pl1.lab12;

import java.io.Serializable;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class MessageDTO implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 2427329186028578856L;
  private final int number;
  private final String message;

  /**
   * @param number
   * @param message
   */
  public MessageDTO(int number, String message) {
    super();
    this.number = number;
    this.message = message;
  }

  @Override
  public String toString() {
    return "Prepravka [number=" + number + ", message=" + message + "]";
  }

}
