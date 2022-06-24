package pl1.lab05;

import pl1.shapes.BorderedEllipse;
import pl1.shapes.MyColor;
import pl1.shapes.manager.CanvasManager;

public class Run {

	public static void main(String[] args) {
		CanvasManager cm = CanvasManager.getInstance();
		cm.add(new BorderedEllipse(50, 20, 35, 45, MyColor.BLUE, MyColor.BROWN));
	}

}
