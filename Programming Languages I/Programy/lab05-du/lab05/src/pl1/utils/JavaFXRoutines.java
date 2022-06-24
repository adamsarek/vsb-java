package pl1.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.fxml.FXMLLoader;

public class JavaFXRoutines {
	public static <T> T loadFXML(Object _this,String fxmlName) {
	      try {
	        FXMLLoader fxmlLoader = new FXMLLoader(_this.getClass().getResource("manipulation_panel.fxml"));
	        fxmlLoader.setControllerFactory(c->{
	          try {
	            return c.equals(_this.getClass())?_this:c.getDeclaredConstructor().newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
		            throw new RuntimeException(e);
	          }
	        });
	        return fxmlLoader.load();
	        
	      } catch (IOException e) {
	        throw new RuntimeException(e);
	      }
	    }
}
