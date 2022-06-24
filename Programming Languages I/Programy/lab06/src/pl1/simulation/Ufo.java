package pl1.simulation;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import pl1.lab06.Constants;
import pl1.manager.Paintable;

public class Ufo implements Paintable, Boundary {

	private int widthOfCanvas;
	private int heightOfCanvas;
	private int x;
	private int y;
	
	public Ufo(int widthOfCanvas, int heightOfCanvas) {
		super();
		this.widthOfCanvas = widthOfCanvas;
		this.heightOfCanvas = heightOfCanvas;
		generatePosition();
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(Constants.UFO, x, y, 45, 45);
	}

	@Override
	public double getXOfCorner() {
		return x;
	}

	@Override
	public double getYOfCorner() {
		return y;
	}

	@Override
	public double getWidth() {
		return 45;
	}

	@Override
	public double getHeight() {
		return 45;
	}

	public void hit() {
		generatePosition();
	}

	private void generatePosition() {
		Random rnd = new Random();
		x = rnd.nextInt(widthOfCanvas/2) + widthOfCanvas/2;
		y = rnd.nextInt(heightOfCanvas/2) + heightOfCanvas/2;
	}

}
