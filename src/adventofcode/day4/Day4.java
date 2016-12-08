package adventofcode.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Stream;

import adventofcode.Challenge;
import adventofcode.InstructionSet;

public final class Day4 extends Challenge<Room> {

	public Day4() {
		super(new InstructionSet<>("\n", "[a-z-]+-[\\d]{3}\\[[a-z]{5}\\]", Room::parse));
	}

	@Override
	public void init() {
		try (BufferedReader reader = new BufferedReader(new FileReader("./2016/day4_input.txt"))) {
			instructionSet.populate(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void part1() {
		int sectorIdTotal = 0;
		
		for (Room room : instructionSet) {
			String roomName = room.getName().replaceAll("-", "");
			
			boolean authentic = getCheckSum(roomName).equalsIgnoreCase(room.getCheckSum());
			
			if (authentic) {
				sectorIdTotal += room.getSectorId();
			}
		}
		
		System.out.println(sectorIdTotal);
	}
	
	private static final int CHECK_SUM_LENGTH = 5;
	
	public String getCheckSum(String roomName) {
		Stream<Character> distinct = roomName.chars().mapToObj(c -> (char) c).distinct().sorted();

		Map<Character, Integer> commonChars = new TreeMap<>();
		distinct.forEach(c -> commonChars.put(c, getCharacterCount(roomName.toCharArray(), c)));

		StringBuilder bldr = new StringBuilder();

		Comparator<Entry<Character, Integer>> comparator = Entry.comparingByValue(Collections.reverseOrder());
		
		commonChars.entrySet().stream().sorted(comparator).limit(CHECK_SUM_LENGTH).map(Entry::getKey)
				.forEach(bldr::append);

		return bldr.toString();
	}

	private int getCharacterCount(char[] characters, Character character) {
		Arrays.sort(characters);
		String string = new String(characters);
		
		return string.lastIndexOf(character) - string.indexOf(character) + 1;
	}
	
	@Override
	public void part2() {
		
	}

}