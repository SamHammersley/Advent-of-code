package adventofcode;

public abstract class Challenge<T> {

	protected final InstructionSet<T> instructionSet;
	
	public Challenge(InstructionSet<T> instructionSet) {
		this.instructionSet = instructionSet;
	}
	
	public abstract void init();
	
	public abstract void part1();
	
	public abstract void part2();
	
}