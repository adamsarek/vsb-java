package pl1.lab06;

import javafx.scene.image.Image;

/**
 * Constants used in application.
 * 
 * @author koz01
 *
 */
public final class Constants {
	
	public static final int GRAVITY = 10;
	
	public static final int INDENTATION = 10;
	
	public static final int INITIAL_POWER = 10;

	public static final int MIN_POWER = 10;
	
	public static final int MAX_POWER = 100;

	public static final int SIZE_OF_BALL = 5;

	public static final int LEN_OF_CATAPULT = 40;
	
	public static final Image UFO;
	static{ 
		UFO = new Image(Constants.class.getResourceAsStream("ufo-512.png"));
	}

	private  Constants() {	}
}
