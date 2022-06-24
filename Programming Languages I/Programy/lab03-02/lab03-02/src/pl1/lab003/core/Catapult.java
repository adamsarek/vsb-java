package pl1.lab003.core;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import pl1.common.ProcessRoutines;
/**
 * Graphical representation of a catapult and it also implements process of a shooting.
 * @author koz01
 *
 */
public class Catapult {

	private static final int ARR_SIZE = 5;
	private final int x;
	private final int y;
	private final int len;

	private int angle;

	private int power;

	public Catapult(int x, int y, int len) {
		super();
		this.x = x;
		this.y = y;
		this.len = len;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void shootBall(Ball ball, CatapultSimulationController controllerForRedraw) {
		//TODO - Implement acceleration by catapult:
		// 1. Spread power to x and y directions: 
		//  - xAcc = cos(angle) * power
		//  - yAcc = sin(angle) * power (coordinate system in canvas is oposite)
		double angleInRadians = Math.toRadians(angle);
		double xAcc = cos(angleInRadians)*power;
		double yAcc = -sin(angleInRadians)*power;
		// 2. Compute end of the catapult.
		int xBound = (int) (x + cos(angleInRadians) * len);
		int yBound = (int) (y - sin(angleInRadians) * len);
		// 3. Store initial time in the variable lastTime.
		long time = System.currentTimeMillis();
		while(ball.getX() < xBound && ball.getY() > yBound) {
			ProcessRoutines.sleep(1);
			long newTime = System.currentTimeMillis();
			double difTime = (newTime - time) / 1000.;
			ball.move(difTime);
			ball.accelerate(xAcc, yAcc, difTime );
			time = newTime;
			controllerForRedraw.needsRedraw();
		}
		// 4. while ball do not reach an end of the catapult do:
		//      Sleep for a one milisecond (use ProcessRoutines.sleep).
		//      Get actual time and compute a time difference.
		//      Move ball(Call method move on the ball).
		//      Compute new acceleration of the ball(Call method accelerate on the ball).
		//      Update variable lastTime with a actual time.
		//		Force canvas redraw.
		
		
	}

	public void paint(GraphicsContext gc) {
		Affine oldTransform = gc.getTransform();
		Transform transform = Transform.translate(x, y);
		transform = transform.createConcatenation(Transform.rotate(-angle, 0, 0));
		gc.setTransform(new Affine(transform));
		gc.setFill(Color.LIGHTBLUE);
		gc.setStroke(Color.LIGHTBLUE);
		gc.strokeLine(0, 0, len, 0);
		gc.fillPolygon(new double[] { len, len - ARR_SIZE, len - ARR_SIZE, len },
				new double[] { 0, -ARR_SIZE, ARR_SIZE, 0 }, 4);
		gc.setTransform(oldTransform);
	}

}
