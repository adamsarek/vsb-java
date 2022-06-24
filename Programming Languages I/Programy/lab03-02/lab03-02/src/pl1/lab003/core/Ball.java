package pl1.lab003.core;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Graphical representation of a ball. It also contains logic for a movement and an acceleration.
 * 
 * @author koz01
 *
 */
public class Ball {
	
	private double x;
	private double y;
	private double xVelocity;
	private double yVelocity;
	
	private final int radius;
	
	public Ball(int x, int y, int radius) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void accelerate(double xAcc, double yAcc, double difTime) {
		//TODO Velocity = Velocity + Acceleration*time
		xVelocity = xVelocity + xAcc*difTime;
		yVelocity = yVelocity + yAcc*difTime;
		//Note that Velocity and Acceleration has x, y components.
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void move(double time) {
		//TODO Position = Position + Velocity*time
		x = x + xVelocity*time;
		y = y + yVelocity*time;
		//Note that Position and Velocity has x, y components.
	}

	public void paint(GraphicsContext gc) {
		gc.setFill(Color.BLUEVIOLET);
		gc.setStroke(Color.BLUEVIOLET);
		gc.fillOval(x - radius, y - radius, radius * 2., radius * 2.);
	}
	
	/**
	 * It makes x dimension of space infinite
	 * 
	 * @param canvasWidth
	 */
	public void fixXCoordinate(int canvasWidth) {
		x = (x + canvasWidth) % canvasWidth;
	}

	public void stopAndMoveTo(int newX, int newY) {
		xVelocity = 0;
		yVelocity = 0;
		this.x = newX;
		this.y = newY;
	}

}