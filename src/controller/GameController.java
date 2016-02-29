package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Context;
import model.Journey;
import model.Road;
import model.RoadElement;
import model.Road.InvalidRoadException;
import view.GameArea;
import view.RoadElementCanvas;
import view.RoadElementCanvas.RoadElementType;
import view.RoadElementEditor;

public class GameController {
	Context context = new Context();
	final Road road = new Road();
	final GameArea gameArea;
	final RoadElementEditor editor = new RoadElementEditor();
	RoadElementCanvas currentSelection;
	
	public GameController() {
		this.gameArea = new GameArea(road.getStartRoadElement(), road.getEndRoadElement());
	}
	
	public void initialize() {		
		editor.initializeController(this);
		gameArea.initialize(this);
		
		currentSelection = gameArea.getStartElement();
	}
	
	public Road getRoad() { return road; }
	public GameArea getGameArea() { return gameArea; }
	public RoadElementEditor getRoadElementEditor() { return editor; }
	
	public void addElement(RoadElementType type) {
		RoadElement re = road.addRoadElement(type);
		RoadElementCanvas rec = new RoadElementCanvas(new RoadElement(type));
		rec.initializeController(this);
		gameArea.addRoadElement(rec);
	}
	
	public void removeElement() {
		gameArea.removeRoadElement(currentSelection);
		road.removeRoadElement(currentSelection.roadElement);
	}
	
	public void moveElementUp() {
		gameArea.moveUp(currentSelection);
		road.moveUp(currentSelection.roadElement);
	}
	
	public void moveElementDown() {
		gameArea.moveDown(currentSelection);
		road.moveDown(currentSelection.roadElement);		
	}
	
	public void setCurrentSelection(RoadElementCanvas rec) {
		currentSelection.unselected();
		rec.selected();
		currentSelection = rec;

		RoadElement re = rec.roadElement;
		editor.switchProperty(re.property);
		editor.setRoadSigns(this.context.getValues(re.property));
		editor.setSelectedRoadSigns(re.straightGoers);
	}
	
	public void travelerPropertyChanged(String newProperty) {
		RoadElement re = currentSelection.roadElement;
		re.property = newProperty;
		re.straightGoers.clear();
		
		editor.setRoadSigns(context.getValues(newProperty));
		
//		if(!reChange && currentRoadElement != null) {
//		String property = (String)e.getProperty().getValue();
//
//		
//		roadSigns.removeAllItems();
//		HashSet<String> valueSet = context.getValues(property);
//		roadSigns.addItems(valueSet);
//		
//		currentRoadElement.setRoadSigns(property, valueSet);
//		}
		
	}
	
	public void roadSignsChanged(Set<String> selectedValues) {
		System.out.println(selectedValues);
		String property = currentSelection.roadElement.property;
		currentSelection.roadElement.setRoadSigns(property, selectedValues);
		
		editor.setSelectedRoadSigns(selectedValues);
//		if(!reChange && currentRoadElement != null) {
//			Set<String> newValues = (Set<String>) e.getProperty().getValue();
//			
//			currentRoadElement.setRoadSigns(currentRoadElement.property,
//					newValues);
//			
	}
	
	public void animateSolution() throws InvalidRoadException {
		List<Journey> journeys = road.play();
		
		for(Journey j : journeys) {
			for(int i = 0; i < gameArea.getComponentCount(); i++) {
				RoadElementCanvas rec = (RoadElementCanvas)gameArea.getComponent(i);
				rec.animateTravel(j.getTraveler(), j.walkTypes.get(i));
			}
			
		}
	}
}
