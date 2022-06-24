package pl1.shapes.manager;

import pl1.shapes.tools.*;
import pl1.utils.IO;
import pl1.shapes.*;

public class Transformer 
{
	public static void transform(Transformable obj, Position newPosition, double resize)
	{
		float distance = (float) Math.sqrt(	  (obj.getX() - newPosition.x) * (obj.getX() - newPosition.x)
						+ (obj.getY() - newPosition.y) * (obj.getY() - newPosition.y));
		int stepDistance = 1;
		int steps = (int)(distance / stepDistance);
		
		int lengthX = newPosition.x - obj.getX();
		float stepX = lengthX / (float)steps;
		
		int lengthY = newPosition.y - obj.getY();
		float stepY = lengthY / (float)steps;
		
		int startingX = obj.getX();
		int startingY = obj.getY();
		
		double oldSize = obj.getSize();
		double newSize = oldSize * resize;
		
		
		for(int step = 0; step < steps; step++)
		{
			obj.setSize((int)(oldSize + oldSize * (resize - 1.0) * step / steps));
			obj.setPosition(startingX + (int)(stepX * step), startingY + (int)(stepY * step));
			IO.wait(5);
		}
		obj.setSize((int)newSize);
		obj.setPosition(newPosition.x, newPosition.y);
	}
}
