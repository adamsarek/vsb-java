/*******************************************************************************
 * Kožusznik Jan
 * Copyright (c) 2014 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/

package pl1.lab08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Book {
  private final String data;

  /**
   *
   */
  public Book() {
    data = getText();
  }

  public Collection<String> getWords() {
	  String[] tokens = getText().split("[\\- \t\n.,;:()\\[\\]{}\"]+");
	  return new ArrayList<>(Arrays.asList(tokens));
  }
  
  @Override
  public String toString() {
    return data;
  }

  private String getText() {
    StringBuilder sb = new StringBuilder();
    try(BufferedReader br = new BufferedReader(new InputStreamReader(Book.class.getResourceAsStream("book.txt")))) {
    	String line;
    	while((line = br.readLine()) != null) {
    		sb.append(line).append('\n');
    	}
    } catch (IOException e) {
		e.printStackTrace();
		return "";
	}

    return sb.toString();
  }

}
