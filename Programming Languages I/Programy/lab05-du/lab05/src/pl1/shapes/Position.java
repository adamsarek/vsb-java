package pl1.shapes;


public class Position
{
    
    public final int x;
    
    public final int y;
    
    public Position( int x, int y )
    { 
        this.x  = x;
        this.y  = y;
    }

    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }

    @Override
    public boolean equals( Object o )
    {
        return (o instanceof Position )   &&
               (((Position)o).x  ==  x)   && 
               (((Position)o).y  ==  y);
    }
    
    @Override
    public String toString()
    {
        return "Pozice[x=" + x + ",y=" + y + "]";
    }
}

