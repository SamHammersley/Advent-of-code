package adventofcode.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import adventofcode.Challenge;
import adventofcode.InstructionSet;
import adventofcode.movement.Direction;
import adventofcode.movement.Point2D;

public final class Day1 extends Challenge<Day1Instruction> {
	
	private static final Point2D INITIAL = new Point2D(0, 0);

	public Day1() {
		super(new InstructionSet<>(", ", "(L|R)(\\d+)", Day1Instruction::parseInstruction));
	}
	
	@Override
	public void init() {
		try (BufferedReader reader = new BufferedReader(new FileReader("./2016/day1_input.txt"))) {
			instructionSet.populate(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void part1() {
		Point2D current = new Point2D(0, 0);
		
		Direction direction = Direction.NORTH;
		
		for (Day1Instruction instruction : instructionSet) {
			direction = direction.rotate(instruction.getOrientation());
			current = current.move(direction, instruction.getCount());
		}
		
		System.out.println(INITIAL.distance(current));
	}
	
	@Override
	public void part2() {
		Point2D current = new Point2D(0, 0);
		
		Set<Point2D> visited = new HashSet<>();
		visited.add(INITIAL);
		
		Optional<Point2D> multipleVisits = Optional.empty();
		
		Direction direction = Direction.NORTH;
		
		for (Day1Instruction instruction : instructionSet) {
			direction = direction.rotate(instruction.getOrientation());
			
			for (int step = 0; step < instruction.getCount(); step++) {
				current = current.move(direction);
				
				if (!multipleVisits.isPresent() && visited.contains(current)) {
					multipleVisits = Optional.of(current);
				}
				
				visited.add(current);
			}
		}
		
		multipleVisits.map(INITIAL::distance).ifPresent(System.out::println);
	}

}