/*******************************************************************************
 * Kožusznik Jan
 * Copyright (c) 2014 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/

package pl1.lab10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Book {

    // example of nested inner class
    public class NestedInnerComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if(o1.length() < o2.length()) {
                return -1;
            }
            if(o1.length() > o2.length()) {
                return 1;
            }
            return 0;
        }
    }

    // example of nested static class
    public static class NestedComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if(o1.length() < o2.length()) {
                return -1;
            }
            if(o1.length() > o2.length()) {
                return 1;
            }
            return 0;
        }
    }


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
      try(BufferedReader br = new BufferedReader(new InputStreamReader(Book.class.getResourceAsStream("/pl1/lab08/book.txt")))) {
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
