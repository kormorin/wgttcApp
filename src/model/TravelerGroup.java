package model;

import java.util.Set;


public class TravelerGroup {
	public Context context = new Context();
	public Set<Traveler> travelers;
	
	public TravelerGroup(Set<Traveler> set) {
		travelers = set;
		
	}
	
	public TravelerGroup filter(String property, Set<String> set) {
//		for(Traveler t : travelers) {
//			try {
//				if(!set.contains(t.getValue(property))) {
//					travelers.remove(t);
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		travelers.removeIf(t -> {
			try {
				String value = t.getValue(property);
				return !set.contains(value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		});
		
		return this;
	}
	
	public TravelerGroup union(TravelerGroup tg) {
		for(Traveler t1 : tg.travelers) {
			boolean contained = false;
			for(Traveler t2 : travelers) {
				if(t1.equals(t2)) {
					contained = true;
					break;
				}
			}
			
			if(!contained)
				travelers.add(t1);
		}
		
		return this;
	}
	
	public boolean equals(Object tg) {
		return (tg.getClass().equals(TravelerGroup.class) &&
				((TravelerGroup)tg).travelers.equals(travelers) );
	}
}
