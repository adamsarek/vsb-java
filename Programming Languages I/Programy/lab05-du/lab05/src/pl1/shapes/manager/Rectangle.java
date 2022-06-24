package pl1.shapes.manager;

import pl1.shapes.AbstractShape;
import pl1.shapes.MyColor;
import pl1.shapes.Position;


public class Rectangle extends AbstractShape implements Clickable{

//== KONSTANTNI ATRIBUTY TRIDY =================================================

  
  public static final MyColor DEFAULT_COLOR = MyColor.RED;

  
  public static final int MAX_STEP = 100;

  
  private static final CanvasManager CM = CanvasManager.getInstance();

//== PROMENNE ATRIBUTY TRIDY ===================================================

  
  private static int step = 50;

  
  private static int count = 0;

//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

  
  private final int order = ++count;

  
  private final String name = "Obdelnik_" + order;

//== PROMENNE ATRIBUTY INSTANCI ================================================

  private int width; //sirka v bodech
  private int height; //Vyska v bodech
  private MyColor color; //Barva instance

//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================

  
  public static int getStep() {
    return step;
  }

  public static void setStep(int size) {
    if ((size < 0) || (size > MAX_STEP)) {
      throw new IllegalArgumentException(
          "Krok musi byt z intervalu <0;" + MAX_STEP + ">.");
    }
    step = size;
  }

//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

  
  //constructor overloading
  public Rectangle() {
    this(0, 0, 2 * step, step);
  }

  
  public Rectangle(int x, int y, int width, int height) {
    this(x, y, width, height, DEFAULT_COLOR);
  }

  
  public Rectangle(int x, int y, int width, int height, MyColor color) {
    //Test platnosti parametru
	super(x, y);
	if ((x < 0) || (y < 0) || (width <= 0) || (height <= 0)) {
      throw new IllegalArgumentException(
          "\nParametry nemaji povolene hodnoty: x=" + x + ", y=" + y
              + ", sirka=" + width + ", vyska=" + height);
    }
    
    //Parametry akceptovany --> muzeme tvorit
    this.width = width;
    this.height = height;
    this.color = color;

  }

  public Rectangle getInstance() {
    return this;
  }

  @Override
  public void paint(MyGraphics graphics) {
    graphics.fillRectangle(getX(), getY(), getWidth(), getHeight(), color);
  }

//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

  
 

 
  
  
  public int getWidth() {
    return width;
  }

  
  public int getHeight() {
    return height;
  }

  
  public void setDimension(int size) {
    setDimension(size, size);
  }

  
  public void setDimension(int width, int height) {
    if ((width < 0) || (height < 0)) {
      throw new IllegalArgumentException(
          "Rozmery musi byt nezaporne: sirka=" + width + ", vyska=" + height);
    }

    this.width = width;
    this.height = height;
    CM.repaint();
  }

  
  public MyColor getColor() {
    return color;
  }

  
  public void setColor(MyColor newColor) {
    color = newColor;
    CM.repaint();
  }

  
  public String getName() {
    return name;
  }

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

  
  @Override
  public String toString() {
    return name + "_(x=" + getX() + ",y=" + getY() + ",sirka=" + width + ",vyska="
        + height + ",barva=" + color + ")";
  }

//== NOVE ZAVEDENE METODY INSTANCI =============================================

  
  public void moveRight(int distance) {		

    setPosition(getX() + distance, getY());
  }

  
  public void moveRight() {
    moveRight(step);
  }

  
  public void moveLeft() {
    moveRight(-step);
  }

  
  public void moveDown(int distance) {
    setPosition(getX(), getY() + distance);
  }

  
  public void moveDown() {
    moveDown(step);
  }

  
  public void moveUp() {
    moveDown(-step);
  }

  @Override
  public boolean isInBound(double x, double y) {

    return (getX() <= x) && (x <= (getY() + width)) && (getY() <= y)
        && (y <= (getY() + height));
  }

@Override
public Position getPosition() {
	return new Position(getX(), getY());
}

//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
