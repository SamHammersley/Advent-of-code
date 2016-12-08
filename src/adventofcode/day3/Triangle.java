package adventofcode.day3;

import java.util.Arrays;

public final class Triangle {

	private Integer[] sides = new Integer[3];
	
	public Triangle(int first, int second, int third) {
		sides[0] = first;
		sides[1] = second;
		sides[2] = third;
	}
	
	public Triangle(Integer[] sides) {
		this.sides = sides;
	}
	
	public int getFirst() {
		return sides[0];
	}
	
	public int getSecond() {
		return sides[1];
	}
	
	public int getThird() {
		return sides[2];
	}
	
	public static Triangle parse(String instruction) {
		String[] split = instruction.split("\\s+");
		Integer[] sides = Arrays.stream(split).map(Integer::parseInt).toArray(Integer[]::new);
		
		return new Triangle(sides);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(sides);
	}
	
	public boolean isValidTriangle() {
		return sides[0] + sides[1] > sides[2]
			&& sides[1] + sides[2] > sides[0]
			&& sides[2] + sides[0] > sides[1];
	}
	
}