package pl1.manager;

import java.io.IOException;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import pl1.lab06.Constants;
import pl1.simulation.SimulationManager;
/**
 * Main UI class that handles events and coordinates the whole functionality.
 * @author koz01
 *
 */
public class CatapultSimulationController {

	@FXML
	private javafx.scene.canvas.Canvas canvas;

	@FXML
	private BorderPane root;

	@FXML
	private Slider power;
	
	@FXML
	private Slider angle;

	@FXML
	private Button fire;

	private SimulationManager simulationManager;

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
	
	

	private void angleChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		simulationManager.setAngle(angle.getValue());
	}
	
	private void powerChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		simulationManager.setPower(power.getValue());
	}

	private void firePressed(ActionEvent e) {
		fire.setDisable(true);
		new Thread(this::doBallMovement).start();
	}

	private void doBallMovement() {
		simulationManager.startSimulation();
		Platform.runLater(this::doRestoreToInitialState);
	}

	private void doRestoreToInitialState() {
		fire.setDisable(false);
		simulationManager.restore();
	}
	
	private void init() {
		synchronized (CanvasManager.class) {
			CanvasManager.constructInstance(canvas);
			power.setValue(Constants.INITIAL_POWER);
			power.setMin(Constants.MIN_POWER);
			power.setMax(Constants.MAX_POWER);
			power.valueProperty().addListener(this::powerChanged);
			angle.valueProperty().addListener(this::angleChanged);
			fire.setOnAction(this::firePressed);
			simulationManager = new SimulationManager( angle.getValue(), power.getValue());
			CanvasManager.getInstance().redraw();
		}
	}

	

	
}
