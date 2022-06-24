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
import pl1.shapes.AbstractShape;
import pl1.shapes.manager.Triangle;
import pl1.shapes.manager.CanvasManager;
import pl1.shapes.Position;
import pl1.shapes.manager.Servant;
import pl1.shapes.tools.Movable;
import pl1.shapes.tools.Transformable;

/**
 * @author Jan Kožusznik
 * @version 0.1
 */
public class ManipulationPanelController {

  private static final String RECTANGLE = "Obdelník";

  private static final String TRIANGLE = "Trojúhelník";

  private static final String ELLIPSE = "Elipsa";

  private ArrayList<AbstractShape> createdObjects = new ArrayList<>();
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
  private RadioButton transformRB;

  @FXML
  private TextField actualX;

  @FXML
  private TextField actualY;

  @FXML
  private TextField selectedX;

  @FXML
  private TextField selectedY;
  
  @FXML
  private TextField resizeTF;

  @FXML
  private Node controlPanel;

  /**
   *
   */
  public ManipulationPanelController() {
    Platform.runLater(() -> {
      toCreate.setItems(
          FXCollections.observableArrayList(RECTANGLE, TRIANGLE, ELLIPSE));
      toCreate.setValue(RECTANGLE);
    });
  }

  	public void mousePressed(MouseEvent e) {

  		if (e.isPrimaryButtonDown()) 
  		{
  			selectedX.setText(e.getX() + "");
  			selectedY.setText(e.getY() + "");
  			if (createRB.isSelected()) 
  			{
  				createShape(e.getX(), e.getY(), toCreate.getValue());
  			} 
  			else if (moveRB.isSelected()) 
  			{
  				moveShape(e.getX(), e.getY());
  			} 
  			else if (deleteRB.isSelected()) 
  			{
  				for (AbstractShape i : createdObjects) 
  				{
  					if (i instanceof Clickable)
  					{
  						Clickable cl = (Clickable) i;
  						if (cl.isInBound(e.getX(), e.getY())) 
  						{
  							createdObjects.remove(i);
  							CanvasManager.getInstance().remove(i);
  							break;
  						}
  					}
  				}
  			}
  			else if (transformRB.isSelected()) 
  			{
  				transformShape(e.getX(), e.getY(), Double.parseDouble(resizeTF.getText()));
  			}
  		}
  	}

  public void mouseMoved(MouseEvent e) {
    actualX.setText(e.getX() + "");
    actualY.setText(e.getY() + "");
  }

  	Clickable selectedForMoving = null;
  
  	private void moveShape(double x, double y) 
  	{

  		if(selectedForMoving == null)
  		{
  			for (Paintable i : createdObjects)
			{
				if (i instanceof Clickable)
				{
					if (((Clickable)i).isInBound(x, y))
					{
						System.out.println("Byl vybran objekt cislo " + i + ".");
						selectedForMoving = (Clickable)i;
						break;
					}
				}
			}
  		  
  			if(selectedForMoving == null)
  			{
  				System.out.println("Prosim vyberte obrazec, ktery chcete presunout.");
  			}
  	  	}
  		else
  	  	{
  		  	moveTo(selectedForMoving, x, y);
  		  	selectedForMoving = null;
  	  	}
  	}
  	
  	private void transformShape(double x, double y, double resize) 
  	{

  		if(selectedForMoving == null)
  		{
  			for (Paintable i : createdObjects)
			{
				if (i instanceof Clickable)
				{
					if (((Clickable)i).isInBound(x, y))
					{
						System.out.println("Byl vybran objekt cislo " + i + ".");
						selectedForMoving = (Clickable)i;
						break;
					}
				}
			}
  		  
  			if(selectedForMoving == null)
  			{
  				System.out.println("Prosim vyberte obrazec, ktery chcete presunout.");
  			}
  	  	}
  		else
  	  	{
  		  	transformTo(selectedForMoving, x, y, resize);
  		  	selectedForMoving = null;
  	  	}
  	}

  private void doMoving(Object obj, double x, double y) {
	  //TODO zde bude kod pro volání objektu, který zajistí animaci
	  System.out.println("Moving selected object to: " + x + ", " + y);
		Servant.moveTo((Movable)obj, new Position((int)x, (int)y), 5, 10);
  }
  
  private void doTransform(Object obj, double x, double y, double resize) {
	  //TODO zde bude kod pro volání objektu, který zajistí animaci
	  System.out.println("Moving selected object to: " + x + ", " + y);
		Transformer.transform((Transformable)obj, new Position((int)x, (int)y), resize);
  }

  private void moveTo(Object obj, double x, double y) {
    new Thread() {
      @Override
      public void run() {
        controlPanel.setDisable(true);
        doMoving(obj, x, y);
        controlPanel.setDisable(false);
      }

    }.start();
  }
  
  private void transformTo(Object obj, double x, double y, double resize) {
	    new Thread() {
	      @Override
	      public void run() {
	        controlPanel.setDisable(true);
	        doTransform(obj, x, y, resize);
	        controlPanel.setDisable(false);
	      }

	    }.start();
	  }
  
  private void createShape(double x, double y, String value) {
    switch (value) {
      case RECTANGLE:
        CanvasManager.getInstance()
            .add(add(new Rectangle((int) x, (int) y, 50, 50)));
        break;
      case TRIANGLE:
          CanvasManager.getInstance()
            .add(add(new Triangle((int) x, (int) y, 50, 50)));
          break;
      case ELLIPSE:
        CanvasManager.getInstance()
            .add(add(new Ellipse((int) x, (int) y, 50, 50)));
        break;
    }
  }

  private Paintable add(AbstractShape obj) {
    createdObjects.add(obj);
    return obj;
  }

}
