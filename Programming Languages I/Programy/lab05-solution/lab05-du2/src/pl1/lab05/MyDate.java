/*www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package pl1.lab05;


@SuppressWarnings("unused")

public final class MyDate {

  private final int year;

  private final int month;
  private final int day;

  
  public MyDate(int year, int month, int day) {
    super();
    this.year = year;
    this.month = month;
    this.day = day;
  }
  
  @Override
  public boolean equals(Object obj) {
	  if (obj instanceof MyDate) {
		  MyDate md = (MyDate) obj;
		  return md.year == year 
				  && md.month == month 
				  && md.day == day;
	  }
	  return false;
  }

}
