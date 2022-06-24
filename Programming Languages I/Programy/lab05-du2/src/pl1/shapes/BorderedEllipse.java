package pl1.shapes;

import pl1.shapes.manager.Ellipse;
import pl1.shapes.manager.MyGraphics;

public class BorderedEllipse extends Ellipse{

	private MyColor colorOfBorder;

	public BorderedEllipse() {
		this(0, 0, 20, 20, MyColor.YELLOW, MyColor.BLACK);
	}
	
	public BorderedEllipse(int x, int y, int width, 
			int height,MyColor color, 
			MyColor colorOfOBorder) {
		super(x,y,width, height);
		this.colorOfBorder = colorOfOBorder;
	}
	
	@Override
	public void paint(MyGraphics g) {
		super.paint(g);
		g.drawEllipse(getX(), getY(), getWidth(), 
				getHeight(), colorOfBorder);
	}
}
