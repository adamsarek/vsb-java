package pl1.manager;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import pl1.simulation.JavaFXApplication;

public class CanvasManager {

	private static CanvasManager instance;
	
	public static synchronized CanvasManager getInstance() {
		if (instance == null) {
			new Thread(JavaFXApplication::doLaunch).start();
			while(instance == null) {
				try {
					CanvasManager.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return instance;
	}
	
	static synchronized void constructInstance(javafx.scene.canvas.Canvas fxCanvas) {
		if (instance != null) {
			throw new IllegalStateException("Canvas already created");
		}
		instance = new CanvasManager(fxCanvas);
		CanvasManager.class.notifyAll();
	}
	
	private javafx.scene.canvas.Canvas fxCanvas;
	
	private List<Paintable> subjects = new LinkedList<>();

	private CanvasManager(javafx.scene.canvas.Canvas fxCanvas) {
		this.fxCanvas = fxCanvas;
	}
	
	public void redraw() {
		GraphicsContext gc = fxCanvas.getGraphicsContext2D();
		Paint original = gc.getFill();
		gc.setFill(Color.LIGHTYELLOW);
		gc.fillRect(0, 0, fxCanvas.getWidth(), fxCanvas.getHeight());
		gc.setFill(original);
		for(Paintable p: subjects) {
			p.paint(gc);
		}
	}

	public double getHeight() {
		return fxCanvas.getHeight();
	}

	public double getWidth() {
		return fxCanvas.getWidth();
	}

	public void add(Paintable p) {
		subjects.add(p);
	}
}
