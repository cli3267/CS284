package Maze;

public class PairInt {
	//data fields
	private int x;
	private int y;
	
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * gets the x coordinate
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}
	/**
	 * gets the y coordinate
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}
	/**
	 * sets the x coordinate
	 * @param x new x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * sets the y coordinate
	 * @param y new y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * checks equality
	 * @return true if equal, otherwise false
	 */
	public boolean equals (Object p) {
		if(p.getClass() == this.getClass()) {
			PairInt pair = (PairInt)p;
			return pair.x == this.x && pair.y == this.y;
		}else {
			return false;
		}
	}
	
	/**
	 * overrides the toString method
	 * @return String representation of PairInt
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * copy of PairInt
	 * @return PairInt of same value
	 */
	public PairInt copy() {
		return new PairInt(this.x, this.y);
	}
}
