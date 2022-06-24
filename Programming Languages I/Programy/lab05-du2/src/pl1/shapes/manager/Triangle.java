package pl1.shapes.manager;

import pl1.shapes.AbstractShape;
import pl1.shapes.Direction8;
import pl1.shapes.MyColor;

/*******************************************************************************
 * Instance tridy {@code Trojuhelnik} predstavuji trojuhelniky urcene
 * pro praci na virtualnim platne pri prvnim seznameni s tridami a objekty.
 * Vychozi podoba tridy urcena pro prvni seznameni s tridami a objekty.
 *
 * @author Rudolf PECINOVSKY
 * @version 3.00.002
 */
public class Triangle extends AbstractShape
{
	protected static final MyColor DEFAULT_COLOR = MyColor.GREEN;
	
	public static final Direction8 DEFAULT_DIRECTION = Direction8.NORTH;
	
	protected static final String DEFAULT_NAME = "Trojuhelnik_";
	
	protected static int count = 0;
	
	private Direction8 direction; //Smer, do nejz je otocen vrchol trojuhelniku
	
	public Triangle(int x, int y, int width, int height) 
	{
		this(x, y, width, height, DEFAULT_COLOR, DEFAULT_DIRECTION, DEFAULT_NAME);
	}
	
	public Triangle(int x, int y, int width, int height, Direction8 direction) 
	{
		this(x, y, width, height, DEFAULT_COLOR, direction, DEFAULT_NAME);
	}
	
	public Triangle(int x, int y, int width, int height, MyColor color, String name) 
	{
		this(x, y, width, height, color, DEFAULT_DIRECTION, name);
	}
	
	public Triangle(int x, int y, int width, int height, MyColor color, Direction8 direction, String name) 
	{
		super(x, y, width, height, color, name);
		this.direction = direction;
	}
	
	
	public Direction8 getDirection() 
	{
		return direction;
	}
	
	public void setDirection(Direction8 newDirection) 
	{
		direction = newDirection;
		CanvasManager.getInstance().repaint();
	}
	
	@Override
	public void paint(MyGraphics g) {
	  double[][] points = getVertices();
	  g.fillPolygon(points[0], points[1], color);
	
	}
	
	private double[][] getVertices() 
	{
		double[] xpoints = null;
		double[] ypoints = null;
		
		switch (direction) 
		{
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
	
	public boolean isInBound(double x, double y) 
	{
		return (xPos <= x) && (x <= (xPos + width)) 
			&& (yPos <= y) && (y <= (yPos + height));
	}
}
