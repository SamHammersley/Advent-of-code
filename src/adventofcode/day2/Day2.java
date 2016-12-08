package adventofcode.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import adventofcode.Challenge;
import adventofcode.InstructionSet;
import adventofcode.movement.Direction;
import adventofcode.movement.Point2D;

public final class Day2 extends Challenge<List<Character>> {

	private static final Map<Character, Direction> DIRECTIONS = new HashMap<>();
	
	static {
		DIRECTIONS.put('U', Direction.NORTH);
		DIRECTIONS.put('R', Direction.EAST);
		DIRECTIONS.put('D', Direction.SOUTH);
		DIRECTIONS.put('L', Direction.WEST);
	}
	
	public Day2() {
		super(new InstructionSet<>("\n", "(U|L|R|D)+", s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toList())));
	}

	@Override
	public void init() {
		try (BufferedReader reader = new BufferedReader(new FileReader("./2016/day2_input.txt"))) {
			instructionSet.populate(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<Integer> navigateKeyPad(char[] keypad, Point2D initial) {
		Point2D button = initial;
		int keypadLength = (int) Math.sqrt(keypad.length);
		List<Integer> indices = new ArrayList<>();
		
		for (List<Character> instructions : instructionSet) {
			List<Direction> directions = instructions.stream().map(DIRECTIONS::get).collect(Collectors.toList());
			
			for (Direction d : directions) {
				Point2D newPoint = button.move(d);
				int index = indexOf(newPoint, keypad);
				
				if (index >= 0 && index < keypad.length
						&& newPoint.getX() < keypadLength && newPoint.getY() < keypadLength
						&& newPoint.getX() >= 0 && newPoint.getY() >= 0) {
					
					if (keypad[index] != ' ') {
						button = newPoint;
					}
				}
			}
			
			indices.add(indexOf(button, keypad));
		}
		
		return indices;
	}

	private static final char[] KEYPAD_1 = {
			'1',	'2',	'3',
			'4',	'5',	'6',
			'7',	'8',	'9'
	};
	
	@Override
	public void part1() {
		List<Integer> indices = navigateKeyPad(KEYPAD_1, new Point2D(1, 1));
		
		indices.stream().map(i -> KEYPAD_1[i]).forEach(System.out::print);
		System.out.println();
	}

	private static final char[] KEYPAD_2 = {
			' ', ' ', '1', ' ', ' ',
			
			' ', '2', '3', '4', ' ',
			
			'5', '6', '7', '8', '9',
			
			' ', 'A', 'B', 'C', ' ',
			
			' ', ' ', 'D', ' ', ' ',
	};
	
	@Override
	public void part2() {
		List<Integer> indices = navigateKeyPad(KEYPAD_2, new Point2D(0, 2));
		
		indices.stream().map(i -> KEYPAD_2[i]).forEach(System.out::print);
	}
	
	public int indexOf(Point2D point, char[] keypad) {
		return (int) (point.getY() * Math.sqrt(keypad.length) + point.getX());
	}

}