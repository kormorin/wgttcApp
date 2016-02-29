package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.RoadElement.PathType;
import model.RoadElement.WalkType;
import view.RoadElementCanvas.RoadElementType;


public class Road {
	private Set<Traveler> travelers;
	private Iterator<Traveler> travelerIterator;
	private ArrayList<RoadElement> roadElements = new ArrayList<>();
	private ArrayList<Journey> result = new ArrayList<>();
	private Context context = new Context();
	private boolean isValid = false;
	
	public Road() {
		roadElements.add(new RoadElement(RoadElementType.START));
		roadElements.add(new RoadElement(RoadElementType.END));
		
		try {
			travelers = context.getAllTravelers();
			
			travelerIterator = travelers.iterator();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeRoadElement(RoadElement re) {
		if(re.type != RoadElementType.START && re.type != RoadElementType.END) {
			this.roadElements.remove(re);
		}
	}
	
	public RoadElement getStartRoadElement() {
		return roadElements.get(0);
	}
	 
	public RoadElement getEndRoadElement() {
		return roadElements.get(roadElements.size() - 1);
	}
	
	public void moveUp(RoadElement re) {
		int index = roadElements.indexOf(re);
		
		if(index > 0 && index < roadElements.size() - 2) {
			roadElements.remove(re);
			roadElements.add(index + 1, re);
		}
	}
	
	public void moveDown(RoadElement re) {
		int index = roadElements.indexOf(re);
		
		if(index > 1 && index < roadElements.size() - 1) {
			roadElements.remove(re);
			roadElements.add(index + 1, re);
		}
	}
	
	public RoadElement addRoadElement(RoadElementType type) {
		RoadElement re = new RoadElement(type);
		roadElements.add(1, re);
		return re;
	}
	
	public void setRoadSigns(int index, String prop, Set<String> set) {
		roadElements.get(index).setRoadSigns(prop, set);
	}
	
	public List<Journey> play() throws InvalidRoadException {
		//check for validity
		for(RoadElement re : roadElements) {
			if(! re.isValid())
				throw new InvalidRoadException();
		}
		
		//dummy érték elsõ híváshoz
		PathType fromPath = PathType.LOWER;
		
		while(travelerIterator.hasNext()) {
			Traveler traveler = travelerIterator.next();
			Journey journey = new Journey(traveler);

			for(RoadElement re : roadElements) {
				WalkType walkType = re.travel(traveler, fromPath);
				fromPath = re.leavingPath;
				journey.walkTypes.add(walkType);
			}

			result.add(journey);				
		}
		
		return result;
	}
	
	public class InvalidRoadException extends Exception {}
	
	public RoadElement getRoadElements(int index) {
		return roadElements.get(index);
	}
	
	public int getIndexOf(RoadElement re) {
		return roadElements.indexOf(re);
	}
	
	
}