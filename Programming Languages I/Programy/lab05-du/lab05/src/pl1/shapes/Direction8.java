package pl1.shapes;


public enum Direction8 {

	EAST(1, 0, "S"), NORTHEAST(1, -1, "SV"), NORTH(0, -1, "S"), NORTHWEST(-1, -1, "SZ"), WEST(-1, 0, "Z"),
	SOUTHWEST(-1, 1, "JZ"), SOUTH(0, 1, "J"), SOUTHEAST(1, 1, "JV"), NONE(0, 0, "@"),;

	private final int dx;
	private final int dy;
	private String abbreviation;

	private Direction8(int dx, int dy, String abbreviation) {
		this.dx = dx;
		this.dy = dy;
		this.abbreviation = abbreviation;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
	}

}
