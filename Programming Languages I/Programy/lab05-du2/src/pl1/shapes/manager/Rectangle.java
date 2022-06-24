package pl1.shapes.manager;

import pl1.shapes.AbstractShape;
import pl1.shapes.MyColor;

/*******************************************************************************
 * Instance tridy {@code Obdelnik} predstavuji obdelniky urcene
 * pro praci na virtualnim platne pri prvnim seznameni s tridami a objekty.
 * Vychozi podoba tridy urcena pro prvni seznameni s tridami a objekty.
 *
 * @author Rudolf PECINOVSKY
 * @version 3.00.002
 */
public class Rectangle extends AbstractShape
{
	protected static final MyColor DEFAULT_COLOR = MyColor.RED;
	
	protected static final String DEFAULT_NAME = "Obdelnik_";
	
	protected static int count = 0;
	
	public Rectangle() 
	{
		super();
	}
	
	public Rectangle(int x, int y, int width, int height) 
	{
		super(x, y, width, height, DEFAULT_COLOR, DEFAULT_NAME);
	}
	
	public Rectangle(int x, int y, int width, int height, MyColor color, String name) 
	{
		super(x, y, width, height, color, name);
	}
	
	@Override
	public void paint(MyGraphics graphics) 
	{
		graphics.fillRectangle(xPos - width / 2, yPos - height / 2, width, height, color);
	}
}
