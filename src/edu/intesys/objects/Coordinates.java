package edu.intesys.objects;

/**
 * Created by JohnPaul on 10/4/2016.
 */
public class Coordinates {
	private int x;
	private int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Coordinates) obj).x == this.x && ((Coordinates) obj).y == this.y;
	}
}
