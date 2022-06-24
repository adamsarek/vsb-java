package pl1.lab003.core;

import java.io.IOException;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import pl1.lab003.Constants;
/**
 * Main UI class that handles events and coordinates the whole functionality.
 * @author koz01
 *
 */
public class CatapultSimulationController {

	@FXML
	private Canvas canvas;

	@FXML
	private BorderPane root;

	@FXML
	private Slider power;
	
	@FXML
	private Slider angle;

	@FXML
	private Button fire;

	private Catapult catapult;

	private Ball ball;

	private Physics physics;

	private int xOfCatapult;

	private int yOfCatapult;

	public static CatapultSimulationController create() throws IOException {
		FXMLLoader loader = new FXMLLoader(
				CatapultSimulationController.class.getResource("CatapultSimulationPanel.fxml"));
		loader.load();
		CatapultSimulationController result = loader.getController();
		result.init();
		return result;
	}

	public Scene createScene() {
		return new Scene(root, 450, 540);
	}
	
	public void needsRedraw() {
		Platform.runLater(this::redraw);
	}

	private void angleChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		catapult.setAngle((int) angle.getValue());
		redraw();
	}
	
	private void powerChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		catapult.setPower((int) power.getValue());
	}

	private void firePressed(ActionEvent e) {
		fire.setDisable(true);
		new Thread(this::doBallMovement).start();
	}

	private void doBallMovement() {
		catapult.shootBall(ball, this);
		physics.manageBallMovement(ball, this, (int) canvas.getWidth(), (int) canvas.getHeight());
		Platform.runLater(this::doRestoreToInitialState);
	}

	private void doRestoreToInitialState() {
		fire.setDisable(false);
		ball.stopAndMoveTo(xOfCatapult, yOfCatapult);
		redraw();
	}
	
	private void init() {
		xOfCatapult = Constants.INDENTATION;
		yOfCatapult = (int) (canvas.getHeight() - Constants.INDENTATION);
		catapult = new Catapult(xOfCatapult, yOfCatapult, Constants.LEN_OF_CATAPULT);
		
		power.setValue(Constants.INITIAL_POWER);
		power.setMin(Constants.MIN_POWER);
		power.setMax(Constants.MAX_POWER);
		power.valueProperty().addListener(this::powerChanged);
		catapult.setPower((int) power.getValue());
		
		angle.valueProperty().addListener(this::angleChanged);
		catapult.setAngle((int) angle.getValue());
		
		ball = new Ball(xOfCatapult, yOfCatapult, Constants.SIZE_OF_BALL);
		fire.setOnAction(this::firePressed);
		physics = new Physics(Constants.GRAVITY);
		redraw();
	}

	private void redraw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		redrawCanvas(gc);
		catapult.paint(gc);
		ball.paint(gc);
	}

	private void redrawCanvas(GraphicsContext gc) {
		Paint original = gc.getFill();
		gc.setFill(Color.LIGHTYELLOW);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(original);
	}
}
