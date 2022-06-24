package pl1.shapes;

public class Area {
    public final int width;

    public final int height;
    
    public final int x;

    public final int y;
    
    public Area(int x, int y,  int width, int height )
    {
    	this.x  = x;
        this.y  = y;
    	this.width = width;
        this.height = height;
    }
    
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }


    
    public int getHeight()
    {
        return height;
    }
    
    
    
    @Override
    public boolean equals( Object o )
    {
        return (o instanceof Area)            &&
               (((Area)o).width  ==  width)   && 
               (((Area)o).height  ==  height)  &&
               (((Area)o).x  ==  x)   && 
               (((Area)o).y  ==  y);
    }
    
}
