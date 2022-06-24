package pl1.shapes.manager;

import pl1.shapes.Dimension;
import pl1.shapes.MyColor;
import pl1.shapes.Position;


public class Ellipse implements Paintable {
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

  
  public static final MyColor DEFAULT_COLOR = MyColor.BLUE;

  
  public static final int MAX_STEP = 100;

  
  private static final CanvasManager CM = CanvasManager.getInstance();

//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

  
  private static int step = 50;

  
  private static int count = 0;

//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

  
  private final int order = ++count;

  
  private String name = "Elipsa_" + order;

//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

  private int xPos; //Bodová x-ová souřadnice instance
  private int yPos; //Bodová y-ová souřadnice instance
  private int width; //šířka v bodech
  private int height; //Výška v bodech
  private MyColor color; //Barva instance

//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================

  
  public static int getStep() {
    return step;
  }

  public static void setStep(int size) {
    if ((size < 0) || (size > MAX_STEP)) {
      throw new IllegalArgumentException(
          "Krok musí byt z intervalu <0;" + MAX_STEP + ">.");
    }
    step = size;
  }

//== OSTATNÍ METODY TŘÍDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

  
  public Ellipse() {
    this(0, 0, 2 * step, step);
  }

  
  public Ellipse(int x, int y, int width, int height) {
    this(x, y, width, height, DEFAULT_COLOR);
  }

  
  public Ellipse(int x, int y, int width, int height, MyColor color) {
    //Test platnosti parametru
    if ((x < 0) || (y < 0) || (width <= 0) || (height <= 0)) {
      throw new IllegalArgumentException(
          "\nParametry nemají povolené hodnoty: x=" + x + ", y=" + y
              + ", šířka=" + width + ", výška=" + height);
    }

    //Parametry akceptovány --> můžeme tvořit
    xPos = x;
    yPos = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

//== PŘÍSTUPOVÉ METODY ATRIBUTU INSTANCÍ =======================================

  
  public int getX() {
    return xPos;
  }

  
  public int getY() {
    return yPos;
  }

  
  public Position getPosition() {
    return new Position(xPos, yPos);
  }

  
  public void setPosition(int x, int y) {
    xPos = x;
    yPos = y;
    CM.repaint();
  }

  
  public void setPosition(Position position) {
    setPosition(position.x, position.y);
  }

  
  public int getWidth() {
    return width;
  }

  
  public int getHeight() {
    return height;
  }

  
  public Dimension getDimension() {
    return new Dimension(width, height);
  }

  
  public void setDimension(int size) {
    setDimension(size, size);
  }

  
  public void setDimension(int width, int height) {
    if ((width < 0) || (height < 0)) {
      throw new IllegalArgumentException(
          "Rozměry musí byt nezáporné: šířka=" + width + ", výška=" + height);
    }
    this.width = width;
    this.height = height;
    CM.repaint();
  }

  
  public void setDimension(Dimension dimension) {
    setDimension(dimension.width, dimension.height);
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

//== PŘEKRYTÉ METODY IMPLEMENTOVANÝCH ROZHRANÍ =================================
//== PŘEKRYTÉ ABSTRAKTNÍ METODY RODIČOVSKÉ TŘÍDY ===============================
//== PŘEKRYTÉ KONKRÉTNÍ METODY RODIČOVSKÉ TŘÍDY ================================

  
  @Override
  public String toString() {
    return name + "_(x=" + xPos + ",y=" + yPos + ",šířka=" + width + ",výška="
        + height + ",barva=" + color + ")";
  }

//== NOVĚ ZAVEDENÉ METODY INSTANCÍ =============================================

  
  @Override
  public void paint(MyGraphics g) {

    g.fillEllipse(xPos, yPos, width, height, color);
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

//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
