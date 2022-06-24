package pl1.simulation;
import javafx.application.Platform;
import pl1.lab06.Constants;
import pl1.manager.CanvasManager;

public class SimulationManager {

	private int xOfCatapult;

	private int yOfCatapult;
	
	private CanvasManager canvas;
	
	private Catapult catapult;

	private Ball ball;

	private Physics physics;

	private ResultInfo info;

	private Ufo ufo;
	
	public SimulationManager( double angle, double power) {
		super();
		canvas = CanvasManager.getInstance();
		
		xOfCatapult = Constants.INDENTATION;
		yOfCatapult = (int) (canvas.getHeight() - Constants.INDENTATION);
		
		catapult = new Catapult(xOfCatapult, yOfCatapult, Constants.LEN_OF_CATAPULT);
		catapult.setAngle(angle);
		catapult.setPower(power);
		ball = new Ball(xOfCatapult, yOfCatapult, Constants.SIZE_OF_BALL);
		physics = new Physics(Constants.GRAVITY);
		info = new ResultInfo();
		ufo = new Ufo((int) canvas.getWidth(),(int) canvas.getHeight());
		canvas.add(catapult);
		canvas.add(ball);
		canvas.add(info);
		canvas.add(ufo);
	}
		
	public void setAngle(double angle) {
		catapult.setAngle(angle);
		redraw();
	}

	public void setPower(double value) {
		catapult.setPower(value);
	}

	public void restore() {
		ball.stopAndMoveTo(xOfCatapult, yOfCatapult);
		redraw();
	}

	public void startSimulation() {
		info.increaseShoots();
		catapult.shootBall(ball, this);
		physics.manageBallMovement(ball, this, (int) canvas.getWidth(), (int) canvas.getHeight());
	}

	public boolean stepOfSimulation() {
		boolean continueSimulation = !ball.overlaps(ufo);
		if (!continueSimulation) {
			ufo.hit();
			info.ufoHit();
		}
		redraw();
		return continueSimulation;
		
	}

	private void redraw() {
		Platform.runLater(canvas::redraw);
	}
}
