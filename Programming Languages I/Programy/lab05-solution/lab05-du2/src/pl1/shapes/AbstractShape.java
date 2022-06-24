package pl1.shapes;

import pl1.shapes.manager.CanvasManager;
import pl1.shapes.manager.MyGraphics;
import pl1.shapes.manager.Paintable;
import pl1.shapes.tools.Movable;

public abstract class AbstractShape implements Movable, Paintable {

	private int x;
	private int y;
	
	
	
	public AbstractShape(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Position getPosition() {
		return new Position(x, y);
	}
	
	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	    CanvasManager.getInstance().repaint();
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public abstract void paint(MyGraphics graphics);
	
}
