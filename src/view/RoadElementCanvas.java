package view;

import org.vaadin.hezamu.canvas.Canvas;

import com.vaadin.ui.UI;

import controller.GameController;
import model.RoadElement;
import model.Traveler;
import model.RoadElement.WalkType;

public class RoadElementCanvas extends Canvas {
	public static enum RoadElementType {START, UP, DOWN, CROSS, END}
	public final RoadElementType type;
	RoadElementEditor editor;
	
	public final RoadElement roadElement;
	
	private static final String bgColor = "#999966";
	private static final String roadColor = "green";
	private static final String selectedFrameColor = "blue";
	
	public RoadElementCanvas(RoadElement re) {
		this.roadElement = re;
		this.type = re.type;
				
		this.setWidth("300px");
		this.setHeight("300px");
		
		draw(type);
	}

	public void initializeController(GameController gameController) {
		this.addMouseDownListener( () -> gameController.setCurrentSelection(this));
	}

	private void draw(RoadElementType type) {
		this.setFillStyle(bgColor);
		this.fillRect(0, 0, 300, 300);
		this.beginPath();
		this.setLineWidth(20);
		this.setLineJoin("round");
		this.setStrokeStyle(roadColor);

		if(type == RoadElementType.START) {
			drawStart();
		}
		else if(type == RoadElementType.END) {
			drawEnd();
		}
		else {
			drawTwoRoads();
			if(type == RoadElementType.CROSS || type == RoadElementType.DOWN)
				drawDownTurn();
			if(type == RoadElementType.CROSS || type == RoadElementType.UP)
				drawUpTurn();			
		}
	}

	private void drawEnd() {
		this.setLineWidth(5);
		this.moveTo(0, 150);
		this.lineTo(300, 150);
		this.stroke();
		
		this.setFillStyle("white");
		this.setFont("bold 30px sans-serif");
		this.fillText("Vár", 110, 80, 100);
		this.fillText("Erdõ", 110, 220, 100);
	}
	
	private void drawStart() {
		this.moveTo(0, 150);
		this.lineTo(80, 150);
		this.lineTo(220, 60);
		this.lineTo(300, 60);

		this.moveTo(80, 150);
		this.lineTo(220, 240);
		this.lineTo(300, 240);
		
		this.stroke();
		
	}

	private void drawTwoRoads() {
		this.moveTo(0, 60);
		this.lineTo(300, 60);
		this.moveTo(0, 240);
		this.lineTo(300, 240);
		this.stroke();
		
	}
	
	private void drawDownTurn() {
		this.moveTo(80, 60);
		this.lineTo(220, 240);
		this.stroke();		
	}

	private void drawUpTurn() {
		this.moveTo(80, 240);
		this.lineTo(220, 60);
		this.stroke();		
	}
	
	private void drawFrame() {
		this.saveContext();
		this.setLineWidth(5);
		this.beginPath();
		this.setStrokeStyle(selectedFrameColor);
		this.moveTo(0, 0);
		this.lineTo(0, 300);
		this.lineTo(300, 300);
		this.lineTo(300, 0);
		this.lineTo(0, 0);
		this.stroke();
		this.restoreContext();
	}
	
	public void unselected() {
		draw(type);
	}
	
	public void selected() {
		drawFrame();
	}
	
	public void animateTravel(Traveler t, WalkType wt) {
//		System.out.println(this.roadElement.travel());
		
		System.out.println("Animating " + t + " " + wt);
	}
	
	
}