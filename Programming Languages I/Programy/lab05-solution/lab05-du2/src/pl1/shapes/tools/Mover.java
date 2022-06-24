package pl1.shapes.tools;

import pl1.shapes.Position;
import pl1.shapes.manager.CanvasManager;
import pl1.utils.ProcessRoutines;


public class Mover {

  private static final int PERIOD = 50;

//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

  
  private static int count = 0;

//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

  
  private final String name;

//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

  
  private double speed;

  private CanvasManager CM = CanvasManager.getInstance();
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ METODY TŘÍDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

  
  public Mover() {
    this(1);
  }

  
  public Mover(double speed) {
    if (speed <= 0) {
      throw new IllegalArgumentException("Giver speed must be positive!");
    }
    this.speed = (speed / 1000) * PERIOD;
    name =
        getClass().getName() + "(ID=" + ++count + ",speed=" + speed + ")";
  }

//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================
//== PŘEKRYTÉ METODY IMPLEMENTOVANÝCH ROZHRANÍ =================================
//== PŘEKRYTÉ ABSTRAKTNÍ METODY RODIČOVSKÉ TŘÍDY ===============================
//== PŘEKRYTÉ KONKRÉTNÍ METODY RODIČOVSKÉ TŘÍDY ================================

  
  @Override
  public String toString() {
    return name;
  }

//== NOVĚ ZAVEDENÉ METODY INSTANCÍ =============================================

  
  public void move(Movable object, int toRight, int toDown) {

    double distance = Math.sqrt((toRight * toRight) + (toDown * toDown));
    int steps = (int) (distance / speed);
    double dx = (toRight + .4) / steps;
    double dy = (toDown + .4) / steps;
    Position p = object.getPosition();
    double x = p.getX() + .4;
    double y = p.getY() + .4;

    for (int i = steps; i > 0; i--) {
      x = x + dx;
      y = y + dy;
      CM.noPaint();
      object.setPosition((int) x, (int) y);
      CM.returnPaint();
      CM.repaint();
      ProcessRoutines.sleep(PERIOD);
    }
  }

  
  public void moveBy(Movable object, Position movement) {
    move(object, movement.x, movement.y);
  }

  
  public void moveOn(Movable object, int x, int y) {
    Position p = object.getPosition();
    move(object, x - p.x, y - p.y);
  }

  
  public void moveOn(Movable object, Position position) {
    moveOn(object, position.x, position.y);
  }

//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
