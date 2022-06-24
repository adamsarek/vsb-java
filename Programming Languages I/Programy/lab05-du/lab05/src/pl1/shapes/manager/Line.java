package pl1.shapes.manager;

import pl1.shapes.MyColor;
import pl1.shapes.Position;


public class Line implements Paintable {
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

  
  public static final MyColor DEFAULT_COLOR = MyColor.BLACK;

  
  private static final CanvasManager CM = CanvasManager.getInstance();

//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

  
  private static int count = 0;

//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

  
  private final String name;

//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

  private int xPos; //Bodová x-ová souřadnice počátku
  private int yPos; //Bodová y-ová souřadnice počátku
  private int kx; //xBodová -ová souřadnice konce
  private int ky; //Bodová y-ová souřadnice konce
  private MyColor color; //Barva instance

//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ METODY TŘÍDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

  
  public Line(int x, int y, int kx, int ky) {
    this(x, y, kx, ky, DEFAULT_COLOR);
  }

  
  public Line(Position start, Position end) {
    this(start.x, start.y, end.x, end.y, DEFAULT_COLOR);
  }

  
  public Line(Position start, Position end, MyColor color) {
    this(start.x, start.y, end.x, end.y, color);
  }

  
  public Line(int x, int y, int kx, int ky, MyColor color) {
    name = this.getClass().getSimpleName() + "_" + ++count;
    xPos = x;
    yPos = y;
    this.kx = kx;
    this.ky = ky;
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
    kx += x - xPos;
    xPos = x;
    ky += y - yPos;
    yPos = y;
    CM.repaint();
  }

  
  public void setPosition(Position position) {
    setPosition(position.x, position.y);
  }

  
  public int getKX() {
    return kx;
  }

  
  public int getKY() {
    return ky;
  }

  
  public Position getKPosition() {
    return new Position(getKX(), getKY());
  }

  
  public void setKPosition(int kx, int ky) {
    this.kx = kx;
    this.ky = ky;
    CM.repaint();
  }

  
  public void setKPosition(Position kPosition) {
    setPosition(kPosition.x, kPosition.y);
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

  
  @Override
  public void paint(MyGraphics graphics) {
    graphics.drawLine(xPos, yPos, kx, ky, color);
  }

//== PŘEKRYTÉ ABSTRAKTNÍ METODY RODIČOVSKÉ TŘÍDY ===============================
//== PŘEKRYTÉ KONKRÉTNÍ METODY RODIČOVSKÉ TŘÍDY ================================

  
  @Override
  public String toString() {
    return name + ": [" + xPos + ";" + yPos + "]-[" + kx + ";" + ky + "]";
  }

//== NOVĚ ZAVEDENÉ METODY INSTANCÍ =============================================

  
  public void connect(int px, int py, int kx, int ky) {
    setPosition(px, py);
    this.kx = kx;
    this.ky = ky;
    CM.repaint();
  }

  
  public void moveRight(int distance) {
    setPosition(xPos + distance, yPos);
  }

  
  public void moveDown(int distance) {
    setPosition(xPos, yPos + distance);
  }

//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
