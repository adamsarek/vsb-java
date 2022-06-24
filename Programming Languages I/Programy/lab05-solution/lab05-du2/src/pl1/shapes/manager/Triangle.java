package pl1.shapes.manager;

import pl1.shapes.Direction8;
import pl1.shapes.MyColor;


public class Triangle implements Paintable {
//== KONSTANTNI ATRIBUTY TRIDY =================================================

  
  public static final MyColor DEFAULT_COLOR = MyColor.GREEN;

  
  public static final Direction8 DEFAULT_DIRECTION = Direction8.NORTH;

  
  public static final int MAX_STEP = 100;

  
  //private static final Canvas CANVAS = Canvas.getInstance();

//== PROMENNE ATRIBUTY TRIDY ===================================================

  
  private static int step = 50;

  
  private static int count = 0;

//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

  
  private final int order = ++count;

  
  private String name = "Trojuhelnik_" + order;

//== PROMENNE ATRIBUTY INSTANCI ================================================

  private int xPos; //Bodova x-ova souradnice instance
  private int yPos; //Bodova y-ova souradnice instance
  private int width; //sirka v bodech
  private int height; //Vyska v bodech
  private MyColor color; //Barva instance
  private Direction8 direction; //Smer, do nejz je otocen vrchol trojuhelniku

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

  
  public Triangle() {
    this(0, 0, 2 * step, step);
  }

  
  public Triangle(int x, int y, int width, int height) {
    this(x, y, width, height, DEFAULT_COLOR, DEFAULT_DIRECTION);
  }

  
  public Triangle(int x, int y, int width, int height, Direction8 direction) {
    this(x, y, width, height, DEFAULT_COLOR, direction);
  }

  
  public Triangle(int x, int y, int width, int height, MyColor color) {
    this(x, y, width, height, color, DEFAULT_DIRECTION);
  }

  
  public Triangle(int x, int y, int width, int height, MyColor color,
      Direction8 direction) {
    //Test platnosti parametru
    if ((x < 0) || (y < 0) || (width <= 0) || (height <= 0)) {
      throw new IllegalArgumentException(
          "\nParametry nemaji povolene hodnoty: x=" + x + ", y=" + y
              + ", sirka=" + width + ", vyska=" + height);
    }

    //Parametry akceptovany --> muzeme tvorit
    xPos = x;
    yPos = y;
    this.width = width;
    this.height = height;
    this.color = color;
    this.direction = direction;

  }

//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

  
  public int getX() {
    return xPos;
  }

  
  public int getY() {
    return yPos;
  }

  
  public void setPosition(int x, int y) {
    erase();
    xPos = x;
    yPos = y;
    CanvasManager.getInstance().repaint();
  }

  
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
    erase();
    this.width = width;
    this.height = height;
    CanvasManager.getInstance().repaint();
  }

  
  public MyColor getColor() {
    return color;
  }

  
  public void setColor(MyColor newColor) {
    color = newColor;
    CanvasManager.getInstance().repaint();
  }

  
  public Direction8 getDirection() {
    return direction;
  }

  
  public void setDirection(Direction8 newDirection) {
    direction = newDirection;
    CanvasManager.getInstance().repaint();
  }

  
  public String getName() {
    return name;
  }

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

  
  @Override
  public String toString() {
    return name + "_(x=" + xPos + ",y=" + yPos + ",sirka=" + width + ",vyska="
        + height + ",barva=" + color + ")";
  }

//== NOVE ZAVEDENE METODY INSTANCI =============================================

  
  @Override
  public void paint(MyGraphics g) {
    double[][] points = getVertices();
    g.fillPolygon(points[0], points[1], color);

  }

  
  public void erase() {
  }

  
  public void moveRight(int distance) {
    setPosition(xPos + distance, yPos);
  }

  
  public void moveRight() {
    moveRight(step);
  }

  
  public void moveLeft() {
    moveRight(-step);
  }

  
  public void moveDown(int distance) {
    setPosition(xPos, yPos + distance);
  }

  
  public void moveDown() {
    moveDown(step);
  }

  
  public void moveUp() {
    moveDown(-step);
  }

//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

  
  private double[][] getVertices() {
    double[] xpoints = null;
    double[] ypoints = null;

    switch (direction) {
      case EAST:
        xpoints = new double[]{xPos, xPos + (width), xPos};
        ypoints = new double[]{yPos, yPos + (height / 2), yPos + height};
        break;

      case NORTHEAST:
        xpoints = new double[]{xPos, xPos + width, xPos + width};
        ypoints = new double[]{yPos, yPos, yPos + height};
        break;

      case NORTH:
        xpoints = new double[]{xPos, xPos + (width / 2), xPos + width};
        ypoints = new double[]{yPos + height, yPos, yPos + height};
        break;

      case NORTHWEST:
        xpoints = new double[]{xPos, xPos, xPos + width};
        ypoints = new double[]{yPos + height, yPos, yPos};
        break;

      case WEST:
        xpoints = new double[]{xPos, xPos + width, xPos + width};
        ypoints = new double[]{yPos + (height / 2), yPos, yPos + height};
        break;

      case SOUTHWEST:
        xpoints = new double[]{xPos, xPos, xPos + width};
        ypoints = new double[]{yPos, yPos + height, yPos + height};
        break;

      case SOUTH:
        xpoints = new double[]{xPos, xPos + (width / 2), xPos + width};
        ypoints = new double[]{yPos, yPos + height, yPos,};
        break;

      case SOUTHEAST:
        xpoints = new double[]{xPos, xPos + width, xPos + width};
        ypoints = new double[]{yPos + height, yPos + height, yPos};
        break;

      default:
        throw new IllegalStateException(
            "Instance ukazuje do nedefinovaneho smeru");
    }
    return new double[][]{xpoints, ypoints};
  }

//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
