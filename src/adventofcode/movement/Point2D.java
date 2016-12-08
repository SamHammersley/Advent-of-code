package adventofcode.movement;

import java.util.Objects;

public final class Point2D {

	private final int x, y;
	
	public Point2D(int x, int y) {
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
	public String toString() {
		return "{" + x + "," + y + "}";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point2D)) {
			return false;
		}
		
		Point2D other = (Point2D) obj;
		return x == other.x && y == other.y;
	}

	public Point2D move(Direction direction) {
		return move(direction, 1);
	}
	
	public Point2D move(Direction direction, int steps) {
		int x = this.x;
		int y = this.y;
		
		if (direction == Direction.NORTH) {
			y -= steps;
		} else if (direction == Direction.EAST) {
			x += steps;
		} else if (direction == Direction.SOUTH) {
			y += steps;
		} else if (direction == Direction.WEST) {
			x -= steps;
		}
		
		return new Point2D(x, y);
	}
	
	public int distance(Point2D other) {
		return Math.abs(x - other.x) + Math.abs(y - other.y);
	}
	
}