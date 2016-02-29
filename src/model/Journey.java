package model;

import java.util.ArrayList;

import model.RoadElement.WalkType;

public class Journey {
	public ArrayList<WalkType> walkTypes = new ArrayList<>();
	private Traveler traveler;
	
	public Journey(Traveler t) {
		traveler = t;
	}
	
	public Traveler getTraveler() { return traveler; }
}
