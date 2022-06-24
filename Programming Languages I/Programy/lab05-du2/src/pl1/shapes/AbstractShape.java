package pl1.shapes;

import pl1.shapes.MyColor;
import pl1.shapes.Position;
import pl1.shapes.manager.CanvasManager;
import pl1.shapes.manager.Clickable;
import pl1.shapes.manager.MyGraphics;
import pl1.shapes.manager.Paintable;
import pl1.shapes.tools.Transformable;

public abstract class AbstractShape implements Transformable, Paintable, Clickable
{
	protected int xPos; //Bodova x-ova souradnice instance
	protected int yPos; //Bodova y-ova souradnice instance
	protected int width; //sirka v bodech
	protected int height; //Vyska v bodech
	protected MyColor color; //Barva instance
	@SuppressWarnings("unused")
	protected final int order = ++count;
	protected static final CanvasManager CM = CanvasManager.getInstance();
	protected static final MyColor DEFAULT_COLOR = MyColor.AZURE;
	protected static int step = 50;
	protected static int count = 0;
	protected static final String DEFAULT_NAME = "AbstractShape_";
	protected String name;
	  
	public AbstractShape() 
	{
	  	this(0, 0, 2 * step, step);
	}
		  
	public AbstractShape(int x, int y, int width, int height) 
	{
		this(x, y, width, height, DEFAULT_COLOR, DEFAULT_NAME);
	}
	  
	public AbstractShape(int x, int y, int width, int height, MyColor color, String name) 
	{
		if ((x < 0) || (y < 0) || (width <= 0) || (height <= 0)) 
		{
			throw new IllegalArgumentException(
	        "\nParametry nemaji povolene hodnoty: x=" + x + ", y=" + y
	            + ", sirka=" + width + ", vyska=" + height);
		}
		
		this.width = width;
		this.height = height;
		this.color = color;
		this.name = name + order;
		xPos = x;
		yPos = y;
	}

	public int getSize() 
	{
		return width;
	}

	public void setSize(int size) 
	{
		setSize(size, size);
	}
	
	public void setSize(int width, int height) 
	{
		if ((width < 0) || (height < 0)) 
		{
	      throw new IllegalArgumentException(
	          "Rozměry musí byt nezáporné: šířka=" + width + ", výška=" + height);
	    }
		
	    this.width = width;
	    this.height = height;
	    CM.repaint();
	}
	
	public int getX() 
	{
		return xPos;
	}

	public int getY() 
	{
	    return yPos;
	}

	public Position getPosition() 
	{
	    return new Position(xPos, yPos);
	}

	public void setPosition(int x, int y) 
	{
		xPos = x;
		yPos = y;
	    CM.repaint();
	}

	public void setPosition(Position position) 
	{
	    setPosition(position.x, position.y);
	}

	public int getWidth() 
	{
	    return width;
	}

	public int getHeight() 
	{
	    return height;
	}

	public MyColor getColor() 
	{
	    return color;
	}

	public void setColor(MyColor newColor) 
	{
	    color = newColor;
	    CM.repaint();
	}

	public String getName() 
	{
	    return name;
	}

	@Override
	public String toString() 
	{
	    return name + "_(x=" + xPos + ",y=" + yPos + ",šířka=" + width + ",výška="
	        + height + ",barva=" + color + ")";
	}

	@Override
	public void paint(MyGraphics g)
	{}

	@Override
	public boolean isInBound(double x, double y) 
	{
		return (xPos - width / 2  <= x) && (x <= (xPos + width  / 2)) 
			&& (yPos - height / 2 <= y) && (y <= (yPos + height / 2));
	}
}