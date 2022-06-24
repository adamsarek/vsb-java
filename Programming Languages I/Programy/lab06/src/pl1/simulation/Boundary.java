package pl1.simulation;

public interface Boundary {
	double getXOfCorner();

	double getYOfCorner();

	double getWidth();

	double getHeight();

	/**
	 * Implementation from https://www.geeksforgeeks.org/find-two-rectangles-overlap/
	 * @param b
	 * @return
	 */
	default boolean overlaps(Boundary b) {
		double l1_x = getXOfCorner();
		double r1_x = getXOfCorner() + getWidth();
		
		double l2_x = b.getXOfCorner();
		double r2_x = b.getXOfCorner() + b.getWidth();
		
		// If one rectangle is on left side of other
		if (l1_x > r2_x || l2_x > r1_x) {
			return false;
		}

		double l1_y = getYOfCorner();
		double r1_y = getYOfCorner() + getHeight();
		
		double l2_y = b.getYOfCorner();
		double r2_y = b.getYOfCorner() + b.getHeight();
		
		// If one rectangle is above other
		return !(l1_y > r2_y || l2_y > r1_y);
	}
}
