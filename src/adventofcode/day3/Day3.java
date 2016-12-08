package adventofcode.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import adventofcode.Challenge;
import adventofcode.InstructionSet;

public final class Day3 extends Challenge<Triangle> {

	public Day3() {
		super(new InstructionSet<>("\n\\s*", "(([\\d+]\\s*)+){3}", Triangle::parse));
	}

	@Override
	public void init() {
		try (BufferedReader reader = new BufferedReader(new FileReader("./2016/day3_input.txt"))) {
			instructionSet.populate(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void part1() {
		System.out.println(instructionSet.stream().filter(Triangle::isValidTriangle).count());
	}

	@Override
	public void part2() {
		Set<Triangle> triangles = new HashSet<>();
		
		List<Integer> firstColumn = instructionSet.stream().map(Triangle::getFirst).collect(Collectors.toList());
		triangles.addAll(parseColumn(firstColumn));
		
		List<Integer> secondColumn = instructionSet.stream().map(Triangle::getSecond).collect(Collectors.toList());
		triangles.addAll(parseColumn(secondColumn));
		
		List<Integer> thirdColumn = instructionSet.stream().map(Triangle::getThird).collect(Collectors.toList());
		triangles.addAll(parseColumn(thirdColumn));
		
		System.out.println(triangles.size());
	}

	private Set<Triangle> parseColumn(List<Integer> column) {
		Set<Triangle> triangles = new HashSet<>();
		
		for (int index = 0; index < column.size(); index += 3) {
			Triangle triangle = new Triangle(column.get(index), column.get(index + 1), column.get(index + 2));
			
			if (triangle.isValidTriangle()) {
				triangles.add(triangle);
			}
		}
		
		return triangles;
	}

}