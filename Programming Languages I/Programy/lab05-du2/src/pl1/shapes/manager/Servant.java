package pl1.shapes.manager;

import pl1.shapes.*;
import pl1.shapes.tools.Movable;
import pl1.utils.IO;

public class Servant 
{
	public static void moveTo(Movable obj, Position newPosition, int stepDistance, int interval)
	{
		float distance = (float) Math.sqrt(	  (obj.getX() - newPosition.x) * (obj.getX() - newPosition.x)
										+ (obj.getY() - newPosition.y) * (obj.getY() - newPosition.y));
		int steps = (int)(distance / stepDistance);
		
		int lengthX = newPosition.x - obj.getX();
		float stepX = lengthX / (float)steps;
		
		int lengthY = newPosition.y - obj.getY();
		float stepY = lengthY / (float)steps;
		
		int startingX = obj.getX();
		int startingY = obj.getY();
		
		for(int step = 0; step < steps; step++)
		{
			obj.setPosition(startingX + (int)(stepX * step), startingY + (int)(stepY * step));
			IO.wait(interval);
		}
		obj.setPosition(newPosition.x, newPosition.y);
	}
}
