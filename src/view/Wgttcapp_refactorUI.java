package view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;

import model.Road;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import controller.GameController;

@SuppressWarnings("serial")
@Theme("wgttcapp_refactor")
public class Wgttcapp_refactorUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Wgttcapp_refactorUI.class, widgetset = "com.example.wgttcapp_refactor.widgetset.Wgttcapp_refactorWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	final GameController gameController = new GameController();
	
	final Road road = gameController.getRoad();
	final GameArea gameArea = gameController.getGameArea();
	final RoadElementEditor roadElementEditor = gameController.getRoadElementEditor();
	

	@Override
	protected void init(VaadinRequest request) {
		gameController.initialize();
		
		final VerticalLayout mainLayout = new VerticalLayout();
		final GameMenu gameMenu = new GameMenu(roadElementEditor, gameArea);
		
				
		mainLayout.addComponent(gameArea);
		mainLayout.addComponent(gameMenu);
		
		setContent(mainLayout);		
	}
		
	public GameController getGameController() { return gameController; }

}