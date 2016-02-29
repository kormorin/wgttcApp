package view;

import java.util.List;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import controller.GameController;
import model.Journey;
import model.Road;
import model.RoadElement;
import model.Road.InvalidRoadException;
import view.RoadElementCanvas.RoadElementType;

public class GameArea extends HorizontalLayout {
	private final RoadElementCanvas startRoadElement;
	private final RoadElementCanvas endRoadElement;
	
	public GameArea(RoadElement start, RoadElement end) {
		this.startRoadElement = new RoadElementCanvas(start);
		this.endRoadElement = new RoadElementCanvas(end);
				
		this.addComponent((Component) startRoadElement);
		this.addComponent((Component) endRoadElement);
	
	}
	
	public void initialize(GameController gameController) {
		startRoadElement.initializeController(gameController);
		endRoadElement.initializeController(gameController);
	}
	
	public RoadElementCanvas getStartElement() { return startRoadElement; }
	
	public void addRoadElement(RoadElementCanvas rec) {
		this.addComponent((Component) rec,1);
	}
	
	public void removeRoadElement(RoadElementCanvas rec) {
		if(rec.type != RoadElementType.START && rec.type != RoadElementType.END) {
			this.removeComponent(rec);
		}
	}
	
	public void moveUp(RoadElementCanvas rec) {
		int index = this.getComponentIndex(rec);
		
		if(index < this.getComponentCount() - 2 && index != 0) {
			this.removeComponent((Component) rec);
			
			this.addComponent((Component) rec, index + 1);
		}
	}

	public void moveDown(RoadElementCanvas rec) {
		int index = this.getComponentIndex(rec);
		
		if(index > 1 && index != this.getComponentCount() - 1) {
			this.removeComponent((Component) rec);			

			this.addComponent((Component) rec, index-1);
		}
	}
	
	public void animateSolution() throws InvalidRoadException {
//		List<Journey> journeys = road.play();
//		
//		for(Journey j : journeys) {
//			for(int i = 0; i < this.getComponentCount(); i++) {
//				RoadElementCanvas rec = (RoadElementCanvas)this.getComponent(i);
//				rec.animateTravel(j.getTraveler(), j.walkTypes.get(i));
//			}
//			
//		}
	}

	

}
