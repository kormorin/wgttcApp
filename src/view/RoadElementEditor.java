package view;

import java.util.HashSet;
import java.util.Set;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;

import controller.GameController;
import model.Context;
import model.Road;
import model.RoadElement;

public class RoadElementEditor extends CustomComponent {
	private ComboBox travelerProperty = new ComboBox();
	private TwinColSelect  roadSigns = new TwinColSelect();
	private Context context = new Context();
	
	public RoadElementEditor() {
				
		travelerProperty.setInvalidAllowed(false);
		travelerProperty.setNewItemsAllowed(false);
		travelerProperty.setNullSelectionAllowed(false);
		for(String item : context.getProperties()) {
			travelerProperty.addItem(item);
		}
		
		roadSigns.setLeftColumnCaption("Egyenesen halad");
		roadSigns.setRightColumnCaption("Elkanyarodik");
		roadSigns.setHeight("150px");
		roadSigns.setNewItemsAllowed(false);
		roadSigns.setNullSelectionAllowed(false);
		roadSigns.setImmediate(true);
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(travelerProperty);
		layout.addComponent(roadSigns);
		layout.setSpacing(true);
		
		setCompositionRoot(layout);
	}
	
	public void initializeController(GameController gameController) {
		travelerProperty.addValueChangeListener(e ->
			gameController.travelerPropertyChanged((String) e.getProperty().getValue()));

		roadSigns.addValueChangeListener(e ->
			gameController.roadSignsChanged( (Set<String>)e.getProperty().getValue() ));
	}
	
	//amikor a lehetséges tulajdonságok nem változnak, csak az aktuális
	public void switchProperty(String property) {
		this.travelerProperty.setValue(property);
	}
	
	//amikor a lehetséges tulajdonságok megváltoz(hat)nak
	public void setProperty(Set<String> properties, String currentProperty) {
		this.setData(properties);
		this.travelerProperty.setValue(currentProperty);
	}
	
	public void setRoadSigns(Set<String> allValues) {
		this.roadSigns.removeAllItems();
		this.roadSigns.addItems(allValues);
	}
	
	public void setSelectedRoadSigns(Set<String> selectedValues) {
		this.roadSigns.setValue(selectedValues);
	}
	
}
