package model;

import java.util.HashMap;

public class Traveler {
	private final HashMap<String,String> propertyValues;
	
	public Traveler(HashMap<String,String> map) {
		propertyValues = map;
	}
	
	public String getValue(String property) throws Exception {
		String value = propertyValues.get(property);
		if(value != null)
			return value;
		else
			throw new Exception("Ennek az utazónak nincs ilyen tulajdonsága: " + property);
	}

	
	public String toString() {
		String str = "traveler(";
		for(String prop : propertyValues.keySet() ) {
			str += propertyValues.get(prop) + " ";
		}
		
		str += ")\n";
		return str;
	}
	
	public boolean equals(Object t) {
		return equals(t.getClass() == Traveler.class &&
				propertyValues.equals( ((Traveler)t).propertyValues )
				);
	}
}
