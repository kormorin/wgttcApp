package model;

import java.util.HashSet;
import java.util.Set;

import view.RoadElementCanvas.RoadElementType;

public class RoadElement {
	public String property;
	public HashSet<String> straightGoers = new HashSet<>();
	public RoadElementType type;
	Context context = new Context();
	public enum PathType {LOWER, UPPER;
		public PathType other() {return (this == PathType.LOWER) ? UPPER : LOWER;}
	};
	protected PathType leavingPath;
	protected Traveler leavingTraveler;
	public enum WalkType {STARTLEFT, STARTRIGHT, GOSTRAIGHTUP,
		GOSTRAIGHTDOWN, TURNUP, TURNDOWN, ARRIVETOCASTLE, ARRIVETOFOREST}
	
	public RoadElement(RoadElementType type) {
		this.type = type;
	}
	
	protected Traveler getLeavingTraveler() { return leavingTraveler; }
	protected PathType getLeavingPathType() { return leavingPath; }
	
	public void setProperty(String newProperty) {
		property = newProperty;
	}
	
	public void setRoadSigns(String prop, Set<String> set) {
//		if(set != null && set.size() > 0 && set.size() < context.getValues(prop).size()) {
		property = prop;
		straightGoers.clear();
		straightGoers.addAll(set);
//		}
	}
	
	public WalkType travel(Traveler t, PathType fromPath) {
		WalkType walkType = null;
		boolean inStraightGoers = false;
		
		try {
			inStraightGoers = straightGoers.contains(t.getValue(property));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(inStraightGoers) {
			leavingPath = fromPath;
			walkType = (fromPath == PathType.LOWER) ?
					WalkType.GOSTRAIGHTDOWN : WalkType.GOSTRAIGHTUP;
		}
		else {
			leavingPath = fromPath.other();
			walkType = (fromPath == PathType.LOWER) ?
					WalkType.TURNUP : WalkType.TURNDOWN;

		}
		
		
		return walkType;
	}
	
	public boolean isValid() {
		if(this.type == RoadElementType.END)
			return true;
		
		if(this.property == null || this.straightGoers == null ||
				this.straightGoers.size() == 0 || 
				this.straightGoers.size() == this.context.getValues(property).size())
			return false;
		
		else
			return true;
	}
	
	
}
