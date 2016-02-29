package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ContextManager {
	private String currentContextName = "shapes";
	private HashMap<String, Context> map = new HashMap<>();
	
	public ContextManager() {
		Context shapes = new Context();
		shapes.map.put("color", new HashSet(Arrays.asList("blue", "green", "yellow")));
		shapes.map.put("size", new HashSet(Arrays.asList("large", "medium", "small", "tiny")));
		shapes.map.put("shape", new HashSet(Arrays.asList("circle", "square")));

		Context dots = new Context();
		dots.map.put("number", new HashSet(Arrays.asList("one", "two", "three", "four", "five")));
		dots.map.put("color", new HashSet(Arrays.asList("red", "purple", "blue", "green", "yellow")));
		
		Context travelers = new Context();
		shapes.map.put("color", new HashSet(Arrays.asList("red", "blue")));
		shapes.map.put("gender", new HashSet(Arrays.asList("boy", "girl")));
		shapes.map.put("small/large", new HashSet(Arrays.asList("small", "large")));
		shapes.map.put("alone/group", new HashSet(Arrays.asList("alone", "with friends")));

		this.map.put("shapes", shapes);
		this.map.put("dots", dots);
		this.map.put("travelers", travelers);
	}
}
