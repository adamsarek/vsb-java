package pl1.shapes.manager;

import javafx.scene.text.Font;
import pl1.shapes.MyColor;
import pl1.shapes.Position;


public class Text implements Paintable {
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

  
  public static final int PLAIN = 0;

  
  public static final int BOLD = 1;

  
  public static final int ITALIC = 2;

  
  public static final MyColor DEFAULT_COLOR = MyColor.BLACK;

  
  private static final CanvasManager SP = CanvasManager.getInstance();

//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

  
  private final String name;

//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

  private int xPos; //Bodová x-ová souřadnice instance
  private int yPos; //Bodová y-ová souřadnice instance
  private MyColor barva; //Barva instance
  private Font font; //Písmo, kterým se text sází
  private Font kfont; //Písmo nastavené pro kreslítko

//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ METODY TŘÍDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

  
  public Text(String text) {
    this(text, 0, 0);
  }

  
  public Text(String text, int x, int y) {
    this(text, x, y, DEFAULT_COLOR);
  }

  
  public Text(String text, Position počátek) {
    this(text, počátek.x, počátek.y);
  }

  
  public Text(String text, Position počátek, MyColor color) {
    this(text, počátek.x, počátek.y, color);
  }

  
  public Text(String text, int x, int y, MyColor color) {
    name = text;
    xPos = x;
    yPos = y;
    barva = color;
    font = new Font("Dialog", 12);
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
    SP.repaint();
  }

  
  public void setPosition(Position position) {
    setPosition(position.x, position.y);
  }

  
  public MyColor getBarva() {
    return barva;
  }

  
  public void setBarva(MyColor nová) {
    barva = nová;
    SP.repaint();
  }

  
  public String getNázev() {
    return name;
  }

  
  public void setFont(String nazev, int style, int velikost) {
    font = new Font(nazev, velikost);
  }

//== PŘEKRYTÉ METODY IMPLEMENTOVANÝCH ROZHRANÍ =================================

  
  @Override
  public void paint(MyGraphics graphics) {
    if (font != kfont) {
      graphics.setFont(font);
      kfont = font;
    }
    graphics.drawText(name, xPos, yPos + font.getSize(), barva);
  }

//== PŘEKRYTÉ ABSTRAKTNÍ METODY RODIČOVSKÉ TŘÍDY ===============================
//== PŘEKRYTÉ KONKRÉTNÍ METODY RODIČOVSKÉ TŘÍDY ================================

  
  @Override
  public String toString() {
    return name;
  }

//== NOVĚ ZAVEDENÉ METODY INSTANCÍ =============================================

  
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
