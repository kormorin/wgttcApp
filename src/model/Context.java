package model;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.Arrays;
import java.util.HashMap;

public class Context {
	HashMap<String, HashSet<String>> map = new HashMap<>();
	
	public Context() {
		map.put("color", new HashSet(Arrays.asList("blue", "green", "yellow")));
		map.put("size", new HashSet(Arrays.asList("large", "medium", "small", "tiny")));
		map.put("shape", new HashSet(Arrays.asList("circle", "square")));
	}
	
	public Set<String> getProperties() {
		return map.keySet();
	}
	
	public HashSet<String> getValues(String travelerProperty) {
		if(travelerProperty == null)
			return new HashSet<>();
		
		return (HashSet<String>)map.get(travelerProperty);
	}

	
	
	
	public HashSet<Traveler> getAllTravelers() throws Exception {
		HashSet<Traveler> retval = new HashSet<>();
		
		HashSet<String> keySetCopy = new HashSet<>(map.keySet());
		
		if(keySetCopy.size() < 2)
			throw new Exception("A kontextus kevesebb, mint 2 tulajdonságot tartalmaz.");
		
		Iterator<String> i = keySetCopy.iterator();
		String prop1 = i.next();
		String prop2 = i.next();
		
		HashSet<HashMap<String,String>> descartesMultResult = descartesMult(prop1, prop2);
		
		if(keySetCopy.size() > 2)
			i.forEachRemaining(p -> descartesMult(descartesMultResult, p));
		
		for(HashMap<String, String> hashMap : descartesMultResult) {
			retval.add(new Traveler(hashMap));
		}
		return retval;

	}
	
	private HashSet<HashMap<String,String>> descartesMult(String prop1, String prop2) {
		HashSet<HashMap<String,String>> retval = new HashSet<>();
		
		for(String value1 : map.get(prop1)) {
			for(String value2 : map.get(prop2)) {
				HashMap<String,String> newPropVal = new HashMap<String,String>();
				newPropVal.put(prop1, value1);
				newPropVal.put(prop2, value2);
				retval.add(newPropVal);
			}
		}
		
		return retval;
	}

	private void descartesMult( HashSet<HashMap<String,String>> set, String prop) {
		HashSet<HashMap<String,String>> newSet = new HashSet<>();
		
		for(HashMap<String, String> element : set) {
			for(String value : map.get(prop)) {
				HashMap<String, String> newElement = new HashMap<>(element);
				newElement.put(prop, value);
				newSet.add(newElement);
			}
		}
		
		set.clear();
		set.addAll(newSet);
	}

}
