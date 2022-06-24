/*******************************************************************************
 * Kozusznik Jan
 * Copyright (c) 2014 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class Pacman {

  private static final int SIZE = 30;

  private static final double SIZE_OF_EYE_PORTION = 0.15;

  private static final int ANGLE = 40;

  private int xPos;

  private int yPos;

  private Direction8 direction;

  //TODO 1.head and eye declaration - instanční proměnné
  //CZ:    deklarace hlavy a oka - instanční proměnné
  private final Arc head;
  private final Ellipse eye;

  public Pacman(int x, int y, Direction8 direction) {
    this.xPos = x;
    this.yPos = y;
    this.direction = direction;
  //TODO 2. add head and eye creation 
    //head is realized
    // -position - x,y
    // -dimension - SIZE,SIZE
    // -color -yellow (MyColor.YELLOW)
    // -angle - call method computeAngle
    // -orientation - oposite direction to direction of head (method reverseDirection on variable direction)
    //eye is realized by ellipse
    // -position x,y - getEyeX(), getEyeY() - a call of member method
    // -dimension - getEyeSize(), getEyeSize() - a call of member method
    // -color - black
    //----------------------------------------
    //CZ:       doplnit vytvoreni hlavy a oka
    //hlavu realizuje Arc
    // -pozice - x,y
    // -rozmer - SIZE,SIZE;
    // -barva - zluta (MyColor.getColor("zluta"))
    // -úhel - volání metody computeAngle
    // -orientace - opačná ke směru (metoda celemVzad volaná na směr - proměnná direction)
    //oko realizue Ellipse
    // -pozice x,y - getEyeX(), getEyeY() - volání členských metod
    // -rozmer - getEyeSize(), getEyeSize() - volání členských metod
    // -barva - cerna
    this.head = new Arc(x, y, SIZE, SIZE, MyColor.getColor("zluta"),direction.celemVzad(),computeAngle());
    this.eye = new Ellipse(getEyeX(), getEyeY(), getEyeSize(), getEyeSize(), MyColor.getColor("cerna"));
    paint();
  }

  public void setDirection(Direction8 direction8) {
    erase();
    this.direction = direction8;
    //TODO 6. Set orientiation of head and eye position
    //---------------------------------------------
    //CZ:     nastavení orientace hlavy a pozice oka
    this.head.setDirection(this.direction.celemVzad());
    this.eye.setPosition(getEyeX(), getEyeY());
    paint();
  }

  public void setPosition(int x, int y) {
    erase();
    this.xPos = x;
    this.yPos = y;
    //TODO 5. call the methon setPosition to head and eye  
    //------------------------------------------
    //        zavolat setPosition na hlavu a oko
    this.head.setPosition(x, y);
    this.eye.setPosition(getEyeX(), getEyeY());
    paint();
  }

  public void erase() {
	//TODO 3. call the method erase to the eye and the head
	//---------------------------------------------
    //CZ:     zavolat erase na oko a hlavu
    this.eye.erase();
    this.head.erase();
  }

  public void paint() {
	//TODO 4. call the method paint to the eye and the head
	//------------------------------------
    //        zavolat paint na oko a hlavu
    this.head.paint();
    this.eye.paint();
  }

  public void moveRight(int step) {
    setPosition(this.xPos + step, this.yPos);

  }

  public void moveDown(int step) {
    setPosition(this.xPos, this.yPos + step);
  }

  private int getEyeSize() {
    return (int) getEyeSizeD();
  }

  private double getEyeSizeD() {
    return SIZE_OF_EYE_PORTION * SIZE;
  }

  private int computeAngle() {
    return 360 - ANGLE;
  }

  private int getEyeX() {
    switch (this.direction) {
      case EAST:
        return this.xPos + SIZE / 2 - getEyeSize() / 2 + getEyeSize() / 4;
      case WEST:
        return this.xPos + SIZE - getEyeSize() - SIZE / 2 + getEyeSize() / 2
            - getEyeSize() / 4;
      case NORTH:
      case SOUTH:
        return this.xPos + SIZE / 4;
      default:
        return 0;
    }

  }

  private int getEyeY() {
    switch (this.direction) {
      case EAST:
      case WEST:
        return this.yPos + SIZE / 4;
      case NORTH:
        return this.yPos + SIZE - getEyeSize() - SIZE / 2 + getEyeSize() / 2
            - getEyeSize() / 4;
      case SOUTH:
        return this.yPos + SIZE / 2 - getEyeSize() / 2 + getEyeSize() / 4;
      default:
        return 0;
    }

  }
}
