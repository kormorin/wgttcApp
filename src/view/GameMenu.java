package view;


import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import controller.GameController;
import model.RoadElement;
import view.RoadElementCanvas.RoadElementType;

public class GameMenu extends HorizontalLayout {
	
	GameArea gameArea;
	RoadElementEditor roadElementEditor;
	final VerticalLayout addElementMenu = new VerticalLayout();
	final Label infolabel = new Label();
	final GameController gameController;
	
	public GameMenu(RoadElementEditor ree, GameArea ga) {
		this.gameController = ( (Wgttcapp_refactorUI) UI.getCurrent() ).gameController;
		this.roadElementEditor = ree;
		this.gameArea = ga;
		
		addElementMenu.addComponent(new Label("Új útelem"));
		addElementMenu.addComponent(new AddButton("Fel", RoadElementType.UP));
		addElementMenu.addComponent(new AddButton("Le", RoadElementType.DOWN));
		addElementMenu.addComponent(new AddButton("Keresztezõdés", RoadElementType.CROSS));
		
		this.addComponent(addElementMenu);
		this.addComponent(roadElementEditor);

		this.addComponent(new MoveDownButton());
		this.addComponent(new MoveUpButton());
		this.addComponent(new RemoveButton());
		this.addComponent(new TrySolutionButton());
		this.addComponent(infolabel);
	}

	private class AddButton extends Button {
		public AddButton(String caption, RoadElementType type) {
			super(caption);
			this.addClickListener(e -> 	gameController.addElement(type) );
		}
	}
	
	private class RemoveButton extends Button {
		public RemoveButton() {
			super("Törlés");
			this.addClickListener(e -> gameController.removeElement() );
		}
	}
	
	private class MoveUpButton extends Button {
		public MoveUpButton() {
			super(">");
			this.addClickListener(e -> gameController.moveElementUp() );
		}
	}

	private class MoveDownButton extends Button {
		public MoveDownButton() {
			super("<");
			this.addClickListener(e -> gameController.moveElementDown() );
		}
	}
	
	private class TrySolutionButton extends Button {
		public TrySolutionButton() {
			super("Megoldás kipróbálása");
			this.addClickListener(e -> {
				try {
					gameArea.animateSolution();
					infolabel.setCaption("A megépített utat sikeresen kipróbáltuk.");
				} catch (Exception e1) {
					infolabel.setCaption("Az épített út nem teljes.");
				}
			} );

		}
	}
}
