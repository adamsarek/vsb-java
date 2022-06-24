/*******************************************************************************
 * Jan Kožusznik
 * Copyright (c) 2016 All Right Reserved, http://www.kozusznik.cz
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package pl1.shapes.manager;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl1.shapes.tools.Movable;
import pl1.shapes.tools.Mover;

public class ManipulationPanelController {

	private static final String RECTANGLE = "Rectangle";

	private static final String TRIANGLE = "Triangle";

	private static final String ELLIPSE = "Ellipse";

	private ArrayList<Paintable> createdObjects = new ArrayList<>();
	@FXML
	private ComboBox<String> toCreate;

	@FXML
	private RadioButton createRB;

	@FXML
	private RadioButton moveRB;

	@FXML
	private RadioButton modifyRB;

	@FXML
	private RadioButton deleteRB;

	@FXML
	private TextField actualX;

	@FXML
	private TextField actualY;

	@FXML
	private TextField selectedX;

	@FXML
	private TextField selectedY;

	@FXML
	private Node controlPanel;

	private Movable selected;

	public ManipulationPanelController() {
		Platform.runLater(() -> {
			toCreate.setItems(FXCollections.observableArrayList(RECTANGLE, TRIANGLE, ELLIPSE));
			toCreate.setValue(RECTANGLE);
		});
	}

	public void mousePressed(MouseEvent e) {

		if (e.isPrimaryButtonDown()) {
			selectedX.setText(e.getX() + "");
			selectedY.setText(e.getY() + "");
			if (createRB.isSelected()) {
				createShape(e.getX(), e.getY(), toCreate.getValue());
			} else if (moveRB.isSelected()) {
				moveShape(e.getX(), e.getY());
			} else if (deleteRB.isSelected()) {
				removeShape(e.getX(), e.getY());
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		actualX.setText(e.getX() + "");
		actualY.setText(e.getY() + "");
	}

	private void createShape(double x, double y, String value) {
		switch (value) {
		case RECTANGLE:
			CanvasManager.getInstance().add(add(new Rectangle((int) x, (int) y, 50, 50)));
			break;
		case ELLIPSE:
			CanvasManager.getInstance().add(add(new Ellipse((int) x, (int) y, 50, 50)));
			break;
		case TRIANGLE:
			CanvasManager.getInstance().add(add(new Triangle((int) x, (int) y, 50, 50)));
			break;
		default:

		}
	}

	private void removeShape(double x, double y) {
		CanvasManager cm = CanvasManager.getInstance();
		for (Paintable p : CanvasManager.getInstance().registeredSubjects()) {
			if (p instanceof Clickable) {
				Clickable clickable = (Clickable) p;
				if (clickable.isInBound(x, y)) {
					cm.remove(p);
				}

			}
		}

	}

	private void moveShape(double x, double y) {
		// TODO
		// if no shape is selected:
		if (selected == null) {
			for (Paintable paintable : CanvasManager.getInstance()
					.registeredSubjects()) {

				if (paintable instanceof Clickable 
						&& paintable instanceof Movable) {
					if (((Clickable)paintable).isInBound(x, y)) {
						// select
						selected = (Movable) paintable;
					}
				}
			}
		// else:
		} else {
			// move selected shape to specific position - call moveTo
			moveTo(selected, x, y);
			selected = null;
		}



	}

	private void doMoving(Movable obj, double x, double y) {
		// TODO here put code that implements animation
		new Mover(20).moveOn(obj,(int) x,(int) y);
	};

	private void moveTo(Movable obj, double x, double y) {
		new Thread() {
			@Override
			public void run() {
				controlPanel.setDisable(true);
				doMoving(obj, x, y);
				controlPanel.setDisable(false);
			}

		}.start();
	}

	private Paintable add(Paintable rectangle2) {
		createdObjects.add(rectangle2);
		return rectangle2;
	}

}
