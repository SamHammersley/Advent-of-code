package adventofcode;

import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class InstructionSet<T> implements Iterable<T> {

	private final String delimiter;
	
	private final String pattern;
	
	private final Set<T> instructionSet = new LinkedHashSet<>();
	
	private final Function<String, T> conversionFunction;
	
	public InstructionSet(String delimiter, String pattern, Function<String, T> conversionFunction) {
		this.delimiter = delimiter;
		this.pattern = pattern;
		this.conversionFunction = conversionFunction;
	}
	
	public void populate(Reader reader) {
		try (Scanner scanner = new Scanner(reader)) {
			scanner.useDelimiter(delimiter);
			scanner.forEachRemaining(this::add);
		}
	}
	
	private void add(String instruction) {
		instruction = instruction.trim();
		if (!instruction.matches(pattern)) {
			throw new IllegalArgumentException(instruction + " is not a valid instruction!");
		}

		instructionSet.add(conversionFunction.apply(instruction));
	}
	
	public Stream<T> stream() {
		return instructionSet.stream();
	}
	
	@Override
	public Iterator<T> iterator() {
		return instructionSet.iterator();
	}

	public int size() {
		return instructionSet.size();
	}

}