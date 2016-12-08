package adventofcode.movement;

public enum Direction {

	NORTH(0),
	
	EAST(1),
	
	SOUTH(2),
	
	WEST(3);
	
	private final int intValue;
	
	Direction(int intValue) {
		this.intValue = intValue;
	}
	
	public Direction rotate(char orientation) {
		Direction[] values = Direction.values();
		int offset = orientation == 'L' ? -1 : 1;
		
		return values[(intValue + offset) & (values.length - 1)];
	}
	
}