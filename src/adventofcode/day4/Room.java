package adventofcode.day4;

public final class Room {

	private final String name;
	
	private final int sectorId;
	
	private final String checkSum;
	
	public Room(String name, int sectorId, String checkSum) {
		this.name = name;
		this.sectorId = sectorId;
		this.checkSum = checkSum;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSectorId() {
		return sectorId;
	}
	
	public String getCheckSum() {
		return checkSum;
	}
	
	@Override
	public String toString() {
		return name + "-" + sectorId + "[" + checkSum + "]";
	}
	
	public static Room parse(String instruction) {
		int lastDashIndex = instruction.lastIndexOf('-');
		int bracketIndex = instruction.indexOf('[');
		
		String name = instruction.substring(0, lastDashIndex);
		int sectorId = Integer.parseInt(instruction.substring(lastDashIndex + 1, bracketIndex));
		String checkSum = instruction.substring(bracketIndex + 1, instruction.lastIndexOf(']'));
		
		return new Room(name, sectorId, checkSum);
	}
	
}