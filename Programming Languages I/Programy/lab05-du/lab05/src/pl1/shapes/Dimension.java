package pl1.shapes;


public class Dimension
{

    public final int width;

    public final int height;

    public Dimension( int width, int height )
    {
        this.width = width;
        this.height = height;
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
        return (o instanceof Dimension)            &&
               (((Dimension)o).width  ==  width)   && 
               (((Dimension)o).height  ==  height);
    }

    @Override
    public String toString()
    {
        return "Dimension[width=" + width + ", height=" + height + "]";
    }


}

