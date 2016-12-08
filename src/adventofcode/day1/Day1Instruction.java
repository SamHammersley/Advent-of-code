package adventofcode.day1;

import java.util.Objects;

public final class Day1Instruction {
	
	private final char orientation;
	
	private final int count;
	
	public Day1Instruction(char orientation, int count) {
		this.orientation = orientation;
		this.count = count;
	}
	
	public char getOrientation() {
		return orientation;
	}
	
	public int getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		return orientation + ": " + count;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orientation, count);
	}
	
	public static Day1Instruction parseInstruction(String instruction) {
		char orientation = instruction.charAt(0);
		int count = Integer.parseInt(instruction.substring(1, instruction.length()));
		
		return new Day1Instruction(orientation, count);
	}
	
}