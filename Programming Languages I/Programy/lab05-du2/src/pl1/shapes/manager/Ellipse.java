package pl1.shapes.manager;

import pl1.shapes.AbstractShape;
import pl1.shapes.MyColor;

/*******************************************************************************
 * Instance třídy {@code Elipsa} představují elipsy určené
 * pro práci na virtuálním plátně při prvním seznámení s třídami a objekty.
 * Výchozí podoba třídy určená pro první seznámení s třídami a objekty.
 *
 * @author Rudolf PECINOVSKÝ
 * @version 3.00.002
 */
public class Ellipse extends AbstractShape
{
	protected static final MyColor DEFAULT_COLOR = MyColor.BLUE;
	
	protected static final String DEFAULT_NAME  = "Elipsa_";
	
	protected static int count = 0;

	public Ellipse() 
	{
		super();
	}
	
	public Ellipse(int x, int y, int width, int height) 
	{
		super(x, y, width, height, DEFAULT_COLOR, DEFAULT_NAME);
	}
	
	public Ellipse(int x, int y, int width, int height, MyColor color, String name) 
	{
		super(x, y, width, height, color, name);
	}
	
	@Override
	public void paint(MyGraphics g) 
	{
		g.fillEllipse(xPos - width / 2, yPos - height / 2, width, height, color);
	}
  	
  	@Override
	public boolean isInBound(double x, double y) 
	{
		double radius = this.width / 2.0;
		double centerX = this.xPos;
		double centerY = this.yPos;
		return Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)) < radius;
	}
}
