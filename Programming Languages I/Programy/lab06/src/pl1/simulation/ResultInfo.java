package pl1.simulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pl1.manager.Paintable;

public class ResultInfo implements Paintable{

	private int shoots;
	private int ufoHits;
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillText("Shoots: " + shoots + ", Hits: " + ufoHits, 10, 20);
	}

	
	public void increaseShoots() {
		shoots++;
	}


	public void ufoHit() {
		ufoHits++;
	}
}
