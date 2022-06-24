package pl1.shapes.manager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import pl1.shapes.MyColor;


public class MyGraphics {
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

  private GraphicsContext g;

//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

  //private MyColor backgroundColor = null;

//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ METODY TŘÍDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

  
  MyGraphics(GraphicsContext g) {
    this.g = g;
  }

//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================

  
  public void setFont(Font font) {
    g.setFont(font);
  }

//== PŘEKRYTÉ METODY IMPLEMENTOVANÝCH ROZHRANÍ =================================
//== PŘEKRYTÉ ABSTRAKTNÍ METODY RODIČOVSKÉ TŘÍDY ===============================
//== PŘEKRYTÉ KONKRÉTNÍ METODY RODIČOVSKÉ TŘÍDY ================================
//== NOVĚ ZAVEDENÉ METODY INSTANCÍ =============================================

  
  public void drawEllipse(double x, double y, double width, double height,
      MyColor color) {
    g.setStroke(color.getColor());
    g.strokeOval(x, y, width, height);
  }

  
  public void fillEllipse(double x, double y, double width, double height,
      MyColor color) {
    g.setFill(color.getColor());
    g.fillOval(x, y, width, height);
  }

  
  public void drawRectangle(double x, double y, double width, double height,
      MyColor color) {
    g.setStroke(color.getColor());
    g.strokeRect(x, y, width, height);
  }

  
  public void fillRectangle(double x, double y, double width, double height,
      MyColor color) {
    g.setFill(color.getColor());
    g.fillRect(x, y, width, height);
  }

  
  public void drawPolygon(double[] x, double[] y, MyColor color) {
    g.setStroke(color.getColor());
    g.strokePolygon(x, y, Math.min(x.length, y.length));
  }

  
  public void fillPolygon(double[] x, double[] y, MyColor color) {
    g.setFill(color.getColor());
    g.fillPolygon(x, y, Math.min(x.length, y.length));
  }

  
  public void drawLine(double x1, double y1, double x2, double y2,
      MyColor color) {
    g.setStroke(color.getColor());
    g.strokeLine(x1, y1, x2, y2);
  }

  
  public void drawText(String text, double x, double y, MyColor color) {
    g.setStroke(color.getColor());
    g.strokeText(text, x, y);
  }

//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
